'use strict';

const crypto = require('node:crypto');

function normalizeIdentifier(value) {
  return String(value ?? '').trim().toLocaleLowerCase('en-US');
}

function aliasDocumentIds(identifier) {
  const normalized = normalizeIdentifier(identifier);
  return [...new Set([
    normalized.replaceAll('/', '_'),
    normalized.replaceAll(':', '_'),
    normalized.replaceAll('@', '_'),
    normalized,
  ])];
}

function decodeBase64(value) {
  if (typeof value !== 'string' || value.length === 0 || value.length > 4096) {
    throw new Error('Invalid base64 input');
  }
  const decoded = Buffer.from(value, 'base64');
  if (decoded.length === 0 || decoded.toString('base64') !== value) {
    throw new Error('Invalid base64 input');
  }
  return decoded;
}

function publicKeyInfo(publicKeyBase64) {
  const der = decodeBase64(publicKeyBase64);
  const keyObject = crypto.createPublicKey({ key: der, format: 'der', type: 'spki' });
  const curve = keyObject.asymmetricKeyDetails?.namedCurve;
  if (keyObject.asymmetricKeyType !== 'ec' || !['prime256v1', 'secp256r1'].includes(curve)) {
    throw new Error('Expected a P-256 EC public key');
  }
  return {
    der,
    keyObject,
    hash: crypto.createHash('sha256').update(der).digest('hex'),
  };
}

function verifyChallengeSignature(publicKeyBase64, challengeBase64, signatureBase64) {
  try {
    const { keyObject } = publicKeyInfo(publicKeyBase64);
    const challenge = decodeBase64(challengeBase64);
    const signature = decodeBase64(signatureBase64);
    return crypto.verify('sha256', challenge, keyObject, signature);
  } catch (_error) {
    return false;
  }
}

function isValidDeviceId(value) {
  return typeof value === 'string' && /^[A-Za-z0-9._-]{1,128}$/.test(value);
}

function buildChallenge({ uid, deviceId, publicKeyHash, expiresAtMs, nonce }) {
  const payload = {
    version: 1,
    uid,
    deviceId,
    publicKeyHash,
    expiresAtMs,
    nonce,
  };
  return Buffer.from(JSON.stringify(payload), 'utf8');
}

module.exports = {
  aliasDocumentIds,
  buildChallenge,
  isValidDeviceId,
  normalizeIdentifier,
  publicKeyInfo,
  verifyChallengeSignature,
};
