package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.runtime.MutableState;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
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
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ManagerDashboardScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
@DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1", m938f = "ManagerDashboardScreen.kt", m939i = {0, 0, 0}, m940l = {23}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1$1"}, m943s = {"L$0", "L$1", "I$0"})
final class ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $message$delegate;
    final /* synthetic */ MutableState<List<FirebaseCloudSync.DeviceRequest>> $requests$delegate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1(MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState, MutableState<String> mutableState2, Continuation<? super ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1> continuation) {
        super(2, continuation);
        this.$requests$delegate = mutableState;
        this.$message$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1 managerDashboardScreenKt$ManagerDashboardScreen$refresh$1 = new ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1(this.$requests$delegate, this.$message$delegate, continuation);
        managerDashboardScreenKt$ManagerDashboardScreen$refresh$1.L$0 = obj;
        return managerDashboardScreenKt$ManagerDashboardScreen$refresh$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM7781constructorimpl;
        MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState;
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState2 = this.$requests$delegate;
                Result.Companion companion = Result.INSTANCE;
                FirebaseCloudSync firebaseCloudSync = FirebaseCloudSync.INSTANCE;
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$2 = mutableState2;
                this.I$0 = 0;
                this.label = 1;
                Object objPendingDeviceRequests = firebaseCloudSync.pendingDeviceRequests(this);
                if (objPendingDeviceRequests == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableState = mutableState2;
                obj = objPendingDeviceRequests;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableState = (MutableState) this.L$2;
                ResultKt.throwOnFailure(obj);
            }
            mutableState.setValue((List) obj);
            objM7781constructorimpl = Result.m7781constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        MutableState<String> mutableState3 = this.$message$delegate;
        Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
        if (thM7784exceptionOrNullimpl != null) {
            mutableState3.setValue(thM7784exceptionOrNullimpl.getMessage());
        }
        return Unit.INSTANCE;
    }
}
