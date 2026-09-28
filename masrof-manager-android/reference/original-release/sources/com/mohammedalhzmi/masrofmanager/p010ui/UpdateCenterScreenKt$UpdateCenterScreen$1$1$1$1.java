package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.runtime.MutableState;
import com.mohammedalhzmi.masrofmanager.util.UpdateCenter;
import com.mohammedalhzmi.masrofmanager.util.UpdateInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: UpdateCenterScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1", m938f = "UpdateCenterScreen.kt", m939i = {}, m940l = {24}, m941m = "invokeSuspend", m942n = {}, m943s = {})
final class UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $availableUrl$delegate;
    final /* synthetic */ MutableState<String> $status$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1(MutableState<String> mutableState, MutableState<String> mutableState2, Continuation<? super UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1> continuation) {
        super(2, continuation);
        this.$status$delegate = mutableState;
        this.$availableUrl$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1(this.$status$delegate, this.$availableUrl$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateCenterScreenKt$UpdateCenterScreen$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = UpdateCenter.INSTANCE.check(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        UpdateInfo updateInfo = (UpdateInfo) obj;
        if (updateInfo != null) {
            int versionCode = updateInfo.getVersionCode();
            MutableState<String> mutableState = this.$status$delegate;
            if (versionCode > 1) {
                mutableState.setValue("يتوفر إصدار " + updateInfo.getVersionName() + ": " + updateInfo.getNotes());
                this.$availableUrl$delegate.setValue(updateInfo.getApkUrl());
            } else {
                mutableState.setValue("التطبيق محدث حاليًا — الإصدار 1.0");
            }
        } else {
            this.$status$delegate.setValue("تعذر الاتصال بمصدر التحديثات");
        }
        return Unit.INSTANCE;
    }
}
