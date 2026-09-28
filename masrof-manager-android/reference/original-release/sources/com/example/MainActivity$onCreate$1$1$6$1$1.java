package com.example;

import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
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
@DebugMetadata(m937c = "com.example.MainActivity$onCreate$1$1$6$1$1", m938f = "MainActivity.kt", m939i = {1}, m940l = {83, 85}, m941m = "invokeSuspend", m942n = {"created"}, m943s = {"Z$0"})
final class MainActivity$onCreate$1$1$6$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $fullName;
    final /* synthetic */ String $password;
    final /* synthetic */ AppRole $role;
    final /* synthetic */ String $username;
    final /* synthetic */ MasrofViewModel $viewModel;
    boolean Z$0;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivity$onCreate$1$1$6$1$1(MasrofViewModel masrofViewModel, String str, String str2, String str3, AppRole appRole, MainActivity mainActivity, Continuation<? super MainActivity$onCreate$1$1$6$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = masrofViewModel;
        this.$username = str;
        this.$password = str2;
        this.$fullName = str3;
        this.$role = appRole;
        this.this$0 = mainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivity$onCreate$1$1$6$1$1(this.$viewModel, this.$username, this.$password, this.$fullName, this.$role, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivity$onCreate$1$1$6$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
    
        if (r11 == r0) goto L21;
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
            AuthenticatedUser authenticatedUser = (AuthenticatedUser) obj;
            MainActivity mainActivity = this.this$0;
            if (authenticatedUser != null) {
                mainActivity.setLoggedIn(true);
                this.this$0.setLocked(false);
                this.this$0.setShowRegister(false);
                this.this$0.setRegistrationError(null);
            } else {
                String lastAuthError = this.$viewModel.getLastAuthError();
                if (lastAuthError == null) {
                    lastAuthError = "تم إنشاء الحساب لكن تعذر تسجيل الدخول به.";
                }
                mainActivity.setRegistrationError(lastAuthError);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        this.label = 1;
        obj = this.$viewModel.registerUser(this.$username, this.$password, this.$fullName, this.$role.name(), this);
        if (obj != coroutine_suspended) {
        }
        return coroutine_suspended;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (!zBooleanValue) {
            MainActivity mainActivity2 = this.this$0;
            String lastAuthError2 = this.$viewModel.getLastAuthError();
            if (lastAuthError2 == null) {
                lastAuthError2 = "تعذر إنشاء الحساب. تحقق من البيانات والاتصال.";
            }
            mainActivity2.setRegistrationError(lastAuthError2);
        } else {
            this.Z$0 = zBooleanValue;
            this.label = 2;
            obj = this.$viewModel.authenticate(this.this$0, this.$username, this.$password, true, this);
        }
        return Unit.INSTANCE;
    }
}
