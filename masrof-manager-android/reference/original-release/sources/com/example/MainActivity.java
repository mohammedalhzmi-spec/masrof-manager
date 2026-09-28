package com.example;

import android.content.Context;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.room.Room;
import com.example.p004ui.theme.ThemeKt;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase;
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository;
import com.mohammedalhzmi.masrofmanager.p010ui.AppLockScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.AppNavigationKt;
import com.mohammedalhzmi.masrofmanager.p010ui.LoginScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.p010ui.RegisterScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.UsernameSetupScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.WelcomeScreenKt;
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
import com.mohammedalhzmi.masrofmanager.util.LockType;
import com.mohammedalhzmi.masrofmanager.util.RememberedLogin;
import com.mohammedalhzmi.masrofmanager.util.SessionManager;
import com.mohammedalhzmi.masrofmanager.util.UserSession;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m913d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u000201H\u0014J\u0010\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u0015H\u0002J\b\u00107\u001a\u00020\u0005H\u0002J\b\u00108\u001a\u000201H\u0002R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR+\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR/\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0004\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\u0004\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR+\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\f\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR+\u0010$\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\f\u001a\u0004\b%\u0010\b\"\u0004\b&\u0010\nR+\u0010(\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\f\u001a\u0004\b)\u0010\b\"\u0004\b*\u0010\nR+\u0010,\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\f\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\n¨\u00069"}, m914d2 = {"Lcom/example/MainActivity;", "Landroidx/fragment/app/FragmentActivity;", "<init>", "()V", "<set-?>", "", "locked", "getLocked", "()Z", "setLocked", "(Z)V", "locked$delegate", "Landroidx/compose/runtime/MutableState;", "showWelcome", "getShowWelcome", "setShowWelcome", "showWelcome$delegate", "loggedIn", "getLoggedIn", "setLoggedIn", "loggedIn$delegate", "", "loginError", "getLoginError", "()Ljava/lang/String;", "setLoginError", "(Ljava/lang/String;)V", "loginError$delegate", "registrationError", "getRegistrationError", "setRegistrationError", "registrationError$delegate", "showRegister", "getShowRegister", "setShowRegister", "showRegister$delegate", "initializing", "getInitializing", "setInitializing", "initializing$delegate", "firstLoginRemember", "getFirstLoginRemember", "setFirstLoginRemember", "firstLoginRemember$delegate", "pendingUsernameSetup", "getPendingUsernameSetup", "setPendingUsernameSetup", "pendingUsernameSetup$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "unlockWithSecret", "secret", "biometricAvailable", "showBiometricPrompt", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class MainActivity extends FragmentActivity {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: locked$delegate, reason: from kotlin metadata */
    private final MutableState locked = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: showWelcome$delegate, reason: from kotlin metadata */
    private final MutableState showWelcome = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: loggedIn$delegate, reason: from kotlin metadata */
    private final MutableState loggedIn = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: loginError$delegate, reason: from kotlin metadata */
    private final MutableState loginError = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: registrationError$delegate, reason: from kotlin metadata */
    private final MutableState registrationError = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    /* JADX INFO: renamed from: showRegister$delegate, reason: from kotlin metadata */
    private final MutableState showRegister = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: initializing$delegate, reason: from kotlin metadata */
    private final MutableState initializing = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: firstLoginRemember$delegate, reason: from kotlin metadata */
    private final MutableState firstLoginRemember = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: pendingUsernameSetup$delegate, reason: from kotlin metadata */
    private final MutableState pendingUsernameSetup = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getLocked() {
        return ((Boolean) this.locked.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLocked(boolean z) {
        this.locked.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getShowWelcome() {
        return ((Boolean) this.showWelcome.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowWelcome(boolean z) {
        this.showWelcome.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getLoggedIn() {
        return ((Boolean) this.loggedIn.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLoggedIn(boolean z) {
        this.loggedIn.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getLoginError() {
        return (String) this.loginError.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLoginError(String str) {
        this.loginError.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String getRegistrationError() {
        return (String) this.registrationError.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRegistrationError(String str) {
        this.registrationError.setValue(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getShowRegister() {
        return ((Boolean) this.showRegister.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowRegister(boolean z) {
        this.showRegister.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getInitializing() {
        return ((Boolean) this.initializing.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setInitializing(boolean z) {
        this.initializing.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getFirstLoginRemember() {
        return ((Boolean) this.firstLoginRemember.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFirstLoginRemember(boolean z) {
        this.firstLoginRemember.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getPendingUsernameSetup() {
        return ((Boolean) this.pendingUsernameSetup.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPendingUsernameSetup(boolean z) {
        this.pendingUsernameSetup.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mainActivity = this;
        EdgeToEdge.enable$default(mainActivity, null, null, 3, null);
        MainActivity mainActivity2 = this;
        FirebaseCloudSync.INSTANCE.initialize(mainActivity2);
        setLocked(AppLockPreferences.INSTANCE.enabled(mainActivity2) && AppLockPreferences.shouldRelock$default(AppLockPreferences.INSTANCE, mainActivity2, 0L, 2, null));
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        final MasrofDatabase masrofDatabase = (MasrofDatabase) Room.databaseBuilder(applicationContext, MasrofDatabase.class, "masrof-db").addMigrations(MasrofDatabase.INSTANCE.getMIGRATION_3_4(), MasrofDatabase.INSTANCE.getMIGRATION_4_5(), MasrofDatabase.INSTANCE.getMIGRATION_5_6(), MasrofDatabase.INSTANCE.getMIGRATION_6_7(), MasrofDatabase.INSTANCE.getMIGRATION_7_8(), MasrofDatabase.INSTANCE.getMIGRATION_8_9(), MasrofDatabase.INSTANCE.getMIGRATION_9_10(), MasrofDatabase.INSTANCE.getMIGRATION_10_11()).build();
        final MasrofRepository masrofRepository = new MasrofRepository(masrofDatabase.documentDao(), masrofDatabase.settingsDao(), masrofDatabase.contactDao(), masrofDatabase.userDao(), masrofDatabase.auditDao(), masrofDatabase.designDao());
        final MasrofViewModel masrofViewModel = (MasrofViewModel) new ViewModelProvider(this, new ViewModelProvider.Factory() { // from class: com.example.MainActivity$onCreate$viewModel$1
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> cls, CreationExtras creationExtras) {
                return (T) super.create(cls, creationExtras);
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(KClass<T> kClass, CreationExtras creationExtras) {
                return (T) super.create(kClass, creationExtras);
            }

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> modelClass) {
                Intrinsics.checkNotNullParameter(modelClass, "modelClass");
                return new MasrofViewModel(masrofRepository, masrofDatabase, null, 4, null);
            }
        }).get(MasrofViewModel.class);
        ComponentActivityKt.setContent$default(mainActivity, null, ComposableLambdaKt.composableLambdaInstance(-601144069, true, new Function2() { // from class: com.example.MainActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$30(masrofViewModel, this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$30(final MasrofViewModel masrofViewModel, final MainActivity mainActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C55@3098L4354,55@3079L4373:MainActivity.kt#to5c3");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-601144069, i, -1, "com.example.MainActivity.onCreate.<anonymous> (MainActivity.kt:55)");
            }
            ThemeKt.MyApplicationTheme(false, false, ComposableLambdaKt.rememberComposableLambda(434770631, true, new Function2() { // from class: com.example.MainActivity$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$30$lambda$29(masrofViewModel, mainActivity, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), composer, 384, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0370  */
    static final Unit onCreate$lambda$30$lambda$29(final MasrofViewModel masrofViewModel, final MainActivity mainActivity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C56@3128L24,57@3190L346,57@3169L367:MainActivity.kt#to5c3");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(434770631, i, -1, "com.example.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:56)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composer, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composer);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1202751969, "CC(remember):MainActivity.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changedInstance(mainActivity);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function2) new MainActivity$onCreate$1$1$1$1(masrofViewModel, mainActivity, null);
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composer, 6);
            if (mainActivity.getInitializing()) {
                composer.startReplaceGroup(-1369025630);
                ComposerKt.sourceInformation(composer, "67@3607L3,67@3593L17");
                ComposerKt.sourceInformationMarkerStart(composer, 1202764970, "CC(remember):MainActivity.kt#9igjgp");
                Object objRememberedValue3 = composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                WelcomeScreenKt.WelcomeScreen((Function0) objRememberedValue3, composer, 6);
                composer.endReplaceGroup();
            } else if (mainActivity.getShowWelcome()) {
                composer.startReplaceGroup(-1368945650);
                ComposerKt.sourceInformation(composer, "69@3687L23,69@3673L37");
                ComposerKt.sourceInformationMarkerStart(composer, 1202767550, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance2 = composer.changedInstance(mainActivity);
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$4$lambda$3(this.f$0);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                WelcomeScreenKt.WelcomeScreen((Function0) objRememberedValue4, composer, 0);
                composer.endReplaceGroup();
            } else if (!mainActivity.getLoggedIn() && mainActivity.getPendingUsernameSetup()) {
                composer.startReplaceGroup(-1368806646);
                ComposerKt.sourceInformation(composer, "72@3852L366,76@4253L80,71@3795L617");
                ComposerKt.sourceInformationMarkerStart(composer, 1202773173, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance3 = composer.changedInstance(coroutineScope) | composer.changedInstance(masrofViewModel) | composer.changedInstance(mainActivity);
                Object objRememberedValue5 = composer.rememberedValue();
                if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function1() { // from class: com.example.MainActivity$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$6$lambda$5(coroutineScope, masrofViewModel, mainActivity, (String) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue5);
                }
                Function1 function1 = (Function1) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202785719, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance4 = composer.changedInstance(mainActivity);
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$8$lambda$7(this.f$0);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                UsernameSetupScreenKt.UsernameSetupScreen(function1, (Function0) objRememberedValue6, masrofViewModel.getLastAuthError(), composer, 0);
                composer.endReplaceGroup();
            } else if (!mainActivity.getLoggedIn() && mainActivity.getShowRegister()) {
                composer.startReplaceGroup(-1368109363);
                ComposerKt.sourceInformation(composer, "80@4517L785,89@5313L50,80@4489L902");
                ComposerKt.sourceInformationMarkerStart(composer, 1202794872, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance5 = composer.changedInstance(coroutineScope) | composer.changedInstance(masrofViewModel) | composer.changedInstance(mainActivity);
                Object objRememberedValue7 = composer.rememberedValue();
                if (zChangedInstance5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new Function6() { // from class: com.example.MainActivity$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function6
                        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$10$lambda$9(coroutineScope, masrofViewModel, mainActivity, (String) obj, (String) obj2, (String) obj3, (AppRole) obj4, ((Boolean) obj5).booleanValue(), ((Boolean) obj6).booleanValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue7);
                }
                Function6 function6 = (Function6) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202819609, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance6 = composer.changedInstance(mainActivity);
                Object objRememberedValue8 = composer.rememberedValue();
                if (zChangedInstance6 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$12$lambda$11(this.f$0);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue8);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                RegisterScreenKt.RegisterScreen(function6, (Function0) objRememberedValue8, mainActivity.getRegistrationError(), composer, 0);
                composer.endReplaceGroup();
            } else if (!mainActivity.getLoggedIn()) {
                composer.startReplaceGroup(-1367140458);
                ComposerKt.sourceInformation(composer, "91@5474L500,96@5991L429,101@6440L243,101@6694L21,101@6730L42,91@5452L1341");
                ComposerKt.sourceInformationMarkerStart(composer, 1202825211, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance7 = composer.changedInstance(coroutineScope) | composer.changedInstance(masrofViewModel) | composer.changedInstance(mainActivity);
                Object objRememberedValue9 = composer.rememberedValue();
                if (zChangedInstance7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue9 = new Function3() { // from class: com.example.MainActivity$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$14$lambda$13(coroutineScope, masrofViewModel, mainActivity, (String) obj, (String) obj2, ((Boolean) obj3).booleanValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue9);
                }
                Function3 function3 = (Function3) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202841684, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance8 = composer.changedInstance(coroutineScope) | composer.changedInstance(masrofViewModel) | composer.changedInstance(mainActivity);
                Object objRememberedValue10 = composer.rememberedValue();
                if (zChangedInstance8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue10 = new Function3() { // from class: com.example.MainActivity$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$16$lambda$15(coroutineScope, masrofViewModel, mainActivity, (String) obj, (String) obj2, ((Boolean) obj3).booleanValue());
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue10);
                }
                Function3 function4 = (Function3) objRememberedValue10;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202855866, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance9 = composer.changedInstance(coroutineScope) | composer.changedInstance(mainActivity);
                Object objRememberedValue11 = composer.rememberedValue();
                if (zChangedInstance9 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue11 = new Function1() { // from class: com.example.MainActivity$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$18$lambda$17(coroutineScope, mainActivity, (String) obj);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue11);
                }
                Function1 function2 = (Function1) objRememberedValue11;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202863772, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance10 = composer.changedInstance(mainActivity);
                Object objRememberedValue12 = composer.rememberedValue();
                if (zChangedInstance10 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue12 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$20$lambda$19(this.f$0);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue12);
                }
                Function0 function0 = (Function0) objRememberedValue12;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 1202864945, "CC(remember):MainActivity.kt#9igjgp");
                boolean zChangedInstance11 = composer.changedInstance(mainActivity);
                Object objRememberedValue13 = composer.rememberedValue();
                if (zChangedInstance11 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue13 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$22$lambda$21(this.f$0);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue13);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                LoginScreenKt.LoginScreen(function3, function4, function2, function0, (Function0) objRememberedValue13, mainActivity.getLoginError(), composer, 0);
                composer.endReplaceGroup();
            } else if (mainActivity.getLocked()) {
                MainActivity mainActivity2 = mainActivity;
                if (AppLockPreferences.INSTANCE.enabled(mainActivity2)) {
                    composer.startReplaceGroup(-1365749085);
                    ComposerKt.sourceInformation(composer, "106@7069L25,107@7131L38,103@6887L304");
                    LockType lockTypeType = AppLockPreferences.INSTANCE.type(mainActivity2);
                    boolean zBiometricAvailable = mainActivity.biometricAvailable();
                    ComposerKt.sourceInformationMarkerStart(composer, 1202875776, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance12 = composer.changedInstance(mainActivity);
                    Object objRememberedValue14 = composer.rememberedValue();
                    if (zChangedInstance12 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue14 = new Function0() { // from class: com.example.MainActivity$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return MainActivity.onCreate$lambda$30$lambda$29$lambda$24$lambda$23(this.f$0);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue14);
                    }
                    Function0 function5 = (Function0) objRememberedValue14;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerStart(composer, 1202877773, "CC(remember):MainActivity.kt#9igjgp");
                    boolean zChangedInstance13 = composer.changedInstance(mainActivity);
                    Object objRememberedValue15 = composer.rememberedValue();
                    if (zChangedInstance13 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue15 = new Function1() { // from class: com.example.MainActivity$$ExternalSyntheticLambda9
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Boolean.valueOf(MainActivity.onCreate$lambda$30$lambda$29$lambda$26$lambda$25(this.f$0, (String) obj));
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue15);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    AppLockScreenKt.AppLockScreen(lockTypeType, zBiometricAvailable, function5, (Function1) objRememberedValue15, composer, 0);
                    composer.endReplaceGroup();
                } else {
                    composer.startReplaceGroup(-1365405636);
                    ComposerKt.sourceInformation(composer, "110@7281L139,110@7237L183");
                    ScaffoldKt.m3057ScaffoldTvnljyQ(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1752801076, true, new Function3() { // from class: com.example.MainActivity$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return MainActivity.onCreate$lambda$30$lambda$29$lambda$28(masrofViewModel, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer, 54), composer, 805306374, 510);
                    composer.endReplaceGroup();
                }
            } else {
                composer.startReplaceGroup(-1365405636);
                ComposerKt.sourceInformation(composer, "110@7281L139,110@7237L183");
                ScaffoldKt.m3057ScaffoldTvnljyQ(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(1752801076, true, new Function3() { // from class: com.example.MainActivity$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return MainActivity.onCreate$lambda$30$lambda$29$lambda$28(masrofViewModel, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54), composer, 805306374, 510);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$4$lambda$3(MainActivity mainActivity) {
        mainActivity.setShowWelcome(false);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$6$lambda$5(CoroutineScope coroutineScope, MasrofViewModel masrofViewModel, MainActivity mainActivity, String username) {
        Intrinsics.checkNotNullParameter(username, "username");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MainActivity$onCreate$1$1$4$1$1(masrofViewModel, mainActivity, username, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$8$lambda$7(MainActivity mainActivity) {
        FirebaseCloudSync.INSTANCE.signOut();
        mainActivity.setPendingUsernameSetup(false);
        mainActivity.setLoginError(null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$10$lambda$9(CoroutineScope coroutineScope, MasrofViewModel masrofViewModel, MainActivity mainActivity, String username, String password, String fullName, AppRole role, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MainActivity$onCreate$1$1$6$1$1(masrofViewModel, username, password, fullName, role, mainActivity, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$12$lambda$11(MainActivity mainActivity) {
        mainActivity.setShowRegister(false);
        mainActivity.setRegistrationError(null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$14$lambda$13(CoroutineScope coroutineScope, MasrofViewModel masrofViewModel, MainActivity mainActivity, String username, String password, boolean z) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MainActivity$onCreate$1$1$8$1$1(masrofViewModel, mainActivity, username, password, z, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$16$lambda$15(CoroutineScope coroutineScope, MasrofViewModel masrofViewModel, MainActivity mainActivity, String email, String password, boolean z) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MainActivity$onCreate$1$1$9$1$1(masrofViewModel, mainActivity, email, password, z, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$18$lambda$17(CoroutineScope coroutineScope, MainActivity mainActivity, String identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new MainActivity$onCreate$1$1$10$1$1(identifier, mainActivity, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$20$lambda$19(MainActivity mainActivity) {
        mainActivity.setLoginError(null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$22$lambda$21(MainActivity mainActivity) {
        mainActivity.setShowRegister(true);
        mainActivity.setLoginError(null);
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$24$lambda$23(MainActivity mainActivity) {
        mainActivity.showBiometricPrompt();
        return Unit.INSTANCE;
    }

    static final boolean onCreate$lambda$30$lambda$29$lambda$26$lambda$25(MainActivity mainActivity, String secret) {
        Intrinsics.checkNotNullParameter(secret, "secret");
        return mainActivity.unlockWithSecret(secret);
    }

    static final Unit onCreate$lambda$30$lambda$29$lambda$28(MasrofViewModel masrofViewModel, PaddingValues innerPadding, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
        ComposerKt.sourceInformation(composer, "C111@7323L75:MainActivity.kt#to5c3");
        if ((i & 6) == 0) {
            i |= composer.changed(innerPadding) ? 4 : 2;
        }
        if ((i & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1752801076, i, -1, "com.example.MainActivity.onCreate.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:111)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, innerPadding);
            ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierPadding);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 532686001, "C111@7372L24:MainActivity.kt#to5c3");
            AppNavigationKt.AppNavigation(masrofViewModel, composer, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0039  */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (getLoggedIn()) {
            MainActivity mainActivity = this;
            if (SessionManager.INSTANCE.expired(mainActivity) && !RememberedLogin.INSTANCE.enabled(mainActivity)) {
                FirebaseCloudSync.INSTANCE.signOut();
                UserSession.INSTANCE.setCurrent(null);
                SessionManager.INSTANCE.clear(mainActivity);
                setLoggedIn(false);
                setLocked(false);
                setLoginError("انتهت الجلسة لأسباب أمنية. سجل الدخول مرة أخرى.");
            } else if (getLoggedIn()) {
                SessionManager.INSTANCE.markActive(this);
            }
        } else if (getLoggedIn()) {
            SessionManager.INSTANCE.markActive(this);
        }
        MainActivity mainActivity2 = this;
        if (AppLockPreferences.INSTANCE.enabled(mainActivity2) && AppLockPreferences.shouldRelock$default(AppLockPreferences.INSTANCE, mainActivity2, 0L, 2, null)) {
            setLocked(true);
        }
    }

    private final boolean unlockWithSecret(String secret) {
        MainActivity mainActivity = this;
        boolean z = secret.length() >= (AppLockPreferences.INSTANCE.type(mainActivity) == LockType.PATTERN ? 4 : 1) && AppLockPreferences.INSTANCE.verify(mainActivity, secret);
        if (z) {
            AppLockPreferences.INSTANCE.markUnlocked(mainActivity);
            setLocked(false);
        }
        return z;
    }

    private final boolean biometricAvailable() {
        MainActivity mainActivity = this;
        return AppLockPreferences.INSTANCE.biometricEnabled(mainActivity) && BiometricManager.from(mainActivity).canAuthenticate(32783) == 0;
    }

    private final void showBiometricPrompt() {
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, getMainExecutor(), new BiometricPrompt.AuthenticationCallback() { // from class: com.example.MainActivity$showBiometricPrompt$prompt$1
            @Override // androidx.biometric.BiometricPrompt.AuthenticationCallback
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                AppLockPreferences.INSTANCE.markUnlocked(this.this$0);
                this.this$0.setLocked(false);
            }
        });
        BiometricPrompt.PromptInfo promptInfoBuild = new BiometricPrompt.PromptInfo.Builder().setTitle("فتح نظام مالية صندوق النظافة").setSubtitle("استخدم بصمة الإصبع أو وسيلة أمان الهاتف").setAllowedAuthenticators(32783).build();
        Intrinsics.checkNotNullExpressionValue(promptInfoBuild, "build(...)");
        biometricPrompt.authenticate(promptInfoBuild);
    }
}
