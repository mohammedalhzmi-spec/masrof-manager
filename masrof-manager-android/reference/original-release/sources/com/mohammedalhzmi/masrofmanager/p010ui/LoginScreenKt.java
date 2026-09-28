package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.activity.compose.BackHandlerKt;
import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.CheckboxDefaults;
import androidx.compose.material3.CheckboxKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.draw.ScaleKt;
import androidx.compose.p000ui.graphics.Color;
import androidx.compose.p000ui.graphics.ColorFilter;
import androidx.compose.p000ui.graphics.ColorKt;
import androidx.compose.p000ui.graphics.Shape;
import androidx.compose.p000ui.layout.ContentScale;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
import androidx.compose.p000ui.res.PainterResources_androidKt;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.input.PasswordVisualTransformation;
import androidx.compose.p000ui.text.input.VisualTransformation;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.p000ui.unit.C1786Dp;
import androidx.compose.p000ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.profileinstaller.ProfileVerifier;
import com.example.C2530R;
import com.google.firebase.messaging.Constants;
import com.google.logging.type.LogSeverity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LoginScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\u001a\u0087\u0001\u0010\u0005\u001a\u00020\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\b2\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\u0012\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\u0013²\u0006\n\u0010\u0014\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u0084\u0002"}, m914d2 = {"GovNavy", "Landroidx/compose/ui/graphics/Color;", "J", "GovGreen", "GovGold", "LoginScreen", "", "onLogin", "Lkotlin/Function3;", "", "", "onFirstLogin", "onResetPassword", "Lkotlin/Function1;", "onBack", "Lkotlin/Function0;", "onRegister", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "(Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app", "firstLogin", "email", HintConstants.AUTOFILL_HINT_USERNAME, "password", "remember", "showContent", "emblemScale", ""}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class LoginScreenKt {
    private static final long GovNavy = ColorKt.Color(4279384925L);
    private static final long GovGreen = ColorKt.Color(4279665487L);
    private static final long GovGold = ColorKt.Color(4290021938L);

    static final Unit LoginScreen$lambda$52(Function3 function3, Function3 function4, Function1 function1, Function0 function0, Function0 function2, String str, int i, Composer composer, int i2) {
        LoginScreen(function3, function4, function1, function0, function2, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LoginScreen(final Function3<? super String, ? super String, ? super Boolean, Unit> onLogin, final Function3<? super String, ? super String, ? super Boolean, Unit> onFirstLogin, final Function1<? super String, Unit> onResetPassword, final Function0<Unit> onBack, final Function0<Unit> onRegister, final String str, Composer composer, final int i) {
        int i2;
        String str2;
        Intrinsics.checkNotNullParameter(onLogin, "onLogin");
        Intrinsics.checkNotNullParameter(onFirstLogin, "onFirstLogin");
        Intrinsics.checkNotNullParameter(onResetPassword, "onResetPassword");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onRegister, "onRegister");
        Composer composerStartRestartGroup = composer.startRestartGroup(-703224513);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LoginScreen)P(3,2,5,1,4)48@1918L34,49@1970L31,50@2022L31,51@2074L31,52@2126L33,53@2183L34,54@2243L22,54@2222L43,55@2293L51,56@2385L214,62@2648L167,63@2832L83,63@2820L95,64@2920L5787:LoginScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onLogin) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onFirstLogin) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onResetPassword) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBack) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onRegister) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            str2 = str;
            i2 |= composerStartRestartGroup.changed(str2) ? 131072 : 65536;
        } else {
            str2 = str;
        }
        if ((74899 & i2) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-703224513, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen (LoginScreen.kt:47)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488882975, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488881314, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488879650, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488877986, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488876320, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488874495, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488872587, "CC(remember):LoginScreen.kt#9igjgp");
            LoginScreenKt$LoginScreen$1$1 loginScreenKt$LoginScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (loginScreenKt$LoginScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                loginScreenKt$LoginScreen$1$1RememberedValue = new LoginScreenKt$LoginScreen$1$1(mutableState6, null);
                composerStartRestartGroup.updateRememberedValue(loginScreenKt$LoginScreen$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) loginScreenKt$LoginScreen$1$1RememberedValue, composerStartRestartGroup, 6);
            final State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("emblem-breath", composerStartRestartGroup, 6, 0), 1.0f, 1.035f, AnimationSpecKt.m1117infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2200, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "emblem-scale", composerStartRestartGroup, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            long j = GovNavy;
            long j2 = GovGreen;
            final TextFieldColors textFieldColorsM2992colors0hiis_0 = outlinedTextFieldDefaults.m2992colors0hiis_0(j, j, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, j2, ColorKt.Color(4290365136L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, j2, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 54, 432, 3072, 0, 3072, 2139088892, 4095);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1488853678, "CC(remember):LoginScreen.kt#9igjgp");
            boolean z = (i2 & 7168) == 2048;
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LoginScreenKt.LoginScreen$lambda$21$lambda$20(onBack, mutableState, mutableState2, mutableState4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandlerKt.BackHandler(false, (Function0) objRememberedValue7, composerStartRestartGroup, 0, 1);
            Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4293850101L), null, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1213backgroundbw27NRU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4301constructorimpl = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1270760915, "C65@3033L21,65@2988L5713:LoginScreen.kt#ska5t9");
            Modifier modifierM1659paddingVpY3zN4 = PaddingKt.m1659paddingVpY3zN4(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), C1786Dp.m7249constructorimpl(20.0f), C1786Dp.m7249constructorimpl(28.0f));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1659paddingVpY3zN4);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -964796458, "C66@3169L30,67@3371L150,67@3212L309,70@3659L10,70@3534L672,77@4219L30,77@4251L92,77@4345L30,78@4513L10,78@4603L3441,78@4388L3656,97@8057L30,97@8089L106,97@8197L194,98@8404L30,98@8436L255:LoginScreen.kt#ska5t9");
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, LoginScreen$lambda$16(mutableState6), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(650, 0, null, 6, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m1053scaleInL8ZKhE$default(AnimationSpecKt.tween$default(LogSeverity.ALERT_VALUE, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.72f, 0L, 4, null)), (ExitTransition) null, (String) null, ComposableLambdaKt.rememberComposableLambda(1907620883, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LoginScreenKt.LoginScreen$lambda$51$lambda$50$lambda$22(stateAnimateFloat, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 1572870, 26);
            boolean zLoginScreen$lambda$16 = LoginScreen$lambda$16(mutableState6);
            EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(650, 180, null, 4, null), 0.0f, 2, null);
            TweenSpec tweenSpecTween = AnimationSpecKt.tween(650, 180, EasingKt.getFastOutSlowInEasing());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -585301627, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(LoginScreenKt.LoginScreen$lambda$51$lambda$50$lambda$24$lambda$23(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, zLoginScreen$lambda$16, (Modifier) null, enterTransitionFadeIn$default.plus(EnterExitTransitionKt.slideInVertically(tweenSpecTween, (Function1) objRememberedValue8)), (ExitTransition) null, (String) null, ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$1465886474$app(), composerStartRestartGroup, 1572870, 26);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(18.0f)), composerStartRestartGroup, 6);
            long j3 = GovGold;
            DividerKt.m2721HorizontalDivider9IZ8Weo(SizeKt.fillMaxWidth(Modifier.INSTANCE, 0.72f), C1786Dp.m7249constructorimpl(2.0f), j3, composerStartRestartGroup, 438, 0);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(18.0f)), composerStartRestartGroup, 6);
            boolean zLoginScreen$lambda$17 = LoginScreen$lambda$16(mutableState6);
            EnterTransition enterTransitionFadeIn$default2 = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(LogSeverity.ALERT_VALUE, 360, null, 4, null), 0.0f, 2, null);
            TweenSpec tweenSpecTween2 = AnimationSpecKt.tween(LogSeverity.ALERT_VALUE, 360, EasingKt.getFastOutSlowInEasing());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -585274299, "CC(remember):LoginScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(LoginScreenKt.LoginScreen$lambda$51$lambda$50$lambda$26$lambda$25(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EnterTransition enterTransitionPlus = enterTransitionFadeIn$default2.plus(EnterExitTransitionKt.slideInVertically(tweenSpecTween2, (Function1) objRememberedValue9));
            final String str3 = str2;
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2114113931, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LoginScreenKt.LoginScreen$lambda$51$lambda$50$lambda$49(textFieldColorsM2992colors0hiis_0, str3, onFirstLogin, onLogin, onResetPassword, onRegister, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54);
            composerStartRestartGroup = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, zLoginScreen$lambda$17, (Modifier) null, enterTransitionPlus, (ExitTransition) null, (String) null, composableLambdaRememberComposableLambda, composerStartRestartGroup, 1572870, 26);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(32.0f)), composerStartRestartGroup, 6);
            TextKt.m3342Text4IGK_g("منظومة داخلية لإدارة المستندات المالية وحركة الاعتماد", (Modifier) null, ColorKt.Color(4284773506L), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3462, 0, 131058);
            TextKt.m3342Text4IGK_g("النظام المالي الخاص بفرع صندوق النظافة والتحسين مديرية الحزم", (Modifier) null, j3, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m7131boximpl(TextAlign.INSTANCE.m7138getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 200070, 0, 130514);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(18.0f)), composerStartRestartGroup, 6);
            TextKt.m3342Text4IGK_g("تم برمجة وتطوير هذا النظام بواسطة المطور محمد الحزمي 2026", PaddingKt.m1660paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(10.0f), 0.0f, 2, null), j, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m7131boximpl(TextAlign.INSTANCE.m7138getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 200118, 0, 130512);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LoginScreenKt.LoginScreen$lambda$52(onLogin, onFirstLogin, onResetPassword, onBack, onRegister, str, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean LoginScreen$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LoginScreen$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String LoginScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String LoginScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String LoginScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean LoginScreen$lambda$13(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LoginScreen$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LoginScreen$lambda$16(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LoginScreen$lambda$17(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit LoginScreen$lambda$21$lambda$20(Function0 function0, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        if (LoginScreen$lambda$1(mutableState)) {
            LoginScreen$lambda$2(mutableState, false);
            mutableState2.setValue("");
            mutableState3.setValue("");
        } else {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    static final Unit LoginScreen$lambda$51$lambda$50$lambda$22(State state, AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation(composer, "C68@3395L43,68@3389L118:LoginScreen.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1907620883, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:68)");
        }
        ImageKt.Image(PainterResources_androidKt.painterResource(C2530R.drawable.official_emblem, composer, 0), "شعار الجمهورية اليمنية", ScaleKt.scale(SizeKt.m1703size3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(112.0f)), LoginScreen$lambda$19(state)), (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer, 48, MenuKt.InTransitionDuration);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final int LoginScreen$lambda$51$lambda$50$lambda$24$lambda$23(int i) {
        return i / 2;
    }

    static final int LoginScreen$lambda$51$lambda$50$lambda$26$lambda$25(int i) {
        return i / 3;
    }

    static final Unit LoginScreen$lambda$51$lambda$50$lambda$49(final TextFieldColors textFieldColors, final String str, final Function3 function3, final Function3 function4, final Function1 function1, final Function0 function0, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation(composer, "C79@4704L23,79@4754L19,79@4775L3255,79@4617L3413:LoginScreen.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2114113931, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:79)");
        }
        CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1941RoundedCornerShape0680j_4(C1786Dp.m7249constructorimpl(18.0f)), CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2478cardElevationaqJV_2Y(C1786Dp.m7249constructorimpl(5.0f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-121688835, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LoginScreenKt.LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48(textFieldColors, str, function3, function4, function1, function0, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, composer, 54), composer, 196614, 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48(TextFieldColors textFieldColors, final String str, final Function3 function3, final Function3 function4, final Function1 function1, Function0 function0, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, ColumnScope Card, Composer composer, int i) {
        String str2;
        byte b;
        int i2;
        final MutableState mutableState6;
        final MutableState mutableState7;
        final MutableState mutableState8;
        float f;
        Object obj;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C80@4793L3223:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-121688835, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:80)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(22.0f));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(10.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM1658padding3ABfNKs);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -101778173, "C81@4913L188,82@5122L233,83@5376L29,86@5954L17,86@5926L261,87@6208L248,89@6965L39,89@6724L102,89@7056L96,89@6707L445,90@7194L78,90@7173L246,91@7461L56,91@7519L123,91@7440L202,93@7861L137:LoginScreen.kt#ska5t9");
            String str3 = LoginScreen$lambda$1(mutableState) ? "تهيئة الدخول الأولى" : "دخول المستخدمين";
            long j = GovNavy;
            TextKt.m3342Text4IGK_g(str3, columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), j, TextUnitKt.getSp(22), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200064, 0, 131024);
            TextKt.m3342Text4IGK_g(LoginScreen$lambda$1(mutableState) ? "استخدم البريد المعتمد لربط اسم المستخدم بالحساب السحابي" : "الدخول اللاحق يتم باسم المستخدم وكلمة المرور", columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), ColorKt.Color(4284510336L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3456, 0, 131056);
            Composer composer2 = composer;
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(6.0f)), composer2, 6);
            if (LoginScreen$lambda$1(mutableState)) {
                composer2.startReplaceGroup(-101342345);
                ComposerKt.sourceInformation(composer2, "84@5467L14,84@5442L216");
                String strLoginScreen$lambda$4 = LoginScreen$lambda$4(mutableState2);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, -1111647167, "CC(remember):LoginScreen.kt#9igjgp");
                Object objRememberedValue = composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return LoginScreenKt.m824xe99c7112(mutableState2, (String) obj2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                str2 = "CC(remember):LoginScreen.kt#9igjgp";
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$4, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$581704104$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$1724221926$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer2, 102236592, 12582912, 0, 4062904);
                composer2 = composer2;
            } else {
                str2 = "CC(remember):LoginScreen.kt#9igjgp";
                composer2.startReplaceGroup(-106748497);
            }
            composer2.endReplaceGroup();
            if (LoginScreen$lambda$1(mutableState)) {
                composer2.startReplaceGroup(-106748497);
            } else {
                composer2.startReplaceGroup(-101090594);
                ComposerKt.sourceInformation(composer2, "85@5724L17,85@5696L209");
                String strLoginScreen$lambda$7 = LoginScreen$lambda$7(mutableState3);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, -1111638940, str2);
                Object objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return LoginScreenKt.m825x4a28806b(mutableState3, (String) obj2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$7, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.m7681getLambda$102591521$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.m7683getLambda$1654592355$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 102236592, 12582912, 0, 4062904);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
            String strLoginScreen$lambda$10 = LoginScreen$lambda$10(mutableState4);
            PasswordVisualTransformation passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer2, -1111631580, str2);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LoginScreenKt.m826x73f1e604(mutableState4, (String) obj2);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Composer composer3 = composer2;
            OutlinedTextFieldKt.OutlinedTextField(strLoginScreen$lambda$10, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$887911309$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$LoginScreenKt.INSTANCE.m7682getLambda$1165264565$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) passwordVisualTransformation, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer3, 102236592, 12582912, 0, 4046520);
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer3, 48);
            ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default4);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor2);
            } else {
                composer3.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composer3);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer3, -1602087355, "C87@6306L17,87@6351L31,87@6287L96,87@6385L69:LoginScreen.kt#ska5t9");
            boolean zLoginScreen$lambda$13 = LoginScreen$lambda$13(mutableState5);
            ComposerKt.sourceInformationMarkerStart(composer3, -744416440, str2);
            Object objRememberedValue4 = composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return LoginScreenKt.m827x21cae283(mutableState5, ((Boolean) obj2).booleanValue());
                    }
                };
                composer3.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            CheckboxDefaults checkboxDefaults = CheckboxDefaults.INSTANCE;
            long j2 = GovGreen;
            CheckboxKt.Checkbox(zLoginScreen$lambda$13, (Function1) objRememberedValue4, null, false, checkboxDefaults.m2497colors5tl4gsc(j2, 0L, 0L, 0L, 0L, 0L, composer, (CheckboxDefaults.$stable << 18) | 6, 62), null, composer, 48, 44);
            TextKt.m3342Text4IGK_g("تذكر الحساب على هذا الجهاز", (Modifier) null, j, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            Composer composer4 = composer;
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            if (str != null) {
                composer4.startReplaceGroup(-100297583);
                ComposerKt.sourceInformation(composer4, "88@6523L29,88@6590L96,88@6496L190");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(ColorKt.Color(4294963696L), 0L, 0L, 0L, composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1910166328, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return LoginScreenKt.m828xd8e9f991(str, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer, 54), composer, 196614, 26);
                composer4 = composer;
            } else {
                composer4.startReplaceGroup(-106748497);
            }
            composer4.endReplaceGroup();
            boolean z = !StringsKt.isBlank(LoginScreen$lambda$10(mutableState4)) && !(LoginScreen$lambda$1(mutableState) && StringsKt.isBlank(LoginScreen$lambda$4(mutableState2))) && (LoginScreen$lambda$1(mutableState) || !StringsKt.isBlank(LoginScreen$lambda$7(mutableState3)));
            ButtonColors buttonColorsM2457buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2457buttonColorsro_MJ88(j2, 0L, 0L, 0L, composer, (ButtonDefaults.$stable << 12) | 6, 14);
            Modifier modifierM1689height3ABfNKs = SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(50.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -1111606855, str2);
            boolean zChanged = composer.changed(function3) | composer.changed(function4);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                b = -106748497;
                i2 = 54;
                Function0 function2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LoginScreenKt.m829xf14e1690(function3, function4, mutableState, mutableState2, mutableState4, mutableState5, mutableState3);
                    }
                };
                mutableState6 = mutableState;
                composer.updateRememberedValue(function2);
                objRememberedValue5 = function2;
            } else {
                mutableState6 = mutableState;
                b = -106748497;
                i2 = 54;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            boolean z2 = z;
            String str4 = str2;
            ButtonKt.Button((Function0) objRememberedValue5, modifierM1689height3ABfNKs, z2, null, buttonColorsM2457buttonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1681385245, r15, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return LoginScreenKt.m830xd8e9f994(mutableState6, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer, i2), composer, 805306416, 488);
            ComposerKt.sourceInformationMarkerStart(composer, -1111591839, str4);
            boolean zChanged2 = composer.changed(function1);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                mutableState7 = mutableState;
                mutableState8 = mutableState3;
                objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LoginScreenKt.m831xe6bed8c0(function1, mutableState7, mutableState2, mutableState8);
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            } else {
                mutableState7 = mutableState;
                mutableState8 = mutableState3;
            }
            Function0 function5 = (Function0) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton(function5, null, !StringsKt.isBlank(LoginScreen$lambda$1(mutableState7) ? LoginScreen$lambda$4(mutableState2) : LoginScreen$lambda$7(mutableState8)), null, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$1513939440$app(), composer, 805306368, 506);
            ComposerKt.sourceInformationMarkerStart(composer, -1111583317, str4);
            Object objRememberedValue7 = composer.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LoginScreenKt.m832x10883e44(mutableState, mutableState2, mutableState4);
                    }
                };
                composer.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue7, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1872598489, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return LoginScreenKt.m833xd8e9f9ae(mutableState, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer, 54), composer, 805306374, 510);
            if (LoginScreen$lambda$1(mutableState)) {
                composer.startReplaceGroup(-1111576236);
                ComposerKt.sourceInformation(composer, "92@7704L49,92@7679L161");
                ComposerKt.sourceInformationMarkerStart(composer, -1111575548, str4);
                Object objRememberedValue8 = composer.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.LoginScreenKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return LoginScreenKt.m834xcf36568a(mutableState, mutableState2, mutableState4);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue8);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                f = 0.0f;
                obj = null;
                ButtonKt.OutlinedButton((Function0) objRememberedValue8, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.m7684getLambda$1913019591$app(), composer, 805306422, 508);
            } else {
                f = 0.0f;
                obj = null;
                composer.startReplaceGroup(-106748497);
            }
            composer.endReplaceGroup();
            ButtonKt.OutlinedButton(function0, SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, 1, obj), C1786Dp.m7249constructorimpl(48.0f)), false, null, null, null, null, null, null, ComposableSingletons$LoginScreenKt.INSTANCE.getLambda$97562341$app(), composer, 805306416, 508);
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

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$28$lambda$27 */
    static final Unit m824xe99c7112(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$30$lambda$29 */
    static final Unit m825x4a28806b(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$32$lambda$31 */
    static final Unit m826x73f1e604(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$35$lambda$34$lambda$33 */
    static final Unit m827x21cae283(MutableState mutableState, boolean z) {
        LoginScreen$lambda$14(mutableState, z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$36 */
    static final Unit m828xd8e9f991(String str, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C88@6592L92:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1910166328, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:88)");
            }
            TextKt.m3342Text4IGK_g(str, PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(10.0f)), ColorKt.Color(4288752162L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3504, 0, 131056);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$38$lambda$37 */
    static final Unit m829xf14e1690(Function3 function3, Function3 function4, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5) {
        if (LoginScreen$lambda$1(mutableState)) {
            function3.invoke(LoginScreen$lambda$4(mutableState2), LoginScreen$lambda$10(mutableState3), Boolean.valueOf(LoginScreen$lambda$13(mutableState4)));
        } else {
            function4.invoke(LoginScreen$lambda$7(mutableState5), LoginScreen$lambda$10(mutableState3), Boolean.valueOf(LoginScreen$lambda$13(mutableState4)));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$39 */
    static final Unit m830xd8e9f994(MutableState mutableState, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C89@7058L92:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1681385245, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:89)");
            }
            TextKt.m3342Text4IGK_g(LoginScreen$lambda$1(mutableState) ? "إنشاء الحساب والمتابعة" : "دخول آمن", (Modifier) null, 0L, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, ProfileVerifier.CompilationStatus.f255xf2722a21, 0, 131038);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$41$lambda$40 */
    static final Unit m831xe6bed8c0(Function1 function1, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        function1.invoke((!LoginScreen$lambda$1(mutableState) || StringsKt.isBlank(LoginScreen$lambda$4(mutableState2))) ? LoginScreen$lambda$7(mutableState3) : LoginScreen$lambda$4(mutableState2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$43$lambda$42 */
    static final Unit m832x10883e44(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        LoginScreen$lambda$2(mutableState, !LoginScreen$lambda$1(mutableState));
        mutableState2.setValue("");
        mutableState3.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$44 */
    static final Unit m833xd8e9f9ae(MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C91@7521L119:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1872598489, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LoginScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LoginScreen.kt:91)");
            }
            TextKt.m3342Text4IGK_g(LoginScreen$lambda$1(mutableState) ? "لدي حساب مهيأ — الدخول باسم المستخدم" : "الدخول لأول مرة بالبريد الإلكتروني", (Modifier) null, GovNavy, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 384, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: LoginScreen$lambda$51$lambda$50$lambda$49$lambda$48$lambda$47$lambda$46$lambda$45 */
    static final Unit m834xcf36568a(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        LoginScreen$lambda$2(mutableState, false);
        mutableState2.setValue("");
        mutableState3.setValue("");
        return Unit.INSTANCE;
    }

    private static final float LoginScreen$lambda$19(State<Float> state) {
        return state.getValue().floatValue();
    }
}
