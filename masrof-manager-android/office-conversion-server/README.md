# Masrof Office Conversion Server

خادم تحويل محلي/خارجي باستخدام LibreOffice Headless. يدعم DOC/DOCX/XLS/XLSX/ODT/ODS/PDF والصيغ الشائعة، مع Queue عبر Redis وتخزين ملفات MinIO وحذف تلقائي بعد مدة الصلاحية.

## التشغيل

```bash
cp .env.example .env
# غيّر API_TOKEN قبل النشر
npm install
npm run dev
# أو التشغيل الكامل
API_TOKEN='ضع-رمزًا-طويلًا' docker compose up -d --build
# المراقبة الاختيارية
API_TOKEN='ضع-رمزًا-طويلًا' docker compose --profile monitoring up -d --build
```

التحقق:

```bash
curl http://localhost:8088/healthz
curl -H 'Authorization: Bearer TOKEN' -F file=@document.docx -F targetFormat=pdf http://localhost:8088/api/conversions
```

بعد إنشاء المهمة، استعلم عن الحالة باستخدام `GET /api/conversions/{id}` ثم نزّلها من `GET /api/conversions/{id}/download`.

## نموذج الأمان

- Worker يعمل كمستخدم غير إداري داخل حاوية.
- حد الرفع الافتراضي 100 MB وحد التحويل 180 ثانية.
- LibreOffice يستخدم profile منفصلًا لكل مهمة.
- لا يتم تنفيذ الملفات المرفوعة، ولا يتم السماح بـ macros.
- يجب وضع API خلف HTTPS وReverse Proxy في الإنتاج.
- غيّر كلمات MinIO وAPI token، ولا تفتح Redis أو MinIO للعامة.
- ضع Worker في شبكة بلا خروج إنترنت عند نشره في بيئة حساسة.

## سياسة التحويل

التحويل DOCX/XLSX إلى PDF عادةً يحافظ على التخطيط بدرجة عالية. PDF إلى DOCX يعتمد على طبقة النص؛ PDF الممسوح يحتاج OCR وقد تتغير الأشكال والجداول العائمة. API يعيد `warnings` حتى لا يخفي هذه الفروقات عن المستخدم.

## التكامل مع Android

استخدم عنوان API في إعداد التطبيق، وارفع الملف عند اختيار "تحويل عبر الإنترنت". بعد نجاح المهمة نزّل الناتج واحفظه في `LocalOfficeDocumentStore`. لا تمنع فتح النسخة المحلية عند انقطاع الشبكة؛ التحويل والمزامنة عملية مؤجلة.
