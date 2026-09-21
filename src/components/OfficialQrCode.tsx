import React, { useEffect, useState } from 'react';
import QRCode from 'qrcode';

interface OfficialQrCodeProps {
  value: string;
  size?: number;
  className?: string;
}

export const OfficialQrCode: React.FC<OfficialQrCodeProps> = ({
  value,
  size = 72,
  className = '',
}) => {
  const [dataUrl, setDataUrl] = useState<string>('');

  useEffect(() => {
    let isMounted = true;
    QRCode.toDataURL(
      value,
      {
        width: size * 2,
        margin: 1,
        color: {
          dark: '#000000',
          light: '#ffffff',
        },
      },
      (err, url) => {
        if (!err && isMounted && url) {
          setDataUrl(url);
        }
      }
    );

    return () => {
      isMounted = false;
    };
  }, [value, size]);

  if (!dataUrl) {
    return (
      <div
        className={`bg-slate-100 border border-slate-300 flex items-center justify-center text-[9px] text-slate-500 font-mono ${className}`}
        style={{ width: size, height: size }}
      >
        QR CODE
      </div>
    );
  }

  return (
    <div
      className={`inline-block border border-slate-800 p-0.5 bg-white shadow-xs ${className}`}
      style={{ width: size + 4, height: size + 4 }}
      title="رمز التحقق الإلكتروني للوثيقة"
    >
      <img
        src={dataUrl}
        alt="QR Code"
        width={size}
        height={size}
        className="w-full h-full block"
      />
    </div>
  );
};
