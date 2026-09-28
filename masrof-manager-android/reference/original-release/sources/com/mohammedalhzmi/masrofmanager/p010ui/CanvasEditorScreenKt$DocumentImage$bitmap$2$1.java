package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.compose.runtime.ProduceStateScope;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import java.io.InputStream;
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
import kotlin.p012io.CloseableKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: CanvasEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\n"}, m914d2 = {"<anonymous>", "", "Landroidx/compose/runtime/ProduceStateScope;", "Landroid/graphics/Bitmap;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$DocumentImage$bitmap$2$1", m938f = "CanvasEditorScreen.kt", m939i = {0}, m940l = {198}, m941m = "invokeSuspend", m942n = {"$this$produceState"}, m943s = {"L$0"})
final class CanvasEditorScreenKt$DocumentImage$bitmap$2$1 extends SuspendLambda implements Function2<ProduceStateScope<Bitmap>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ DesignElementEntity $element;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CanvasEditorScreenKt$DocumentImage$bitmap$2$1(Context context, DesignElementEntity designElementEntity, Continuation<? super CanvasEditorScreenKt$DocumentImage$bitmap$2$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$element = designElementEntity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CanvasEditorScreenKt$DocumentImage$bitmap$2$1 canvasEditorScreenKt$DocumentImage$bitmap$2$1 = new CanvasEditorScreenKt$DocumentImage$bitmap$2$1(this.$context, this.$element, continuation);
        canvasEditorScreenKt$DocumentImage$bitmap$2$1.L$0 = obj;
        return canvasEditorScreenKt$DocumentImage$bitmap$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProduceStateScope<Bitmap> produceStateScope, Continuation<? super Unit> continuation) {
        return ((CanvasEditorScreenKt$DocumentImage$bitmap$2$1) create(produceStateScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$DocumentImage$bitmap$2$1$1 */
    /* JADX INFO: compiled from: CanvasEditorScreen.kt */
    @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$DocumentImage$bitmap$2$1$1", m938f = "CanvasEditorScreen.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
    static final class C38821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ DesignElementEntity $element;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C38821(Context context, DesignElementEntity designElementEntity, Continuation<? super C38821> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$element = designElementEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C38821 c38821 = new C38821(this.$context, this.$element, continuation);
            c38821.L$0 = obj;
            return c38821;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
            return ((C38821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM7781constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context context = this.$context;
            DesignElementEntity designElementEntity = this.$element;
            try {
                Result.Companion companion = Result.INSTANCE;
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(designElementEntity.getContent()));
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                    CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                    objM7781constructorimpl = Result.m7781constructorimpl(bitmapDecodeStream);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStreamOpenInputStream, th);
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

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProduceStateScope produceStateScope = (ProduceStateScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = SpillingKt.nullOutSpilledVariable(produceStateScope);
            this.L$1 = produceStateScope;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new C38821(this.$context, this.$element, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            produceStateScope = (ProduceStateScope) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        produceStateScope.setValue(obj);
        return Unit.INSTANCE;
    }
}
