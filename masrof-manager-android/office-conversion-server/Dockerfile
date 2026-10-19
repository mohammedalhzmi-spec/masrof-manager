FROM node:22-bookworm-slim
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y --no-install-recommends libreoffice-writer libreoffice-calc libreoffice-impress libreoffice-draw poppler-utils tesseract-ocr tesseract-ocr-ara fonts-noto-core fonts-noto-cjk fonts-dejavu ca-certificates tini && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY package*.json ./
RUN npm ci --omit=dev
COPY src ./src
RUN useradd --create-home --uid 10001 converter && mkdir -p /tmp/masrof-conversions && chown -R converter:converter /app /tmp/masrof-conversions
USER converter
ENTRYPOINT ["/usr/bin/tini","--"]
