import React, { useRef, useState, useEffect } from 'react';
import {
  CanvasElement,
} from '../types';
import {
  X,
  Move,
  Layers,
  Maximize2,
  Trash2,
  RotateCw,
  Bold,
  Eye,
  ArrowUp,
  ArrowDown,
  ArrowRight,
  ArrowLeft,
  Stamp,
  ShieldCheck,
  CheckCircle2,
  AlertCircle,
  Coins,
  Building2,
  PhoneCall,
  Calendar,
  Landmark,
  Lock,
  FileCheck,
} from 'lucide-react';

interface CanvasElementsLayerProps {
  elements: CanvasElement[];
  isEditable: boolean;
  activeElementId: string | null;
  onSelectElement: (id: string | null) => void;
  onUpdateElement: (id: string, updates: Partial<CanvasElement>) => void;
  onDeleteElement: (id: string) => void;
  layer: 'background' | 'foreground';
}

export const getIconComponent = (name?: string, className?: string) => {
  const props = { className: className || 'w-6 h-6' };
  switch (name) {
    case 'stamp':
      return <Stamp {...props} />;
    case 'shield':
      return <ShieldCheck {...props} />;
    case 'check':
      return <CheckCircle2 {...props} />;
    case 'alert':
      return <AlertCircle {...props} />;
    case 'coins':
      return <Coins {...props} />;
    case 'building':
      return <Building2 {...props} />;
    case 'phone':
      return <PhoneCall {...props} />;
    case 'calendar':
      return <Calendar {...props} />;
    case 'landmark':
      return <Landmark {...props} />;
    case 'lock':
      return <Lock {...props} />;
    case 'fileCheck':
    default:
      return <FileCheck {...props} />;
  }
};

export const CanvasElementsLayer: React.FC<CanvasElementsLayerProps> = ({
  elements,
  isEditable,
  activeElementId,
  onSelectElement,
  onUpdateElement,
  onDeleteElement,
  layer,
}) => {
  const filteredElements = elements.filter((el) => el.layer === layer);
  const [draggingId, setDraggingId] = useState<string | null>(null);
  const [resizingInfo, setResizingInfo] = useState<{
    id: string;
    handle: 'se' | 'sw' | 'ne' | 'nw';
    startX: number;
    startY: number;
    startW: number;
    startH: number;
    startElX: number;
    startElY: number;
  } | null>(null);

  const dragStartRef = useRef<{ startX: number; startY: number; elX: number; elY: number } | null>(
    null
  );

  // Mouse & Touch Dragging Handlers
  const handleMouseDown = (e: React.MouseEvent, el: CanvasElement) => {
    if (!isEditable) return;
    e.stopPropagation();
    onSelectElement(el.id);
    setDraggingId(el.id);
    dragStartRef.current = {
      startX: e.clientX,
      startY: e.clientY,
      elX: el.x,
      elY: el.y,
    };
  };

  const handleTouchStart = (e: React.TouchEvent, el: CanvasElement) => {
    if (!isEditable || e.touches.length !== 1) return;
    e.stopPropagation();
    const touch = e.touches[0];
    onSelectElement(el.id);
    setDraggingId(el.id);
    dragStartRef.current = {
      startX: touch.clientX,
      startY: touch.clientY,
      elX: el.x,
      elY: el.y,
    };
  };

  // Resize Corner Handle Handlers
  const handleResizeHandleMouseDown = (
    e: React.MouseEvent,
    el: CanvasElement,
    handle: 'se' | 'sw' | 'ne' | 'nw'
  ) => {
    if (!isEditable) return;
    e.stopPropagation();
    e.preventDefault();
    setResizingInfo({
      id: el.id,
      handle,
      startX: e.clientX,
      startY: e.clientY,
      startW: el.width,
      startH: el.height || 60,
      startElX: el.x,
      startElY: el.y,
    });
  };

  const handleResizeHandleTouchStart = (
    e: React.TouchEvent,
    el: CanvasElement,
    handle: 'se' | 'sw' | 'ne' | 'nw'
  ) => {
    if (!isEditable || e.touches.length !== 1) return;
    e.stopPropagation();
    const touch = e.touches[0];
    setResizingInfo({
      id: el.id,
      handle,
      startX: touch.clientX,
      startY: touch.clientY,
      startW: el.width,
      startH: el.height || 60,
      startElX: el.x,
      startElY: el.y,
    });
  };

  // Movement & Resize Window Event Listeners
  useEffect(() => {
    const handlePointerMove = (clientX: number, clientY: number) => {
      // 1. Moving Element
      if (draggingId && dragStartRef.current) {
        const dx = clientX - dragStartRef.current.startX;
        const dy = clientY - dragStartRef.current.startY;
        const newX = Math.max(0, dragStartRef.current.elX + dx);
        const newY = Math.max(0, dragStartRef.current.elY + dy);
        onUpdateElement(draggingId, { x: Math.round(newX), y: Math.round(newY) });
        return;
      }

      // 2. Resizing Element via Corner Handle
      if (resizingInfo) {
        const dx = clientX - resizingInfo.startX;
        const dy = clientY - resizingInfo.startY;

        let newW = resizingInfo.startW;
        let newH = resizingInfo.startH;
        let newX = resizingInfo.startElX;
        let newY = resizingInfo.startElY;

        if (resizingInfo.handle === 'se') {
          newW = Math.max(30, resizingInfo.startW + dx);
          newH = Math.max(20, resizingInfo.startH + dy);
        } else if (resizingInfo.handle === 'sw') {
          newW = Math.max(30, resizingInfo.startW - dx);
          newH = Math.max(20, resizingInfo.startH + dy);
          newX = Math.max(0, resizingInfo.startElX + dx);
        } else if (resizingInfo.handle === 'ne') {
          newW = Math.max(30, resizingInfo.startW + dx);
          newH = Math.max(20, resizingInfo.startH - dy);
          newY = Math.max(0, resizingInfo.startElY + dy);
        } else if (resizingInfo.handle === 'nw') {
          newW = Math.max(30, resizingInfo.startW - dx);
          newH = Math.max(20, resizingInfo.startH - dy);
          newX = Math.max(0, resizingInfo.startElX + dx);
          newY = Math.max(0, resizingInfo.startElY + dy);
        }

        onUpdateElement(resizingInfo.id, {
          width: Math.round(newW),
          height: Math.round(newH),
          x: Math.round(newX),
          y: Math.round(newY),
        });
      }
    };

    const onMouseMove = (e: MouseEvent) => {
      handlePointerMove(e.clientX, e.clientY);
    };

    const onTouchMove = (e: TouchEvent) => {
      if (e.touches.length === 1) {
        handlePointerMove(e.touches[0].clientX, e.touches[0].clientY);
      }
    };

    const handlePointerUp = () => {
      setDraggingId(null);
      dragStartRef.current = null;
      setResizingInfo(null);
    };

    if (draggingId || resizingInfo) {
      window.addEventListener('mousemove', onMouseMove);
      window.addEventListener('mouseup', handlePointerUp);
      window.addEventListener('touchmove', onTouchMove, { passive: false });
      window.addEventListener('touchend', handlePointerUp);
    }
    return () => {
      window.removeEventListener('mousemove', onMouseMove);
      window.removeEventListener('mouseup', handlePointerUp);
      window.removeEventListener('touchmove', onTouchMove);
      window.removeEventListener('touchend', handlePointerUp);
    };
  }, [draggingId, resizingInfo, onUpdateElement]);

  if (filteredElements.length === 0) return null;

  return (
    <div
      className={`absolute inset-0 overflow-hidden ${
        layer === 'background' ? 'z-0 pointer-events-auto' : 'z-30 pointer-events-none'
      }`}
    >
      {filteredElements.map((el) => {
        const isSelected = isEditable && activeElementId === el.id;

        return (
          <div
            key={el.id}
            id={`canvas-elem-${el.id}`}
            onMouseDown={(e) => handleMouseDown(e, el)}
            onTouchStart={(e) => handleTouchStart(e, el)}
            style={{
              position: 'absolute',
              left: `${el.x}px`,
              top: `${el.y}px`,
              width: `${el.width}px`,
              height: el.height ? `${el.height}px` : 'auto',
              opacity: el.opacity ?? 1,
              transform: el.rotation ? `rotate(${el.rotation}deg)` : undefined,
              zIndex: isSelected ? 40 : layer === 'background' ? 1 : 35,
              pointerEvents: isEditable ? 'auto' : 'none',
              cursor: isEditable ? 'move' : 'default',
            }}
            className={`transition-shadow select-none ${
              isSelected ? 'ring-2 ring-blue-500 ring-offset-1 rounded-xs' : ''
            }`}
          >
            {/* 1. Image Element */}
            {el.type === 'image' && (
              <div className="w-full h-full relative group">
                <img
                  src={el.content}
                  alt="مرفق صورة في المستند"
                  className="w-full h-full object-contain pointer-events-none"
                  style={{
                    borderRadius: el.borderRadius ? `${el.borderRadius}px` : 0,
                  }}
                  crossOrigin="anonymous"
                />
              </div>
            )}

            {/* 2. Textbox Element */}
            {el.type === 'textbox' && (
              <div
                style={{
                  backgroundColor: el.backgroundColor || '#f8fafc',
                  color: el.color || '#000000',
                  borderColor: el.borderColor || '#cbd5e1',
                  borderWidth: `${el.borderWidth ?? 1}px`,
                  borderStyle: el.borderStyle || 'solid',
                  borderRadius: `${el.borderRadius ?? 6}px`,
                  fontSize: `${el.fontSize ?? 13}px`,
                  fontWeight: el.isBold ? 800 : 600,
                  padding: '8px 12px',
                  width: '100%',
                  height: '100%',
                }}
                className="overflow-hidden shadow-xs flex flex-col justify-start"
              >
                {isEditable ? (
                  <div
                    contentEditable
                    suppressContentEditableWarning
                    onBlur={(e) => onUpdateElement(el.id, { content: e.currentTarget.innerText })}
                    className="w-full h-full focus:outline-hidden font-['Cairo',sans-serif] leading-relaxed cursor-text"
                  >
                    {el.content}
                  </div>
                ) : (
                  <div className="w-full h-full font-['Cairo',sans-serif] leading-relaxed">
                    {el.content}
                  </div>
                )}
              </div>
            )}

            {/* 3. Shape Element */}
            {el.type === 'shape' && (
              <div
                style={{
                  width: '100%',
                  height: '100%',
                  backgroundColor: el.backgroundColor || 'transparent',
                  borderColor: el.borderColor || '#0284c7',
                  borderWidth: `${el.borderWidth ?? 2}px`,
                  borderStyle: el.borderStyle || 'solid',
                  borderRadius:
                    el.shapeType === 'circle'
                      ? '50%'
                      : el.shapeType === 'rounded'
                      ? '16px'
                      : el.shapeType === 'divider'
                      ? '0px'
                      : `${el.borderRadius ?? 4}px`,
                  color: el.color || '#000000',
                }}
                className={`flex items-center justify-center p-2 text-center font-bold ${
                  el.shapeType === 'divider' ? 'border-b-4 border-t-0 border-l-0 border-r-0' : ''
                }`}
              >
                {el.content && (
                  <span
                    style={{
                      fontSize: `${el.fontSize ?? 12}px`,
                      fontWeight: el.isBold ? 800 : 600,
                    }}
                    className="select-none"
                  >
                    {el.content}
                  </span>
                )}
              </div>
            )}

            {/* 4. Symbol Element */}
            {el.type === 'symbol' && (
              <div
                style={{
                  fontSize: `${el.fontSize ?? 24}px`,
                  color: el.color || '#1e293b',
                  fontWeight: el.isBold ? 900 : 700,
                }}
                className="w-full h-full flex items-center justify-center font-serif leading-none"
              >
                {el.content}
              </div>
            )}

            {/* 5. Icon Element */}
            {el.type === 'icon' && (
              <div
                style={{
                  color: el.color || '#0284c7',
                }}
                className="w-full h-full flex items-center justify-center"
              >
                {getIconComponent(el.iconName || el.content, 'w-full h-full object-contain')}
              </div>
            )}

            {/* 4 Corner Resize Handles like Microsoft Word */}
            {isSelected && (
              <>
                <div
                  onMouseDown={(e) => handleResizeHandleMouseDown(e, el, 'ne')}
                  onTouchStart={(e) => handleResizeHandleTouchStart(e, el, 'ne')}
                  className="absolute -top-1.5 -right-1.5 w-3.5 h-3.5 bg-white border-2 border-blue-600 rounded-full cursor-nesw-resize z-50 shadow-sm hover:scale-125 transition-transform"
                  title="سحب لتغيير الحجم"
                />
                <div
                  onMouseDown={(e) => handleResizeHandleMouseDown(e, el, 'nw')}
                  onTouchStart={(e) => handleResizeHandleTouchStart(e, el, 'nw')}
                  className="absolute -top-1.5 -left-1.5 w-3.5 h-3.5 bg-white border-2 border-blue-600 rounded-full cursor-nwse-resize z-50 shadow-sm hover:scale-125 transition-transform"
                  title="سحب لتغيير الحجم"
                />
                <div
                  onMouseDown={(e) => handleResizeHandleMouseDown(e, el, 'se')}
                  onTouchStart={(e) => handleResizeHandleTouchStart(e, el, 'se')}
                  className="absolute -bottom-1.5 -right-1.5 w-3.5 h-3.5 bg-white border-2 border-blue-600 rounded-full cursor-nwse-resize z-50 shadow-sm hover:scale-125 transition-transform"
                  title="سحب لتغيير الحجم"
                />
                <div
                  onMouseDown={(e) => handleResizeHandleMouseDown(e, el, 'sw')}
                  onTouchStart={(e) => handleResizeHandleTouchStart(e, el, 'sw')}
                  className="absolute -bottom-1.5 -left-1.5 w-3.5 h-3.5 bg-white border-2 border-blue-600 rounded-full cursor-nesw-resize z-50 shadow-sm hover:scale-125 transition-transform"
                  title="سحب لتغيير الحجم"
                />
              </>
            )}

            {/* Selection HUD Toolbar when element is actively selected */}
            {isSelected && (
              <div
                onMouseDown={(e) => e.stopPropagation()}
                className="absolute -top-10 right-0 bg-slate-900 text-white rounded-lg shadow-xl px-2 py-1 flex items-center gap-1.5 text-[11px] font-bold no-print z-50 border border-slate-700 whitespace-nowrap"
              >
                {/* Layer toggle */}
                <button
                  type="button"
                  onClick={() =>
                    onUpdateElement(el.id, {
                      layer: el.layer === 'background' ? 'foreground' : 'background',
                    })
                  }
                  className="px-1.5 py-0.5 rounded hover:bg-slate-800 text-blue-300 flex items-center gap-1"
                  title="تغيير الطبقة: خلف النص / أمام النص"
                >
                  <Layers className="w-3 h-3" />
                  <span>{el.layer === 'background' ? 'خلف النص' : 'أمام النص'}</span>
                </button>

                <div className="w-[1px] h-3 bg-slate-700"></div>

                {/* Resize + / - */}
                <button
                  type="button"
                  onClick={() =>
                    onUpdateElement(el.id, {
                      width: Math.max(30, Math.round(el.width * 1.15)),
                      height: el.height ? Math.max(30, Math.round(el.height * 1.15)) : el.height,
                    })
                  }
                  className="px-1 py-0.5 rounded hover:bg-slate-800 text-slate-200"
                  title="تكبير"
                >
                  +
                </button>
                <button
                  type="button"
                  onClick={() =>
                    onUpdateElement(el.id, {
                      width: Math.max(30, Math.round(el.width * 0.85)),
                      height: el.height ? Math.max(30, Math.round(el.height * 0.85)) : el.height,
                    })
                  }
                  className="px-1 py-0.5 rounded hover:bg-slate-800 text-slate-200"
                  title="تصغير"
                >
                  -
                </button>

                <div className="w-[1px] h-3 bg-slate-700"></div>

                {/* Opacity presets */}
                <button
                  type="button"
                  onClick={() =>
                    onUpdateElement(el.id, {
                      opacity: el.opacity <= 0.3 ? 1 : Math.max(0.15, el.opacity - 0.25),
                    })
                  }
                  className="px-1.5 py-0.5 rounded hover:bg-slate-800 text-amber-300 flex items-center gap-1"
                  title="ضبط الشفافية"
                >
                  <Eye className="w-3 h-3" />
                  <span>{Math.round((el.opacity ?? 1) * 100)}%</span>
                </button>

                <div className="w-[1px] h-3 bg-slate-700"></div>

                {/* Delete */}
                <button
                  type="button"
                  onClick={() => onDeleteElement(el.id)}
                  className="px-1.5 py-0.5 rounded hover:bg-red-900/60 text-red-400 hover:text-red-300"
                  title="حذف العنصر"
                >
                  <Trash2 className="w-3 h-3" />
                </button>

                <button
                  type="button"
                  onClick={() => onSelectElement(null)}
                  className="p-0.5 rounded hover:bg-slate-800 text-slate-400 hover:text-white"
                  title="إلغاء التحديد"
                >
                  <X className="w-3 h-3" />
                </button>
              </div>
            )}
          </div>
        );
      })}
    </div>
  );
};
