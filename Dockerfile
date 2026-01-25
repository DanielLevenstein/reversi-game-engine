# Multi-stage build for optimized Docker image
# Stage 1: Test stage - Run all tests first
FROM eclipse-temurin:21-jdk-alpine AS tester

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
FROM eclipse-temurin:21-jdk-alpine AS builder

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
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Install curl for health checks and bash for scripts
RUN apk add --no-cache curl bash

# Copy the staged application from builder
COPY --from=builder /build/target/universal/stage .

# Copy entrypoint script
COPY docker-entrypoint.sh /app/docker-entrypoint.sh
RUN chmod +x /app/docker-entrypoint.sh

# Create a non-root user for security
RUN addgroup -g 1000 play && \
    adduser -D -u 1000 -G play play && \
    chown -R play:play /app

USER play

# Expose the default Play Framework port
EXPOSE 9000

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:9000/health || exit 1

# Run the application
ENTRYPOINT ["/app/docker-entrypoint.sh"]
