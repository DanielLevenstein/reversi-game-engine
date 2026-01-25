#!/bin/bash
set -e

echo "Starting Reversi Game Engine..."

# Run the application with necessary Java module options for Java 17+
exec java \
  --add-opens java.base/java.lang=ALL-UNNAMED \
  --add-opens java.base/java.lang.invoke=ALL-UNNAMED \
  -Dplay.server.provider=play.core.server.NettyServerProvider \
  -Dconfig.file=/app/conf/application.conf \
  -Dplay.server.pidfile.path=/dev/null \
  -cp "/app/lib/*:/app/conf" \
  play.core.server.ProdServerStart
