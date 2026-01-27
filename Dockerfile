# Multi-stage build for optimized Docker image
# Stage 1: Test stage - Run all tests first
# NOTE I was using Java 11.0.27 for building earlier and this image uses Java 17.1.12
FROM sbtscala/scala-sbt:graalvm-ce-22.3.3-b1-java17_1.12.0_2.13.18 AS tester

WORKDIR /build

# Install SBT
RUN apk add --no-cache \
    curl \
    bash \
    && curl -fsSL "https://github.com/sbt/sbt/releases/download/v1.10.11/sbt-1.10.11.zip" -o sbt.zip \
    && unzip sbt.zip \
    && rm sbt.zip \
    && mv sbt /opt/sbt \
    && chmod +x /opt/sbt/bin/sbt
#ERROR: failed to build: failed to solve: process "/bin/sh -c apk add --no-cache     curl     bash     && curl -fsSL \"https://github.com/sbt/sbt/releases/download/v1.10.11/sbt-1.10.11.zip\" -o sbt.zip     && unzip sbt.zip     && rm sbt.zip     && mv sbt /opt/sbt     && chmod +x /opt/sbt/bin/sbt" did not complete successfully: exit code: 127
ENV PATH="/opt/sbt/bin:$PATH"

# Copy build files
COPY build.sbt .
COPY project ./project
COPY conf ./conf
COPY app ./app
COPY app-test ./app-test

# Run all tests - build fails if any test fails
RUN echo "========================================" && \
    echo "Running Test Suite:" && \
    echo "  1. ReversiMinMaxTreeTest" && \
    echo "  2. GameControllerSpec" && \
    echo "  3. ImmutableReversiEngineGetValidMovesTest" && \
    echo "  4. ImmutableReversiEngineMakeMoveTest" && \
    echo "========================================" && \
    sbt test && \
    echo "========================================" && \
    echo "✅ All tests passed! Proceeding with build." && \
    echo "========================================"

# Stage 2: Builder stage - Build the application (only runs if tests pass)
#    eclipse-temurin:11-jdk-alpine image doesn't contain a builder image for some reason
# building pipeline as a single image
FROM sbtscala/scala-sbt:graalvm-ce-22.3.3-b1-java17_1.12.0_2.13.18 AS builder
WORKDIR /build

## Install SBT
#RUN apk add --no-cache \
#    curl \
#    bash \
#    && curl -fsSL "https://github.com/sbt/sbt/releases/download/v1.10.11/sbt-1.10.11.zip" -o sbt.zip \
#    && unzip sbt.zip \
#    && rm sbt.zip \
#    && mv sbt /opt/sbt \
#    && chmod +x /opt/sbt/bin/sbt

ENV PATH="/opt/sbt/bin:$PATH"

# Copy build files
COPY build.sbt .
COPY project ./project
COPY conf ./conf
COPY app ./app
COPY app-test ./app-test

# Build the application
RUN sbt clean compile stage

# Stage 2: Runtime stage
FROM sbtscala/scala-sbt:graalvm-ce-22.3.3-b1-java17_1.12.0_2.13.18

WORKDIR /app

# Install curl for health checks and bash for scripts
RUN #apk add --no-cache curl bash

# Copy the staged application from builder
COPY --from=builder /build/target/universal/stage .

# Copy entrypoint script
COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

# TODO fix this code for the new base image
# Create a non-root user for security
# Create a non-root user for security (Debian-based image)
RUN groupadd -g 1000 play && \
    useradd -u 1000 -g play -m -s /bin/bash play && \
    chown -R play:play /app

USER play

# Expose the default Play Framework port
EXPOSE 9000

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:9000/health || exit 1

# Run the application
ENTRYPOINT ["/app/docker-entrypoint.sh"]
