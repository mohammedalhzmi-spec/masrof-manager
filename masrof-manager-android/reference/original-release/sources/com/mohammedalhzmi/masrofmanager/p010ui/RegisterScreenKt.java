package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.activity.compose.BackHandlerKt;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
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
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.CheckboxDefaults;
import androidx.compose.material3.CheckboxKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldDefaults;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.graphics.Color;
import androidx.compose.p000ui.graphics.ColorKt;
import androidx.compose.p000ui.graphics.Shape;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
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
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.profileinstaller.ProfileVerifier;
import com.google.firebase.messaging.Constants;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RegisterScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001aW\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0006X\u008a\u008e\u0002"}, m914d2 = {"RegisterScreen", "", "onRegister", "Lkotlin/Function6;", "", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "", "onBack", "Lkotlin/Function0;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "(Lkotlin/jvm/functions/Function6;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app", HintConstants.AUTOFILL_HINT_USERNAME, "fullName", "password", "confirm", "role", "agreeStorage", "agreeTerms"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class RegisterScreenKt {
    static final Unit RegisterScreen$lambda$51(Function6 function6, Function0 function0, String str, int i, Composer composer, int i2) {
        RegisterScreen(function6, function0, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void RegisterScreen(final Function6<? super String, ? super String, ? super String, ? super AppRole, ? super Boolean, ? super Boolean, Unit> onRegister, final Function0<Unit> onBack, final String str, Composer composer, final int i) {
        int i2;
        final String str2;
        final Function6<? super String, ? super String, ? super String, ? super AppRole, ? super Boolean, ? super Boolean, Unit> function6;
        final Function0<Unit> function0;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onRegister, "onRegister");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1944901030);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RegisterScreen)P(2,1)20@876L31,21@928L31,22@980L31,23@1031L31,24@1079L41,25@1145L34,26@1202L34,28@1305L28,29@1382L103,30@1490L3430:RegisterScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onRegister) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBack) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            str2 = str;
            function6 = onRegister;
            function0 = onBack;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1944901030, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen (RegisterScreen.kt:19)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739110855, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739109191, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739107527, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739105895, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739104349, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppRole.USER, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739102244, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -739100420, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final long jColor = ColorKt.Color(4279384925L);
            final long jColor2 = ColorKt.Color(4279665487L);
            BackHandlerKt.BackHandler(false, onBack, composerStartRestartGroup, i2 & 112, 1);
            final TextFieldColors textFieldColorsM2992colors0hiis_0 = OutlinedTextFieldDefaults.INSTANCE.m2992colors0hiis_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, null, jColor2, ColorKt.Color(4290365136L), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, jColor2, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, 432, 3072, 0, 3072, 2139088895, 4095);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 45140935, "C31@1646L21,31@1558L3356:RegisterScreen.kt#ska5t9");
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(18.0f)), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(9.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1010290677, "C32@1786L10,32@1734L121,33@1868L100,34@2068L23,34@2093L2811,34@1981L2923:RegisterScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("طلب إنشاء حساب مستخدم", (Modifier) null, jColor, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineSmall(), composerStartRestartGroup, 196998, 0, 65498);
            TextKt.m3342Text4IGK_g("يخضع الحساب للصلاحيات المعتمدة في منظومة الفرع.", (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3462, 0, 131058);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM1941RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1941RoundedCornerShape0680j_4(C1786Dp.m7249constructorimpl(16.0f));
            CardColors cardColorsM2477cardColorsro_MJ88 = CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14);
            Function3 function3 = new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return RegisterScreenKt.RegisterScreen$lambda$50$lambda$49$lambda$48(textFieldColorsM2992colors0hiis_0, jColor, str, jColor2, onRegister, onBack, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            str2 = str;
            function6 = onRegister;
            function0 = onBack;
            CardKt.Card(modifierFillMaxWidth$default, roundedCornerShapeM1941RoundedCornerShape0680j_4, cardColorsM2477cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(1555500872, true, function3, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 24);
            composer2 = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RegisterScreenKt.RegisterScreen$lambda$51(function6, function0, str2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String RegisterScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RegisterScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RegisterScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RegisterScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final AppRole RegisterScreen$lambda$13(MutableState<AppRole> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean RegisterScreen$lambda$16(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RegisterScreen$lambda$17(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean RegisterScreen$lambda$19(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RegisterScreen$lambda$20(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit RegisterScreen$lambda$50$lambda$49$lambda$48(TextFieldColors textFieldColors, final long j, final String str, long j2, final Function6 function6, Function0 function0, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, ColumnScope Card, Composer composer, int i) {
        boolean z;
        char c;
        int i2;
        AppRole[] appRoleArr;
        int i3;
        String str2;
        String str3;
        String str4;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C35@2111L2779:RegisterScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1555500872, i, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen.<anonymous>.<anonymous>.<anonymous> (RegisterScreen.kt:35)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(18.0f));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(9.0f));
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
            ComposerKt.sourceInformationMarkerStart(composer, -1496303996, "C36@2243L17,36@2215L252,37@2516L17,37@2488L158,38@2695L17,38@2667L211,39@2926L16,39@2899L215,40@3135L73,41@3229L386,42@3636L233,43@3890L228,45@4587L36,45@4485L76,45@4333L406,46@4829L43,46@4760L112:RegisterScreen.kt#ska5t9");
            String strRegisterScreen$lambda$1 = RegisterScreen$lambda$1(mutableState);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 2029940367, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m852xb016704b(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strRegisterScreen$lambda$1, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RegisterScreenKt.INSTANCE.m7697getLambda$863288872$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RegisterScreenKt.INSTANCE.getLambda$644614629$app(), false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 1573296, 12583296, 0, 4059064);
            String strRegisterScreen$lambda$4 = RegisterScreen$lambda$4(mutableState2);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 2029949103, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m853xd9dfd5cf(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strRegisterScreen$lambda$4, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RegisterScreenKt.INSTANCE.getLambda$1477668879$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 1573296, 12582912, 0, 4063160);
            String strRegisterScreen$lambda$7 = RegisterScreen$lambda$7(mutableState3);
            PasswordVisualTransformation passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 2029954831, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m854x3a93b53(mutableState3, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strRegisterScreen$lambda$7, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RegisterScreenKt.INSTANCE.getLambda$1580145872$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) passwordVisualTransformation, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 1573296, 12582912, 0, 4046776);
            String strRegisterScreen$lambda$10 = RegisterScreen$lambda$10(mutableState4);
            PasswordVisualTransformation passwordVisualTransformation2 = new PasswordVisualTransformation((char) 0, 1, null);
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, 2029962222, "CC(remember):RegisterScreen.kt#9igjgp");
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m855x2d72a0d7(mutableState4, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            String str5 = "CC(remember):RegisterScreen.kt#9igjgp";
            OutlinedTextFieldKt.OutlinedTextField(strRegisterScreen$lambda$10, (Function1<? super String, Unit>) objRememberedValue4, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RegisterScreenKt.INSTANCE.getLambda$1682622865$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) passwordVisualTransformation2, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 1573296, 12582912, 0, 4046776);
            TextKt.m3342Text4IGK_g("الدور الوظيفي المطلوب", (Modifier) null, j, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 196998, 0, 131034);
            final long j3 = j;
            Composer composer2 = composer;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            int i4 = 6;
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getStart(), composer2, 6);
            String str6 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str6);
            boolean z2 = false;
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, companion);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            String str7 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            byte b = -692256719;
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str7);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, 1730064746, "C:RegisterScreen.kt#ska5t9");
            composer2.startReplaceGroup(1025640392);
            ComposerKt.sourceInformation(composer2, "");
            AppRole[] appRoleArrValues = AppRole.values();
            int length = appRoleArrValues.length;
            int i5 = 0;
            while (i5 < length) {
                final AppRole appRole = appRoleArrValues[i5];
                if (appRole == RegisterScreen$lambda$13(mutableState5)) {
                    composer2.startReplaceGroup(-1367090090);
                    ComposerKt.sourceInformation(composer2, "41@3399L36,41@3358L15,41@3473L20,41@3341L152");
                    String str8 = str6;
                    str2 = str7;
                    i3 = i5;
                    appRoleArr = appRoleArrValues;
                    ButtonColors buttonColorsM2457buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2457buttonColorsro_MJ88(j2, 0L, 0L, 0L, composer, (ButtonDefaults.$stable << 12) | i4, 14);
                    composer2 = composer;
                    Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    String str9 = str5;
                    ComposerKt.sourceInformationMarkerStart(composer2, 371542723, str9);
                    boolean zChanged = composer2.changed(appRole.ordinal());
                    Object objRememberedValue5 = composer2.rememberedValue();
                    if (zChanged || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RegisterScreenKt.m856xa105d427(appRole, mutableState5);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue5);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) objRememberedValue5, modifierFillMaxWidth$default5, false, null, buttonColorsM2457buttonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1323800737, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return RegisterScreenKt.m857x446d8848(appRole, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer2, 54), composer2, 805306416, 492);
                    composer2.endReplaceGroup();
                    str3 = str8;
                    str4 = str9;
                } else {
                    appRoleArr = appRoleArrValues;
                    i3 = i5;
                    str2 = str7;
                    String str10 = str6;
                    String str11 = str5;
                    composer2.startReplaceGroup(371547332);
                    ComposerKt.sourceInformation(composer2, "41@3524L15,41@3577L34,41@3499L112");
                    ComposerKt.sourceInformationMarkerStart(composer2, 371548035, str11);
                    boolean zChanged2 = composer2.changed(appRole.ordinal());
                    Object objRememberedValue6 = composer2.rememberedValue();
                    if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RegisterScreenKt.m858x5fb3ec82(appRole, mutableState5);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue6);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2147067690, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return RegisterScreenKt.m859x446d884b(appRole, j3, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer2, 54);
                    str3 = str10;
                    str4 = str11;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue6, modifierFillMaxWidth$default6, false, null, null, null, null, null, null, composableLambdaRememberComposableLambda, composer2, 805306416, 508);
                    composer2.endReplaceGroup();
                }
                i5 = i3 + 1;
                str5 = str4;
                str6 = str3;
                appRoleArrValues = appRoleArr;
                length = length;
                i4 = i4;
                str7 = str2;
                z2 = false;
                b = -692256719;
                j3 = j;
            }
            String str12 = str7;
            String str13 = str5;
            String str14 = str6;
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer2, 48);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str14);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, companion2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str12);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor3);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, 809834960, "C42@3713L21,42@3762L28,42@3690L101,42@3793L74:RegisterScreen.kt#ska5t9");
            boolean zRegisterScreen$lambda$16 = RegisterScreen$lambda$16(mutableState6);
            ComposerKt.sourceInformationMarkerStart(composer2, -1359349033, str13);
            Object objRememberedValue7 = composer2.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m860x28e68114(mutableState6, ((Boolean) obj).booleanValue());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            CheckboxKt.Checkbox(zRegisterScreen$lambda$16, (Function1) objRememberedValue7, null, false, CheckboxDefaults.INSTANCE.m2497colors5tl4gsc(j2, 0L, 0L, 0L, 0L, 0L, composer, (CheckboxDefaults.$stable << 18) | 6, 62), null, composer, 48, 44);
            TextKt.m3342Text4IGK_g("أوافق على حفظ بيانات الحساب محليًا", (Modifier) null, j, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composer, 48);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, str14);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, companion3);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, str12);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl4 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 50963998, "C43@3965L19,43@4012L28,43@3944L97,43@4043L73:RegisterScreen.kt#ska5t9");
            boolean zRegisterScreen$lambda$19 = RegisterScreen$lambda$19(mutableState7);
            ComposerKt.sourceInformationMarkerStart(composer, 1248570508, str13);
            Object objRememberedValue8 = composer.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RegisterScreenKt.m861x4cae475c(mutableState7, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            boolean z3 = false;
            CheckboxKt.Checkbox(zRegisterScreen$lambda$19, (Function1) objRememberedValue8, null, false, CheckboxDefaults.INSTANCE.m2497colors5tl4gsc(j2, 0L, 0L, 0L, 0L, 0L, composer, (CheckboxDefaults.$stable << 18) | 6, 62), null, composer, 48, 44);
            TextKt.m3342Text4IGK_g("أوافق على الشروط وسياسة الاستخدام", (Modifier) null, j, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            Composer composer3 = composer;
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            if (str != null) {
                composer3.startReplaceGroup(2030001784);
                ComposerKt.sourceInformation(composer3, "44@4185L29,44@4216L96,44@4158L154");
                i2 = 6;
                composer3 = composer;
                z = true;
                c = '6';
                CardKt.Card(null, null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(ColorKt.Color(4294963696L), 0L, 0L, 0L, composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-245921909, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return RegisterScreenKt.RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$43(str, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer3, 54), composer3, ProfileVerifier.CompilationStatus.f255xf2722a21, 27);
            } else {
                z = true;
                c = '6';
                i2 = 6;
                composer3.startReplaceGroup(-1498584636);
            }
            composer3.endReplaceGroup();
            if (!StringsKt.isBlank(RegisterScreen$lambda$1(mutableState)) && !StringsKt.isBlank(RegisterScreen$lambda$4(mutableState2)) && RegisterScreen$lambda$7(mutableState3).length() >= i2 && Intrinsics.areEqual(RegisterScreen$lambda$7(mutableState3), RegisterScreen$lambda$10(mutableState4)) && RegisterScreen$lambda$16(mutableState6) && RegisterScreen$lambda$19(mutableState7)) {
                z3 = z;
            }
            ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
            int i6 = (ButtonDefaults.$stable << 12) | i2;
            boolean z4 = z;
            Composer composer4 = composer3;
            ButtonColors buttonColorsM2457buttonColorsro_MJ89 = buttonDefaults.m2457buttonColorsro_MJ88(j2, 0L, 0L, 0L, composer4, i6, 14);
            Modifier modifierM1689height3ABfNKs = SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z4 ? 1 : 0, null), C1786Dp.m7249constructorimpl(50.0f));
            ComposerKt.sourceInformationMarkerStart(composer4, 2030012170, str13);
            boolean zChanged3 = composer4.changed(function6);
            Object objRememberedValue9 = composer4.rememberedValue();
            if (zChanged3 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Function0 function1 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RegisterScreenKt.m862x7e27d38d(function6, mutableState, mutableState3, mutableState2, mutableState5, mutableState6, mutableState7);
                    }
                };
                composer4.updateRememberedValue(function1);
                objRememberedValue9 = function1;
            }
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ButtonKt.Button((Function0) objRememberedValue9, modifierM1689height3ABfNKs, z3, null, buttonColorsM2457buttonColorsro_MJ89, null, null, null, null, ComposableSingletons$RegisterScreenKt.INSTANCE.getLambda$845260718$app(), composer4, 805306416, 488);
            ButtonKt.OutlinedButton(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z4 ? 1 : 0, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1515650864, z4, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.RegisterScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return RegisterScreenKt.RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$46(j, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer4, 54), composer4, 805306416, 508);
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

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$22$lambda$21 */
    static final Unit m852xb016704b(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$24$lambda$23 */
    static final Unit m853xd9dfd5cf(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$26$lambda$25 */
    static final Unit m854x3a93b53(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$28$lambda$27 */
    static final Unit m855x2d72a0d7(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$36$lambda$35$lambda$30$lambda$29 */
    static final Unit m856xa105d427(AppRole appRole, MutableState mutableState) {
        mutableState.setValue(appRole);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$36$lambda$35$lambda$31 */
    static final Unit m857x446d8848(AppRole appRole, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C41@3475L16:RegisterScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1323800737, i, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RegisterScreen.kt:41)");
            }
            TextKt.m3342Text4IGK_g(appRole.getTitle(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$36$lambda$35$lambda$33$lambda$32 */
    static final Unit m858x5fb3ec82(AppRole appRole, MutableState mutableState) {
        mutableState.setValue(appRole);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$36$lambda$35$lambda$34 */
    static final Unit m859x446d884b(AppRole appRole, long j, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C41@3579L30:RegisterScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2147067690, i, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RegisterScreen.kt:41)");
            }
            TextKt.m3342Text4IGK_g(appRole.getTitle(), (Modifier) null, j, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 384, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$39$lambda$38$lambda$37 */
    static final Unit m860x28e68114(MutableState mutableState, boolean z) {
        RegisterScreen$lambda$17(mutableState, z);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$42$lambda$41$lambda$40 */
    static final Unit m861x4cae475c(MutableState mutableState, boolean z) {
        RegisterScreen$lambda$20(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$43(String str, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C44@4218L92:RegisterScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-245921909, i, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RegisterScreen.kt:44)");
            }
            TextKt.m3342Text4IGK_g(str, PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(10.0f)), ColorKt.Color(4288752162L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3504, 0, 131056);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$45$lambda$44 */
    static final Unit m862x7e27d38d(Function6 function6, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6) {
        function6.invoke(RegisterScreen$lambda$1(mutableState), RegisterScreen$lambda$7(mutableState2), RegisterScreen$lambda$4(mutableState3), RegisterScreen$lambda$13(mutableState4), Boolean.valueOf(RegisterScreen$lambda$16(mutableState5)), Boolean.valueOf(RegisterScreen$lambda$19(mutableState6)));
        return Unit.INSTANCE;
    }

    static final Unit RegisterScreen$lambda$50$lambda$49$lambda$48$lambda$47$lambda$46(long j, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C46@4831L39:RegisterScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1515650864, i, -1, "com.mohammedalhzmi.masrofmanager.ui.RegisterScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RegisterScreen.kt:46)");
            }
            TextKt.m3342Text4IGK_g("العودة إلى الدخول", (Modifier) null, j, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
