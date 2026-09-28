package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.p012io.TextStreamsKt;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* JADX INFO: compiled from: UpdateCenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0086@¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/UpdateCenter;", "", "<init>", "()V", "MANIFEST_URL", "", "check", "Lcom/mohammedalhzmi/masrofmanager/util/UpdateInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openRelease", "", "context", "Landroid/content/Context;", ImagesContract.URL, "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class UpdateCenter {
    public static final int $stable = 0;
    public static final UpdateCenter INSTANCE = new UpdateCenter();
    private static final String MANIFEST_URL = "https://raw.githubusercontent.com/mohammedalhzmi-spec/masrof-manager1/main/update.json";

    private UpdateCenter() {
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.util.UpdateCenter$check$2 */
    /* JADX INFO: compiled from: UpdateCenter.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "Lcom/mohammedalhzmi/masrofmanager/util/UpdateInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.util.UpdateCenter$check$2", m938f = "UpdateCenter.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C41012 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UpdateInfo>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C41012(Continuation<? super C41012> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C41012 c41012 = new C41012(continuation);
            c41012.L$0 = obj;
            return c41012;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UpdateInfo> continuation) {
            return ((C41012) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM7781constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                Result.Companion companion = Result.INSTANCE;
                URLConnection uRLConnectionOpenConnection = new URL(UpdateCenter.MANIFEST_URL).openConnection();
                Intrinsics.checkNotNull(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                httpURLConnection.setConnectTimeout(7000);
                httpURLConnection.setReadTimeout(7000);
                InputStream inputStream = httpURLConnection.getInputStream();
                Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
                Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String text = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    JSONObject jSONObject = new JSONObject(text);
                    int i = jSONObject.getInt("versionCode");
                    String string = jSONObject.getString("versionName");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    String string2 = jSONObject.getString("apkUrl");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    String strOptString = jSONObject.optString("notes");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                    objM7781constructorimpl = Result.m7781constructorimpl(new UpdateInfo(i, string, string2, strOptString));
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedReader, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                Result.Companion companion2 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                return null;
            }
            return objM7781constructorimpl;
        }
    }

    public final Object check(Continuation<? super UpdateInfo> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C41012(null), continuation);
    }

    public final void openRelease(Context context, String url) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(url, "url");
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
    }
}
