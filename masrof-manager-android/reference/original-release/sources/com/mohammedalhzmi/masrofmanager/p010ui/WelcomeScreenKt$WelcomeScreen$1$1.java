package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.runtime.MutableFloatState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: WelcomeScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.WelcomeScreenKt$WelcomeScreen$1$1", m938f = "WelcomeScreen.kt", m939i = {0}, m940l = {23, 23}, m941m = "invokeSuspend", m942n = {"step"}, m943s = {"I$0"})
final class WelcomeScreenKt$WelcomeScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $onFinished;
    final /* synthetic */ MutableFloatState $progress$delegate;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WelcomeScreenKt$WelcomeScreen$1$1(Function0<Unit> function0, MutableFloatState mutableFloatState, Continuation<? super WelcomeScreenKt$WelcomeScreen$1$1> continuation) {
        super(2, continuation);
        this.$onFinished = function0;
        this.$progress$delegate = mutableFloatState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WelcomeScreenKt$WelcomeScreen$1$1(this.$onFinished, this.$progress$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WelcomeScreenKt$WelcomeScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0028  */
    /* JADX WARN: Code duplicated, block: B:17:0x0043  */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(320, r6) == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(700, r6) == r0) goto L19;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0035 -> B:16:0x0038). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 1;
            if (i < 11) {
                this.I$0 = i;
                this.label = 1;
            } else {
                this.label = 2;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            i = this.I$0;
            ResultKt.throwOnFailure(obj);
            this.$progress$delegate.setFloatValue(i / 10.0f);
            i++;
            if (i < 11) {
                this.I$0 = i;
                this.label = 1;
            } else {
                this.label = 2;
            }
            return coroutine_suspended;
        }
        if (i2 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$onFinished.invoke();
        return Unit.INSTANCE;
    }
}
