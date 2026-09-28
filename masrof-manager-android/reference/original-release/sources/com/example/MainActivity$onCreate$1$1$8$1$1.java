package com.example;

import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences;
import com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser;
import com.mohammedalhzmi.masrofmanager.util.SessionManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.example.MainActivity$onCreate$1$1$8$1$1", m938f = "MainActivity.kt", m939i = {}, m940l = {94}, m941m = "invokeSuspend", m942n = {}, m943s = {})
final class MainActivity$onCreate$1$1$8$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $password;
    final /* synthetic */ boolean $remember;
    final /* synthetic */ String $username;
    final /* synthetic */ MasrofViewModel $viewModel;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$1$1$8$1$1(MasrofViewModel masrofViewModel, MainActivity mainActivity, String str, String str2, boolean z, Continuation<? super MainActivity$onCreate$1$1$8$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = masrofViewModel;
        this.this$0 = mainActivity;
        this.$username = str;
        this.$password = str2;
        this.$remember = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$onCreate$1$1$8$1$1(this.$viewModel, this.this$0, this.$username, this.$password, this.$remember, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$1$1$8$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.$viewModel.authenticate(this.this$0, this.$username, this.$password, this.$remember, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((AuthenticatedUser) obj) != null) {
            SessionManager.INSTANCE.markActive(this.this$0);
            this.this$0.setLoggedIn(true);
            this.this$0.setLocked(false);
            this.this$0.setLoginError(null);
            AppLockPreferences.INSTANCE.markUnlocked(this.this$0);
        } else {
            MainActivity mainActivity = this.this$0;
            String lastAuthError = this.$viewModel.getLastAuthError();
            if (lastAuthError == null) {
                lastAuthError = "تعذر تسجيل الدخول.";
            }
            mainActivity.setLoginError(lastAuthError);
        }
        return Unit.INSTANCE;
    }
}
