package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.runtime.MutableState;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ManagerDashboardScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$1$1", m938f = "ManagerDashboardScreen.kt", m939i = {}, m940l = {}, m941m = "invokeSuspend", m942n = {}, m943s = {})
final class ManagerDashboardScreenKt$ManagerDashboardScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $message$delegate;
    final /* synthetic */ MutableState<List<FirebaseCloudSync.DeviceRequest>> $requests$delegate;
    final /* synthetic */ CoroutineScope $scope;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ManagerDashboardScreenKt$ManagerDashboardScreen$1$1(CoroutineScope coroutineScope, MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState, MutableState<String> mutableState2, Continuation<? super ManagerDashboardScreenKt$ManagerDashboardScreen$1$1> continuation) {
        super(2, continuation);
        this.$scope = coroutineScope;
        this.$requests$delegate = mutableState;
        this.$message$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ManagerDashboardScreenKt$ManagerDashboardScreen$1$1(this.$scope, this.$requests$delegate, this.$message$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ManagerDashboardScreenKt$ManagerDashboardScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ManagerDashboardScreenKt.ManagerDashboardScreen$refresh(this.$scope, this.$requests$delegate, this.$message$delegate);
        return Unit.INSTANCE;
    }
}
