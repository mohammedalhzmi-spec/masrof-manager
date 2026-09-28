package com.example;

import androidx.core.location.LocationRequestCompat;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.example.MainActivity$onCreate$1$1$10$1$1", m938f = "MainActivity.kt", m939i = {0, 0, 0}, m940l = {LocationRequestCompat.QUALITY_BALANCED_POWER_ACCURACY}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-MainActivity$onCreate$1$1$10$1$1$1"}, m943s = {"L$0", "L$2", "I$0"})
final class MainActivity$onCreate$1$1$10$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $identifier;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$1$1$10$1$1(String str, MainActivity mainActivity, Continuation<? super MainActivity$onCreate$1$1$10$1$1> continuation) {
        super(2, continuation);
        this.$identifier = str;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainActivity$onCreate$1$1$10$1$1 mainActivity$onCreate$1$1$10$1$1 = new MainActivity$onCreate$1$1$10$1$1(this.$identifier, this.this$0, continuation);
        mainActivity$onCreate$1$1$10$1$1.L$0 = obj;
        return mainActivity$onCreate$1$1$10$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$1$1$10$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM7781constructorimpl;
        MainActivity mainActivity;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String str = this.$identifier;
                MainActivity mainActivity2 = this.this$0;
                Result.Companion companion = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync = FirebaseCloudSync.INSTANCE;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = mainActivity2;
                this.L$2 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 1;
                if (firebaseCloudSync.sendPasswordReset(str, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mainActivity = mainActivity2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mainActivity = (MainActivity) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            mainActivity.setLoginError("تم إرسال رابط استعادة كلمة المرور إلى البريد المرتبط بالحساب.");
            objM7781constructorimpl = Result.m7781constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        MainActivity mainActivity3 = this.this$0;
        Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
        if (thM7784exceptionOrNullimpl != null) {
            mainActivity3.setLoginError(FirebaseCloudSync.INSTANCE.friendlyAuthError(thM7784exceptionOrNullimpl));
        }
        return Unit.INSTANCE;
    }
}
