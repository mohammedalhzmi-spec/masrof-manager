package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.activity.compose.BackHandlerKt;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UsernameSetupScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a9\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\b¨\u0006\t²\u0006\n\u0010\n\u001a\u00020\u0004X\u008a\u008e\u0002"}, m914d2 = {"UsernameSetupScreen", "", "onConfirm", "Lkotlin/Function1;", "", "onBack", "Lkotlin/Function0;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app", HintConstants.AUTOFILL_HINT_USERNAME}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class UsernameSetupScreenKt {
    static final Unit UsernameSetupScreen$lambda$10(Function1 function1, Function0 function0, String str, int i, Composer composer, int i2) {
        UsernameSetupScreen(function1, function0, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void UsernameSetupScreen(final Function1<? super String, Unit> onConfirm, final Function0<Unit> onBack, final String str, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onConfirm, "onConfirm");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1050778475);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UsernameSetupScreen)P(2,1)17@667L31,20@770L28,21@803L1252:UsernameSetupScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onConfirm) ? 4 : 2) | i;
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
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1050778475, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreen (UsernameSetupScreen.kt:16)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -480795948, "CC(remember):UsernameSetupScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final long jColor = ColorKt.Color(4279384925L);
            final long jColor2 = ColorKt.Color(4279665487L);
            BackHandlerKt.BackHandler(false, onBack, composerStartRestartGroup, i2 & 112, 1);
            Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4293850101L), null, 2, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 375838451, "C22@1010L23,22@1035L1014,22@908L1141:UsernameSetupScreen.kt#ska5t9");
            composer2 = composerStartRestartGroup;
            CardKt.Card(PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(20.0f)), RoundedCornerShapeKt.m1941RoundedCornerShape0680j_4(C1786Dp.m7249constructorimpl(18.0f)), CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1825619853, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return UsernameSetupScreenKt.UsernameSetupScreen$lambda$9$lambda$8(jColor, str, jColor2, onConfirm, onBack, mutableState, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composer2, 196614, 24);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return UsernameSetupScreenKt.UsernameSetupScreen$lambda$10(onConfirm, onBack, str, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String UsernameSetupScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit UsernameSetupScreen$lambda$9$lambda$8(long j, String str, long j2, final Function1 function1, Function0 function0, final MutableState mutableState, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C23@1049L990:UsernameSetupScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1825619853, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreen.<anonymous>.<anonymous> (UsernameSetupScreen.kt:23)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(22.0f));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(12.0f));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, centerHorizontally, composer, 54);
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
            ComposerKt.sourceInformationMarkerStart(composer, -1259277559, "C24@1202L88,25@1307L133,26@1485L17,26@1457L135,28@1813L36,28@1718L30,28@1701L222,29@1940L85:UsernameSetupScreen.kt#ska5t9");
            boolean z = false;
            TextKt.m3342Text4IGK_g("إكمال إعداد الحساب", (Modifier) null, j, TextUnitKt.getSp(22), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 131026);
            TextKt.m3342Text4IGK_g("تم إنشاء الحساب بالبريد بنجاح. اختر اسم المستخدم الذي ستستعمله في الدخول القادم.", (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            String strUsernameSetupScreen$lambda$1 = UsernameSetupScreen$lambda$1(mutableState);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1287539596, "CC(remember):UsernameSetupScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return UsernameSetupScreenKt.UsernameSetupScreen$lambda$9$lambda$8$lambda$7$lambda$4$lambda$3(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strUsernameSetupScreen$lambda$1, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UsernameSetupScreenKt.INSTANCE.m7716getLambda$1812584931$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 12582912, 0, 8257464);
            Composer composer2 = composer;
            if (str != null) {
                composer2.startReplaceGroup(-1287534981);
                ComposerKt.sourceInformation(composer2, "27@1628L56");
                TextKt.m3342Text4IGK_g(str, (Modifier) null, ColorKt.Color(4288752162L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3456, 0, 131058);
                composer2 = composer;
            } else {
                composer2.startReplaceGroup(-1260496449);
            }
            composer2.endReplaceGroup();
            if (StringsKt.trim((CharSequence) UsernameSetupScreen$lambda$1(mutableState)).toString().length() >= 3) {
                z = true;
            }
            ButtonColors buttonColorsM2457buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2457buttonColorsro_MJ88(j2, 0L, 0L, 0L, composer, (ButtonDefaults.$stable << 12) | 6, 14);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1287532127, "CC(remember):UsernameSetupScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function1);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UsernameSetupScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UsernameSetupScreenKt.UsernameSetupScreen$lambda$9$lambda$8$lambda$7$lambda$6$lambda$5(function1, mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue2, modifierFillMaxWidth$default2, z, null, buttonColorsM2457buttonColorsro_MJ88, null, null, null, null, ComposableSingletons$UsernameSetupScreenKt.INSTANCE.getLambda$166631283$app(), composer, 805306416, 488);
            ButtonKt.OutlinedButton(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UsernameSetupScreenKt.INSTANCE.m7717getLambda$575041163$app(), composer, 805306416, 508);
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

    static final Unit UsernameSetupScreen$lambda$9$lambda$8$lambda$7$lambda$4$lambda$3(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit UsernameSetupScreen$lambda$9$lambda$8$lambda$7$lambda$6$lambda$5(Function1 function1, MutableState mutableState) {
        function1.invoke(StringsKt.trim((CharSequence) UsernameSetupScreen$lambda$1(mutableState)).toString());
        return Unit.INSTANCE;
    }
}
