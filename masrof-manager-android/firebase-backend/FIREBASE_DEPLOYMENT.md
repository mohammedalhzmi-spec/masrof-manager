# نشر Firebase لتطبيق Masrof Manager Android

## ملفات التهيئة المنشأة

- `.firebaserc` يحدد المشروع `masrof-manager`.
- `functions/.env.masrof-manager` يحتوي على `FIREBASE_WEB_API_KEY` المطابق لملف Android `google-services.json`.

ملف البيئة مستبعد من Git عبر `firebase-backend/.gitignore` ولا يجب رفعه إلى المستودع.

## المتطلبات قبل النشر

1. حساب Google لديه صلاحية Firebase Owner أو Firebase Admin على مشروع `masrof-manager`.
2. تفعيل مشروع Firebase وربطه بمشروع Google Cloud.
3. إنشاء Firestore Database في نفس المشروع، ويفضل اختيار المنطقة المناسبة قبل أول استخدام.
4. تفعيل Firebase Authentication ثم مزود **Email/Password**.
5. تفعيل Cloud Functions ومتطلبات الفوترة المطلوبة من Google Cloud، إن طلبها المشروع.
6. التأكد من أن Node.js 22 متاح محليًا.
7. وجود الحسابات في Firebase Authentication وملفات `users/{uid}` في Firestore، مع:
   - `active: true`
   - `role: SYSTEM_ADMIN` أو دور صحيح مطابق للقواعد.

## تثبيت Firebase CLI

استخدم أحد الخيارين:

```bash
npm install -g firebase-tools
firebase --version
```

أو دون تثبيت عالمي:

```bash
npx firebase-tools@latest --version
```

## تسجيل الدخول واختيار المشروع

نفّذ الأوامر من مجلد `firebase-backend`، وليس من جذر المستودع:

```bash
cd /path/to/masrof-manager/masrof-manager-android/firebase-backend
firebase login
firebase projects:list
firebase use masrof-manager
```

للتحقق من المشروع الحالي:

```bash
firebase use
cat .firebaserc
```

إذا استخدمت `npx`، استبدل `firebase` في الأوامر بـ `npx firebase-tools@latest`.

## فحوص محلية قبل النشر

```bash
cd /path/to/masrof-manager/masrof-manager-android/firebase-backend
npm --prefix functions ci --no-audit --no-fund
npm --prefix functions test
npm --prefix functions run check
```

اختبار وجود ملف البيئة دون طباعة قيمة المفتاح:

```bash
test -s functions/.env.masrof-manager
awk -F= '/^FIREBASE_WEB_API_KEY=/{print ($2 != "" ? "FIREBASE_WEB_API_KEY is set" : "FIREBASE_WEB_API_KEY is empty")}' functions/.env.masrof-manager
```

## النشر الأول

انشر Functions وFirestore معًا:

```bash
cd /path/to/masrof-manager/masrof-manager-android/firebase-backend
firebase deploy --project masrof-manager --config firebase.json --only functions,firestore
```

أو انشرهما على مرحلتين لتسهيل تشخيص الأخطاء:

```bash
firebase deploy --project masrof-manager --config firebase.json --only functions
firebase deploy --project masrof-manager --config firebase.json --only firestore
```

## التحقق بعد النشر

اعرض الوظائف المنشورة:

```bash
firebase functions:list --project masrof-manager
```

ينبغي ظهور الوظائف التالية في المنطقة `us-central1`:

```text
beginCloudLogin
completeCloudLogin
beginCloudSessionRefresh
completeCloudSessionRefresh
requestCloudDeviceApproval
listPendingCloudDevices
approveCloudDevice
```

اختبار endpoint مبدئيًا لاختبار وجود الوظيفة فقط:

```bash
curl -i -X POST \
  'https://us-central1-masrof-manager.cloudfunctions.net/beginCloudLogin' \
  -H 'content-type: application/json' \
  --data '{}'
```

النتيجة المتوقعة بعد النشر ليست نجاح تسجيل دخول لأن البيانات ناقصة، لكنها يجب ألا تكون `404`. غالبًا ستظهر استجابة خطأ تحقق من المدخلات، وهذا يدل على أن الوظيفة أصبحت موجودة.

## خطوات اختبار تسجيل الدخول في Android

1. ابنِ التطبيق باستخدام ملف:

   ```text
   app/google-services.json
   ```

2. تأكد من أن معرّف الحزمة هو:

   ```text
   com.mohammedalhzmi.masrofmanager
   ```

3. أنشئ مستخدمًا في Firebase Authentication باستخدام Email/Password.
4. أنشئ مستندًا مطابقًا في Firestore:

   ```text
   users/{UID}
   ```

   مثال للحقول الأساسية:

   ```json
   {
     "email": "user@example.com",
     "active": true,
     "role": "SYSTEM_ADMIN"
   }
   ```

5. افتح التطبيق وأدخل البريد وكلمة المرور.
6. في أول جهاز، ستنشأ مطالبة اعتماد للجهاز بحالة `PENDING`.
7. يجب اعتماد الجهاز عبر حساب إداري موثوق قبل أن تصبح حالة الدخول `READY`.
8. بعد الاعتماد، سجّل الدخول مجددًا وتحقق من ظهور حالة الجهاز والحساب كمعتمدين.

## أكثر أسباب فشل الدخول شيوعًا

- عدم نشر Functions أو نشرها في مشروع مختلف.
- استخدام ملف `google-services.json` لمشروع غير `masrof-manager`.
- عدم تفعيل Email/Password في Firebase Authentication.
- عدم وجود `users/{uid}`.
- وجود `active: false` أو دور غير صحيح.
- عدم اعتماد الجهاز، حيث تبقى الحالة `DEVICE_PENDING`.
- عدم إنشاء Firestore Database.
- نشر قواعد Firestore قبل تجهيز المستخدم الإداري.
- تشغيل نسخة APK قديمة لا تحتوي على آخر تعديلات Android.
- اختلاف منطقة Functions عن `us-central1`.

## ملاحظات أمنية

- `FIREBASE_WEB_API_KEY` مفتاح عميل وليس مفتاح Service Account، لكنه يجب أن يبقى داخل ملف البيئة المحلي وألا يضاف إلى Git.
- لا ترفع service-account JSON أو private key أو كلمات المرور إلى المستودع.
- لا تنشر قواعد Firestore أو Functions إلى مشروع آخر بالخطأ؛ راجع `firebase use` قبل النشر.
- قبل الإنتاج، فعّل App Check بعد اختبار توافقه مع طريقة توزيع APK.
