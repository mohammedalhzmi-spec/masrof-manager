package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.p000ui.input.pointer.PointerInputChange;
import androidx.compose.p000ui.input.pointer.PointerInputScope;
import androidx.compose.runtime.MutableIntState;
import androidx.core.view.MotionEventCompat;
import com.mohammedalhzmi.masrofmanager.data.Document;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: BookEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$BookEditorScreen$1$2$1", m938f = "BookEditorScreen.kt", m939i = {0}, m940l = {MotionEventCompat.AXIS_GENERIC_16}, m941m = "invokeSuspend", m942n = {"$this$pointerInput"}, m943s = {"L$0"})
final class BookEditorScreenKt$BookEditorScreen$1$2$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableIntState $pageIndex$delegate;
    final /* synthetic */ List<Document> $pages;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BookEditorScreenKt$BookEditorScreen$1$2$1(List<Document> list, MutableIntState mutableIntState, Continuation<? super BookEditorScreenKt$BookEditorScreen$1$2$1> continuation) {
        super(2, continuation);
        this.$pages = list;
        this.$pageIndex$delegate = mutableIntState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BookEditorScreenKt$BookEditorScreen$1$2$1 bookEditorScreenKt$BookEditorScreen$1$2$1 = new BookEditorScreenKt$BookEditorScreen$1$2$1(this.$pages, this.$pageIndex$delegate, continuation);
        bookEditorScreenKt$BookEditorScreen$1$2$1.L$0 = obj;
        return bookEditorScreenKt$BookEditorScreen$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((BookEditorScreenKt$BookEditorScreen$1$2$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final List<Document> list = this.$pages;
            final MutableIntState mutableIntState = this.$pageIndex$delegate;
            this.L$0 = SpillingKt.nullOutSpilledVariable(pointerInputScope);
            this.label = 1;
            if (DragGestureDetectorKt.detectHorizontalDragGestures$default(pointerInputScope, null, null, null, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$BookEditorScreen$1$2$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return BookEditorScreenKt$BookEditorScreen$1$2$1.invokeSuspend$lambda$0(list, mutableIntState, (PointerInputChange) obj2, ((Float) obj3).floatValue());
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

    static final Unit invokeSuspend$lambda$0(List list, MutableIntState mutableIntState, PointerInputChange pointerInputChange, float f) {
        pointerInputChange.consume();
        if (f < -80.0f && BookEditorScreenKt.BookEditorScreen$lambda$5(mutableIntState) < CollectionsKt.getLastIndex(list)) {
            mutableIntState.setIntValue(BookEditorScreenKt.BookEditorScreen$lambda$5(mutableIntState) + 1);
        }
        if (f > 80.0f && BookEditorScreenKt.BookEditorScreen$lambda$5(mutableIntState) > 0) {
            mutableIntState.setIntValue(BookEditorScreenKt.BookEditorScreen$lambda$5(mutableIntState) - 1);
        }
        return Unit.INSTANCE;
    }
}
