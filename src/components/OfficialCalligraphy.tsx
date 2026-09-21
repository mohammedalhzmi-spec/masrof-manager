import React from 'react';

export const BismillahCalligraphy: React.FC<{ className?: string }> = ({ className = 'text-base' }) => {
  return (
    <div className={`text-center font-['Amiri',serif] font-bold tracking-wide select-none ${className}`}>
      بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ
    </div>
  );
};

export const RepublicHeader: React.FC<{ className?: string }> = ({ className = '' }) => {
  return (
    <div className={`font-['Amiri',serif] font-bold text-lg select-none leading-tight ${className}`}>
      الجمهورية اليمنية
    </div>
  );
};

export const OfficialRubberStamp: React.FC<{
  branchName?: string;
  fundName?: string;
  size?: number;
  className?: string;
}> = ({
  branchName = 'فرع مديرية الحزم',
  fundName = 'صندوق النظافة والتحسين م/إب',
  size = 120,
  className = '',
}) => {
  return (
    <div
      className={`inline-block select-none opacity-85 hover:opacity-100 transition-opacity ${className}`}
      style={{ width: size, height: size }}
      title="الختم الرسمي المعتمد"
    >
      <svg viewBox="0 0 200 200" className="w-full h-full text-blue-900 drop-shadow-sm rotate-[-8deg]">
        {/* Outer Circle */}
        <circle cx="100" cy="100" r="94" fill="none" stroke="currentColor" strokeWidth="3.5" strokeDasharray="6 2" />
        <circle cx="100" cy="100" r="86" fill="none" stroke="currentColor" strokeWidth="1.5" />
        <circle cx="100" cy="100" r="60" fill="none" stroke="currentColor" strokeWidth="2" />
        
        {/* Curved Texts */}
        <path id="circlePathTop" d="M 20 100 A 80 80 0 0 1 180 100" fill="none" />
        <path id="circlePathBottom" d="M 180 100 A 80 80 0 0 1 20 100" fill="none" />
        
        <text fill="currentColor" fontSize="13" fontWeight="bold" fontFamily="'Amiri', 'Cairo', serif">
          <textPath href="#circlePathTop" startOffset="50%" textAnchor="middle">
            الجمهورية اليمنية • {fundName}
          </textPath>
        </text>
        
        <text fill="currentColor" fontSize="12" fontWeight="bold" fontFamily="'Amiri', 'Cairo', serif">
          <textPath href="#circlePathBottom" startOffset="50%" textAnchor="middle">
            {branchName} • الإدارة المالية
          </textPath>
        </text>

        {/* Center Emblem / Word */}
        <g transform="translate(70, 75)">
          <rect x="0" y="0" width="60" height="24" rx="4" fill="none" stroke="currentColor" strokeWidth="1.5" />
          <text x="30" y="16" textAnchor="middle" fill="currentColor" fontSize="13" fontWeight="900" fontFamily="'Cairo', serif">
            مـعـتـمـد
          </text>
        </g>
        
        {/* Star decorations */}
        <text x="25" y="104" fill="currentColor" fontSize="14" textAnchor="middle">★</text>
        <text x="175" y="104" fill="currentColor" fontSize="14" textAnchor="middle">★</text>
      </svg>
    </div>
  );
};
