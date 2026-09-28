package com.example;

import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences;
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
@DebugMetadata(m937c = "com.example.MainActivity$onCreate$1$1$4$1$1", m938f = "MainActivity.kt", m939i = {}, m940l = {74}, m941m = "invokeSuspend", m942n = {}, m943s = {})
final class MainActivity$onCreate$1$1$4$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $username;
    final /* synthetic */ MasrofViewModel $viewModel;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$1$1$4$1$1(MasrofViewModel masrofViewModel, MainActivity mainActivity, String str, Continuation<? super MainActivity$onCreate$1$1$4$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = masrofViewModel;
        this.this$0 = mainActivity;
        this.$username = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$onCreate$1$1$4$1$1(this.$viewModel, this.this$0, this.$username, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$1$1$4$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MasrofViewModel masrofViewModel = this.$viewModel;
            MainActivity mainActivity = this.this$0;
            this.label = 1;
            obj = masrofViewModel.completeFirstLoginUsername(mainActivity, this.$username, mainActivity.getFirstLoginRemember(), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            this.this$0.setPendingUsernameSetup(false);
            this.this$0.setLoggedIn(true);
            this.this$0.setLocked(false);
            this.this$0.setLoginError(null);
            AppLockPreferences.INSTANCE.markUnlocked(this.this$0);
        }
        return Unit.INSTANCE;
    }
}
