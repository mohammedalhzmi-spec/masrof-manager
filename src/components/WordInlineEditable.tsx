import React, { useRef, useEffect, useState } from 'react';

interface WordInlineEditableProps {
  value: string | number | undefined | null;
  onChange: (value: string) => void;
  placeholder?: string;
  className?: string;
  isEditable?: boolean;
  multiline?: boolean;
  dir?: 'rtl' | 'ltr' | 'auto';
  title?: string;
  numeric?: boolean;
  minWidth?: string;
  suffix?: string;
  prefix?: string;
}

/**
 * WordInlineEditable Component
 * Provides seamless 100% Word-like direct in-page editing on document sheets.
 * - Native caret, selection, and typing right on the document page.
 * - Prevents cursor jumping during React re-renders by maintaining local DOM synchronization.
 * - Displays subtle Word-like hover and focus borders when editing, but is 100% clean and transparent when printing.
 * - Auto-saves on blur and input, remembering every keystroke.
 */
export const WordInlineEditable: React.FC<WordInlineEditableProps> = ({
  value,
  onChange,
  placeholder = '...........................',
  className = '',
  isEditable = false,
  multiline = false,
  dir = 'rtl',
  title = 'انقر للكتابة والتعديل المباشر على الورقة',
  numeric = false,
  minWidth = '40px',
  suffix = '',
  prefix = '',
}) => {
  const spanRef = useRef<HTMLSpanElement>(null);
  const isFocusedRef = useRef<boolean>(false);
  const [isEmpty, setIsEmpty] = useState<boolean>(!value && value !== 0);

  // Synchronize text when value changes from external sources (e.g. Assistant or Quick Fields),
  // but ONLY if this specific field is not currently focused and active to protect cursor position.
  useEffect(() => {
    if (spanRef.current && !isFocusedRef.current) {
      const displayVal = value !== undefined && value !== null ? String(value) : '';
      if (spanRef.current.innerText !== displayVal) {
        spanRef.current.innerText = displayVal;
      }
      setIsEmpty(displayVal.trim().length === 0);
    }
  }, [value]);

  // Non-editable mode (e.g. for official print/export or read-only view)
  if (!isEditable) {
    const displayVal = value !== undefined && value !== null && String(value).trim().length > 0
      ? String(value)
      : placeholder;
    return (
      <span className={`inline-block ${className}`} dir={dir}>
        {prefix}
        {displayVal}
        {suffix}
      </span>
    );
  }

  const handleFocus = () => {
    isFocusedRef.current = true;
  };

  const handleBlur = (e: React.FocusEvent<HTMLSpanElement>) => {
    isFocusedRef.current = false;
    let text = e.currentTarget.innerText.trim();
    if (numeric) {
      // Clean up number
      const digits = text.replace(/[^\d.]/g, '');
      text = digits;
    }
    setIsEmpty(text.length === 0);
    onChange(text);
  };

  const handleInput = (e: React.FormEvent<HTMLSpanElement>) => {
    const text = e.currentTarget.innerText;
    setIsEmpty(text.trim().length === 0);
    // Real-time broadcast for autosaving
    onChange(text);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLSpanElement>) => {
    if (!multiline && e.key === 'Enter') {
      e.preventDefault();
      spanRef.current?.blur();
    }
  };

  return (
    <span
      className={`relative inline-flex items-center group/editable ${className}`}
      dir={dir}
      title={title}
    >
      {prefix && <span className="shrink-0 select-none mr-1">{prefix}</span>}

      <span
        ref={spanRef}
        contentEditable={isEditable}
        suppressContentEditableWarning
        onFocus={handleFocus}
        onBlur={handleBlur}
        onInput={handleInput}
        onKeyDown={handleKeyDown}
        data-placeholder={placeholder}
        style={{ minWidth }}
        className={`outline-none transition-all duration-150 rounded-xs select-text break-words ${
          isEditable
            ? 'cursor-text hover:bg-blue-50/70 hover:ring-1 hover:ring-blue-400/80 focus:bg-blue-50/90 focus:ring-2 focus:ring-blue-500 focus:shadow-xs px-1 py-0.5'
            : ''
        } ${isEmpty && isEditable ? 'text-slate-400 font-normal italic' : ''}`}
      >
        {value !== undefined && value !== null && String(value).length > 0
          ? String(value)
          : isEditable
          ? ''
          : placeholder}
      </span>

      {/* Subtle Word placeholder when empty in editable mode */}
      {isEmpty && isEditable && (
        <span
          onClick={() => {
            spanRef.current?.focus();
          }}
          className="absolute inset-y-0 right-1 flex items-center text-slate-400/90 pointer-events-none select-none text-xs font-normal"
        >
          {placeholder}
        </span>
      )}

      {suffix && <span className="shrink-0 select-none ml-1">{suffix}</span>}
    </span>
  );
};
