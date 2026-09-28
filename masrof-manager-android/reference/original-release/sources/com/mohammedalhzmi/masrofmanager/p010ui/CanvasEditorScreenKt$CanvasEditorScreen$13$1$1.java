package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import androidx.compose.runtime.State;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.util.HybridAiAssistant;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CanvasEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$13$1$1", m938f = "CanvasEditorScreen.kt", m939i = {0, 0, 0}, m940l = {153}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-CanvasEditorScreenKt$CanvasEditorScreen$13$1$1$1"}, m943s = {"L$0", "L$1", "I$0"})
final class CanvasEditorScreenKt$CanvasEditorScreen$13$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ State<DocumentDesignEntity> $design$delegate;
    final /* synthetic */ State<List<DesignElementEntity>> $elements$delegate;
    final /* synthetic */ String $endpoint;
    final /* synthetic */ String $instruction;
    final /* synthetic */ String $key;
    final /* synthetic */ Function1<String, Unit> $status;
    final /* synthetic */ MasrofViewModel $viewModel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CanvasEditorScreenKt$CanvasEditorScreen$13$1$1(Function1<? super String, Unit> function1, Context context, String str, String str2, String str3, State<? extends List<DesignElementEntity>> state, State<DocumentDesignEntity> state2, MasrofViewModel masrofViewModel, Continuation<? super CanvasEditorScreenKt$CanvasEditorScreen$13$1$1> continuation) {
        super(2, continuation);
        this.$status = function1;
        this.$context = context;
        this.$key = str;
        this.$endpoint = str2;
        this.$instruction = str3;
        this.$elements$delegate = state;
        this.$design$delegate = state2;
        this.$viewModel = masrofViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CanvasEditorScreenKt$CanvasEditorScreen$13$1$1 canvasEditorScreenKt$CanvasEditorScreen$13$1$1 = new CanvasEditorScreenKt$CanvasEditorScreen$13$1$1(this.$status, this.$context, this.$key, this.$endpoint, this.$instruction, this.$elements$delegate, this.$design$delegate, this.$viewModel, continuation);
        canvasEditorScreenKt$CanvasEditorScreen$13$1$1.L$0 = obj;
        return canvasEditorScreenKt$CanvasEditorScreen$13$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CanvasEditorScreenKt$CanvasEditorScreen$13$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CanvasEditorScreenKt$CanvasEditorScreen$13$1$1 canvasEditorScreenKt$CanvasEditorScreen$13$1$1;
        Throwable th;
        Object objM7781constructorimpl;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.$status.invoke("جارٍ تحليل الطلب…");
            Context context = this.$context;
            String str = this.$key;
            String str2 = this.$endpoint;
            String str3 = this.$instruction;
            State<List<DesignElementEntity>> state = this.$elements$delegate;
            State<DocumentDesignEntity> state2 = this.$design$delegate;
            try {
                Result.Companion companion = Result.INSTANCE;
                HybridAiAssistant hybridAiAssistant = HybridAiAssistant.INSTANCE;
                List<DesignElementEntity> listCanvasEditorScreen$lambda$0 = CanvasEditorScreenKt.CanvasEditorScreen$lambda$0(state);
                DocumentDesignEntity documentDesignEntityCanvasEditorScreen$lambda$25 = CanvasEditorScreenKt.CanvasEditorScreen$lambda$25(state2);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.I$0 = 0;
                this.label = 1;
                canvasEditorScreenKt$CanvasEditorScreen$13$1$1 = this;
                try {
                    obj = hybridAiAssistant.plan(context, str, str2, str3, listCanvasEditorScreen$lambda$0, documentDesignEntityCanvasEditorScreen$lambda$25, canvasEditorScreenKt$CanvasEditorScreen$13$1$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    th = th;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                }
            } catch (Throwable th3) {
                th = th3;
                canvasEditorScreenKt$CanvasEditorScreen$13$1$1 = this;
                th = th;
                Result.Companion companion3 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                ResultKt.throwOnFailure(obj);
                canvasEditorScreenKt$CanvasEditorScreen$13$1$1 = this;
            } catch (Throwable th4) {
                th = th4;
                canvasEditorScreenKt$CanvasEditorScreen$13$1$1 = this;
                Result.Companion companion4 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
        }
        objM7781constructorimpl = Result.m7781constructorimpl((HybridAiAssistant.Result) obj);
        MasrofViewModel masrofViewModel = canvasEditorScreenKt$CanvasEditorScreen$13$1$1.$viewModel;
        Function1<String, Unit> function1 = canvasEditorScreenKt$CanvasEditorScreen$13$1$1.$status;
        if (Result.m7788isSuccessimpl(objM7781constructorimpl)) {
            HybridAiAssistant.Result result = (HybridAiAssistant.Result) objM7781constructorimpl;
            masrofViewModel.applyAiCommands(result.getCommands());
            function1.invoke(result.getMode() + ": تم تطبيق " + result.getCommands().size() + " أمرًا على التصميم");
        }
        Function1<String, Unit> function2 = canvasEditorScreenKt$CanvasEditorScreen$13$1$1.$status;
        Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
        if (thM7784exceptionOrNullimpl != null) {
            String message = thM7784exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "خطأ غير معروف";
            }
            function2.invoke("تعذر التنفيذ: " + message);
        }
        return Unit.INSTANCE;
    }
}
