package com.example;

import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser;
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
@DebugMetadata(m937c = "com.example.MainActivity$onCreate$1$1$1$1", m938f = "MainActivity.kt", m939i = {}, m940l = {59, 60}, m941m = "invokeSuspend", m942n = {}, m943s = {})
final class MainActivity$onCreate$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MasrofViewModel $viewModel;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$1$1$1$1(MasrofViewModel masrofViewModel, MainActivity mainActivity, Continuation<? super MainActivity$onCreate$1$1$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = masrofViewModel;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$onCreate$1$1$1$1(this.$viewModel, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003e, code lost:
    
        if (r6 == r0) goto L15;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (((AuthenticatedUser) obj) != null) {
                this.this$0.setLoggedIn(true);
                this.this$0.setShowWelcome(false);
            }
            this.this$0.setInitializing(false);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        if (this.$viewModel.ensureDefaultAdmin(this) != coroutine_suspended) {
        }
        return coroutine_suspended;
        this.label = 2;
        obj = this.$viewModel.restoreLocalUser(this.this$0, this);
    }
}
