package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.p000ui.geometry.Offset;
import androidx.compose.p000ui.input.pointer.PointerInputChange;
import androidx.compose.p000ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CanvasEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasElement$1$2$1", m938f = "CanvasEditorScreen.kt", m939i = {0}, m940l = {176}, m941m = "invokeSuspend", m942n = {"$this$pointerInput"}, m943s = {"L$0"})
final class CanvasEditorScreenKt$CanvasElement$1$2$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<Float, Float, Unit> $onResize;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    CanvasEditorScreenKt$CanvasElement$1$2$1(Function2<? super Float, ? super Float, Unit> function2, Continuation<? super CanvasEditorScreenKt$CanvasElement$1$2$1> continuation) {
        super(2, continuation);
        this.$onResize = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CanvasEditorScreenKt$CanvasElement$1$2$1 canvasEditorScreenKt$CanvasElement$1$2$1 = new CanvasEditorScreenKt$CanvasElement$1$2$1(this.$onResize, continuation);
        canvasEditorScreenKt$CanvasElement$1$2$1.L$0 = obj;
        return canvasEditorScreenKt$CanvasElement$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((CanvasEditorScreenKt$CanvasElement$1$2$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    static final Unit invokeSuspend$lambda$0(Function2 function2, PointerInputChange pointerInputChange, Offset offset) {
        pointerInputChange.consume();
        function2.invoke(Float.valueOf(Offset.m4567getXimpl(offset.getPackedValue())), Float.valueOf(Offset.m4568getYimpl(offset.getPackedValue())));
        return Unit.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Function2<Float, Float, Unit> function2 = this.$onResize;
            this.L$0 = SpillingKt.nullOutSpilledVariable(pointerInputScope);
            this.label = 1;
            if (DragGestureDetectorKt.detectDragGestures$default(pointerInputScope, null, null, null, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasElement$1$2$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return CanvasEditorScreenKt$CanvasElement$1$2$1.invokeSuspend$lambda$0(function2, (PointerInputChange) obj2, (Offset) obj3);
                }
            }, this, 7, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
