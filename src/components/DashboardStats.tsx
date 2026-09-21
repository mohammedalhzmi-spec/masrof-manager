import React from 'react';
import {
  Coins,
  FileCheck2,
  FileSpreadsheet,
  Receipt,
  TrendingUp,
  Clock,
} from 'lucide-react';
import { Document } from '../types';

interface DashboardStatsProps {
  documents: Document[];
}

export const DashboardStats: React.FC<DashboardStatsProps> = ({ documents }) => {
  const totalAmount = documents.reduce((sum, doc) => sum + (doc.amount || 0), 0);
  const ordersCount = documents.filter((d) => d.type === 'ORDER').length;
  const requestsCount = documents.filter((d) => d.type === 'REQUEST').length;
  const receiptsCount = documents.filter((d) => d.type === 'RECEIPT').length;
  const approvedCount = documents.filter((d) => d.status === 'APPROVED' || d.status === 'PAID').length;

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4 mb-6">
      {/* Total Amount Disbursed */}
      <div
        id="stat-total-amount"
        className="bg-white rounded-2xl p-5 border border-slate-200/80 shadow-sm hover:shadow-md transition relative overflow-hidden"
      >
        <div className="flex items-center justify-between">
          <div>
            <p className="text-xs font-semibold text-slate-500 mb-1">
              إجمالي المبالغ المصروفة والمطلوبة
            </p>
            <h3 className="text-2xl font-black text-slate-900 tracking-tight">
              {totalAmount.toLocaleString('ar-YE')}{' '}
              <span className="text-xs font-bold text-emerald-600">ريال يمني</span>
            </h3>
          </div>
          <div className="w-12 h-12 rounded-xl bg-emerald-50 text-emerald-600 flex items-center justify-center border border-emerald-100">
            <Coins className="w-6 h-6" />
          </div>
        </div>
        <div className="mt-3 flex items-center gap-1.5 text-xs text-slate-500">
          <TrendingUp className="w-3.5 h-3.5 text-emerald-600" />
          <span>يشمل جميع أوامر وطلبات الصرف المسجلة</span>
        </div>
      </div>

      {/* Orders Count */}
      <div
        id="stat-orders-count"
        className="bg-white rounded-2xl p-5 border border-slate-200/80 shadow-sm hover:shadow-md transition"
      >
        <div className="flex items-center justify-between">
          <div>
            <p className="text-xs font-semibold text-slate-500 mb-1">أوامر الصرف الرسمية</p>
            <h3 className="text-2xl font-black text-slate-900 tracking-tight">
              {ordersCount}{' '}
              <span className="text-xs font-bold text-slate-500">أمر صرف</span>
            </h3>
          </div>
          <div className="w-12 h-12 rounded-xl bg-blue-50 text-blue-600 flex items-center justify-center border border-blue-100">
            <FileSpreadsheet className="w-6 h-6" />
          </div>
        </div>
        <div className="mt-3 flex items-center gap-1.5 text-xs text-slate-500">
          <Clock className="w-3.5 h-3.5 text-blue-600" />
          <span>أوامر معتمدة وجاهزة للصرف المالي</span>
        </div>
      </div>

      {/* Requests Count */}
      <div
        id="stat-requests-count"
        className="bg-white rounded-2xl p-5 border border-slate-200/80 shadow-sm hover:shadow-md transition"
      >
        <div className="flex items-center justify-between">
          <div>
            <p className="text-xs font-semibold text-slate-500 mb-1">طلبات الصرف المقدمة</p>
            <h3 className="text-2xl font-black text-slate-900 tracking-tight">
              {requestsCount}{' '}
              <span className="text-xs font-bold text-slate-500">طلب</span>
            </h3>
          </div>
          <div className="w-12 h-12 rounded-xl bg-amber-50 text-amber-600 flex items-center justify-center border border-amber-100">
            <FileCheck2 className="w-6 h-6" />
          </div>
        </div>
        <div className="mt-3 flex items-center gap-1.5 text-xs text-slate-500">
          <span>طلبات مرفوعة لمدير عام الصندوق</span>
        </div>
      </div>

      {/* Receipts Count */}
      <div
        id="stat-receipts-count"
        className="bg-white rounded-2xl p-5 border border-slate-200/80 shadow-sm hover:shadow-md transition"
      >
        <div className="flex items-center justify-between">
          <div>
            <p className="text-xs font-semibold text-slate-500 mb-1">سندات وأوراق الاستلام</p>
            <h3 className="text-2xl font-black text-slate-900 tracking-tight">
              {receiptsCount}{' '}
              <span className="text-xs font-bold text-slate-500">سند قبض</span>
            </h3>
          </div>
          <div className="w-12 h-12 rounded-xl bg-purple-50 text-purple-600 flex items-center justify-center border border-purple-100">
            <Receipt className="w-6 h-6" />
          </div>
        </div>
        <div className="mt-3 flex items-center gap-1.5 text-xs text-emerald-600 font-semibold">
          <span>{approvedCount} مستندات مكتملة ومعتمدة</span>
        </div>
      </div>
    </div>
  );
};
