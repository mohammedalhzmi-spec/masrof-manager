import React, { useState } from 'react';
import { YEMEN_EMBLEM_BASE64, YEMEN_EMBLEM_PATH } from '../utils/emblemConstants';

interface YemenEmblemProps {
  className?: string;
  size?: number;
  customLogoUrl?: string;
}

export const YemenEmblem: React.FC<YemenEmblemProps> = ({
  className = 'w-24 h-14',
  size,
  customLogoUrl,
}) => {
  const [hasError, setHasError] = useState(false);
  const primarySrc = customLogoUrl || YEMEN_EMBLEM_PATH;
  const currentSrc = hasError ? YEMEN_EMBLEM_BASE64 : primarySrc;

  return (
    <div
      className={`inline-flex items-center justify-center select-none ${className}`}
      style={size ? { width: size, height: 'auto' } : undefined}
      title="شعار الجمهورية اليمنية الرسمي"
    >
      <img
        src={currentSrc}
        alt="شعار الجمهورية اليمنية"
        className="max-h-full max-w-full object-contain filter drop-shadow-xs select-none pointer-events-none"
        referrerPolicy="no-referrer"
        onError={() => {
          if (!hasError) {
            setHasError(true);
          }
        }}
      />
    </div>
  );
};
