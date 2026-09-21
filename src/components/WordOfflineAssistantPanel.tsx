import React, { useState, useRef, useEffect } from 'react';
import { Document } from '../types';
import { processOfflineAssistantCommand } from '../utils/offlineAssistant';
import {
  Bot,
  Zap,
  Send,
  X,
  Sparkles,
  WifiOff,
  CheckCircle2,
  HelpCircle,
  RotateCcw,
  ArrowDownCircle,
  Layers,
  FileText,
  Stamp,
  Coins,
} from 'lucide-react';

interface ChatMessage {
  id: string;
  sender: 'user' | 'assistant';
  text: string;
  actionTaken?: string;
  timestamp: string;
}

interface WordOfflineAssistantPanelProps {
  isOpen: boolean;
  onClose: () => void;
  currentDoc: Document;
  onApplyDocUpdate: (updatedDoc: Document, feedbackMessage: string) => void;
}

const QUICK_SUGGESTIONS = [
  'اجعل الكتابة في المستندات عريض',
  'أعد تفقيط المبلغ الحالي',
  'أضف ختم الاعتماد المعتمد',
  'أضف علامة مائية رسمية بالخلف',
  'اجعل الهوامش ضيقة',
  'أضف رمز الريال ﷼',
  'أضف مربع نص لملاحظات الصرف',
  'غير الهوامش إلى عريضة',
  'عدل المبلغ إلى 200,000 ريال',
];

export const WordOfflineAssistantPanel: React.FC<WordOfflineAssistantPanelProps> = ({
  isOpen,
  onClose,
  currentDoc,
  onApplyDocUpdate,
}) => {
  const [messages, setMessages] = useState<ChatMessage[]>([
    {
      id: 'welcome',
      sender: 'assistant',
      text: 'مرحباً بك! أنا مساعد المستندات الذكي المدمج في محرر وورد. أعمل بشكل فوري وبدون أي اتصال بالإنترنت (100% Offline). يمكنك إصدار الأوامر لتعديل المبالغ، التفقيط، الخط العريض، إضافة أختام، علامات مائية، مربعات نصوص، هوامش، أو أي بيانات للمستند وسأنفذها لحظياً!',
      timestamp: 'الآن',
    },
  ]);
  const [inputValue, setInputValue] = useState('');
  const [lastAction, setLastAction] = useState<string | null>(null);

  const messagesEndRef = useRef<HTMLDivElement>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (isOpen) {
      inputRef.current?.focus();
    }
  }, [isOpen]);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [messages]);

  if (!isOpen) return null;

  const handleSendCommand = (textToSend?: string) => {
    const commandText = (textToSend || inputValue).trim();
    if (!commandText) return;

    const userMsg: ChatMessage = {
      id: `user_${Date.now()}`,
      sender: 'user',
      text: commandText,
      timestamp: new Date().toLocaleTimeString('ar-YE', { hour: '2-digit', minute: '2-digit' }),
    };

    setMessages((prev) => [...prev, userMsg]);
    if (!textToSend) setInputValue('');

    // Process immediately using 100% Offline AI Rule Engine
    const result = processOfflineAssistantCommand(commandText, currentDoc);

    // Apply update to the document immediately
    onApplyDocUpdate(result.updatedDoc, result.message);
    setLastAction(result.actionTaken);

    const botMsg: ChatMessage = {
      id: `bot_${Date.now()}`,
      sender: 'assistant',
      text: result.message,
      actionTaken: result.actionTaken,
      timestamp: new Date().toLocaleTimeString('ar-YE', { hour: '2-digit', minute: '2-digit' }),
    };

    setMessages((prev) => [...prev, botMsg]);
  };

  return (
    <div className="w-80 md:w-96 bg-slate-900 border-l border-slate-800 flex flex-col h-full shrink-0 shadow-2xl z-30 animate-fade-in text-slate-100">
      {/* 1. Header with Offline Indicator */}
      <div className="p-3.5 bg-slate-950 border-b border-slate-800 flex items-center justify-between shrink-0">
        <div className="flex items-center gap-2.5">
          <div className="w-8 h-8 rounded-lg bg-gradient-to-br from-indigo-500 to-blue-600 flex items-center justify-center text-white shadow-md">
            <Bot className="w-5 h-5" />
          </div>
          <div>
            <div className="flex items-center gap-1.5">
              <h3 className="text-xs font-bold text-white">المساعد الذكي للمستندات</h3>
              <span className="flex items-center gap-1 px-1.5 py-0.5 rounded text-[10px] font-bold bg-emerald-950/80 text-emerald-300 border border-emerald-700/60">
                <Zap className="w-2.5 h-2.5 text-amber-400" />
                <span>فوري</span>
              </span>
            </div>
            <div className="flex items-center gap-1 text-[10px] text-emerald-400 font-medium">
              <WifiOff className="w-3 h-3 text-emerald-400" />
              <span>يعمل بدون اتصال إنترنت 100%</span>
            </div>
          </div>
        </div>

        <button
          onClick={onClose}
          className="p-1 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition"
          title="إغلاق نافذة المساعد"
        >
          <X className="w-4 h-4" />
        </button>
      </div>

      {/* 2. Messages List */}
      <div className="flex-1 overflow-y-auto p-3 space-y-3 text-xs">
        {messages.map((msg) => {
          const isAssistant = msg.sender === 'assistant';
          return (
            <div
              key={msg.id}
              className={`flex flex-col ${isAssistant ? 'items-start' : 'items-end'} space-y-1`}
            >
              <div className="flex items-center gap-1 text-[10px] text-slate-400">
                {isAssistant ? (
                  <>
                    <Bot className="w-3 h-3 text-blue-400" />
                    <span>مساعد المستند</span>
                  </>
                ) : (
                  <span>أنت</span>
                )}
                <span>• {msg.timestamp}</span>
              </div>

              <div
                className={`p-3 rounded-2xl max-w-[92%] leading-relaxed ${
                  isAssistant
                    ? 'bg-slate-800/90 text-slate-100 border border-slate-700 rounded-tr-xs'
                    : 'bg-blue-600 text-white font-medium rounded-tl-xs shadow-sm'
                }`}
              >
                <p className="whitespace-pre-wrap">{msg.text}</p>
                {msg.actionTaken && (
                  <div className="mt-2 pt-1.5 border-t border-slate-700/60 flex items-center gap-1 text-[10px] text-emerald-400 font-bold">
                    <CheckCircle2 className="w-3 h-3" />
                    <span>تم تطبيق التغيير فورياً على الورقة</span>
                  </div>
                )}
              </div>
            </div>
          );
        })}
        <div ref={messagesEndRef} />
      </div>

      {/* 3. Quick Suggestion Chips */}
      <div className="p-2.5 bg-slate-950/80 border-t border-slate-800/80 shrink-0">
        <div className="flex items-center justify-between mb-1.5">
          <span className="text-[10px] text-slate-400 flex items-center gap-1">
            <Sparkles className="w-3 h-3 text-amber-400" />
            <span>أوامر سريعة بنقرة واحدة:</span>
          </span>
          {lastAction && (
            <span className="text-[9px] text-emerald-400 font-bold">آخر إجراء: {lastAction}</span>
          )}
        </div>
        <div className="flex flex-wrap gap-1 max-h-24 overflow-y-auto p-0.5">
          {QUICK_SUGGESTIONS.map((sug) => (
            <button
              key={sug}
              type="button"
              onClick={() => handleSendCommand(sug)}
              className="text-[10px] px-2 py-1 rounded-md bg-slate-800 hover:bg-slate-700 text-slate-200 hover:text-white border border-slate-700 transition active:scale-95 text-right font-medium"
            >
              {sug}
            </button>
          ))}
        </div>
      </div>

      {/* 4. Input Bar */}
      <div className="p-3 bg-slate-950 border-t border-slate-800 shrink-0">
        <form
          onSubmit={(e) => {
            e.preventDefault();
            handleSendCommand();
          }}
          className="flex items-center gap-1.5"
        >
          <input
            ref={inputRef}
            type="text"
            value={inputValue}
            onChange={(e) => setInputValue(e.target.value)}
            placeholder="اكتب أمرك للمساعد (مثال: 'عدل المبلغ إلى 50000')..."
            className="flex-1 bg-slate-900 border border-slate-700 rounded-xl px-3 py-2 text-xs text-white placeholder-slate-500 focus:border-blue-500 focus:outline-hidden"
          />
          <button
            type="submit"
            disabled={!inputValue.trim()}
            className="p-2 rounded-xl bg-blue-600 hover:bg-blue-500 disabled:opacity-40 text-white font-bold transition active:scale-95 shadow-md flex items-center justify-center"
            title="إرسال الأمر للمساعد"
          >
            <Send className="w-4 h-4 rotate-180" />
          </button>
        </form>
        <p className="text-[10px] text-slate-500 text-center mt-1.5">
          المساعد مبرمج بنظام الذكاء المحلي للتعرف على الأرقام، الكلمات، والوثائق فورياً
        </p>
      </div>
    </div>
  );
};
