package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.graphics.Shape;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.input.KeyboardType;
import androidx.compose.p000ui.text.input.PasswordVisualTransformation;
import androidx.compose.p000ui.text.input.PlatformImeOptions;
import androidx.compose.p000ui.text.input.VisualTransformation;
import androidx.compose.p000ui.text.intl.LocaleList;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.p000ui.unit.C1786Dp;
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
import com.google.firebase.firestore.model.Values;
import com.google.firebase.messaging.Constants;
import com.mohammedalhzmi.masrofmanager.util.LockType;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppLockScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\tH\u0007¢\u0006\u0002\u0010\u000b\u001a)\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\u000f¨\u0006\u0010²\u0006\n\u0010\u0011\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0005X\u008a\u008e\u0002"}, m914d2 = {"AppLockScreen", "", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/util/LockType;", "biometricAvailable", "", "onBiometric", "Lkotlin/Function0;", "onUnlock", "Lkotlin/Function1;", "", "(Lcom/mohammedalhzmi/masrofmanager/util/LockType;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "PatternPad", Values.VECTOR_MAP_VECTORS_KEY, "onTap", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "secret", Constants.IPC_BUNDLE_KEY_SEND_ERROR}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppLockScreenKt {

    /* JADX INFO: compiled from: AppLockScreen.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LockType.values().length];
            try {
                iArr[LockType.PATTERN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit AppLockScreen$lambda$16(LockType lockType, boolean z, Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        AppLockScreen(lockType, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PatternPad$lambda$22(String str, Function1 function1, int i, Composer composer, int i2) {
        PatternPad(str, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void AppLockScreen(final LockType type, final boolean z, final Function0<Unit> onBiometric, final Function1<? super String, Boolean> onUnlock, Composer composer, final int i) {
        int i2;
        boolean z2;
        int i3;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(onBiometric, "onBiometric");
        Intrinsics.checkNotNullParameter(onUnlock, "onUnlock");
        Composer composerStartRestartGroup = composer.startRestartGroup(994435923);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AppLockScreen)P(3)15@644L31,16@693L34,17@732L1580:AppLockScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(type.ordinal()) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBiometric) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onUnlock) ? 2048 : 1024;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(994435923, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.AppLockScreen (AppLockScreen.kt:14)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2124780142, "CC(remember):AppLockScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2124778571, "CC(remember):AppLockScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(24.0f));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1658padding3ABfNKs);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1043569781, "C18@948L10,18@893L80,19@982L30,20@1021L55,21@1085L30,34@1843L30,35@1899L57,35@1882L135:AppLockScreen.kt#ska5t9");
            int i4 = i2;
            TextKt.m3342Text4IGK_g("نظام مالية صندوق النظافة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineSmall(), composerStartRestartGroup, 6, 0, 65534);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            TextKt.m3342Text4IGK_g("التطبيق مقفل لحماية المستندات والبيانات المالية", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 6, 0, 131070);
            Composer composer2 = composerStartRestartGroup;
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(20.0f)), composer2, 6);
            if (WhenMappings.$EnumSwitchMapping$0[type.ordinal()] == 1) {
                composer2.startReplaceGroup(864955020);
                ComposerKt.sourceInformation(composer2, "23@1189L16,23@1170L35");
                String strAppLockScreen$lambda$1 = AppLockScreen$lambda$1(mutableState);
                ComposerKt.sourceInformationMarkerStart(composer2, 864955609, "CC(remember):AppLockScreen.kt#9igjgp");
                Object objRememberedValue3 = composer2.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppLockScreenKt.AppLockScreen$lambda$15$lambda$7$lambda$6(mutableState, (String) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                PatternPad(strAppLockScreen$lambda$1, (Function1) objRememberedValue3, composer2, 48);
                composer2.endReplaceGroup();
                z2 = false;
            } else {
                composer2.startReplaceGroup(864957284);
                ComposerKt.sourceInformation(composer2, "26@1309L30,27@1365L69,24@1226L507");
                String strAppLockScreen$lambda$2 = AppLockScreen$lambda$1(mutableState);
                ComposerKt.sourceInformationMarkerStart(composer2, 864959463, "CC(remember):AppLockScreen.kt#9igjgp");
                Object objRememberedValue4 = composer2.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppLockScreenKt.AppLockScreen$lambda$15$lambda$9$lambda$8(mutableState, mutableState2, (String) obj);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                Function1 function1 = (Function1) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                z2 = false;
                OutlinedTextFieldKt.OutlinedTextField(strAppLockScreen$lambda$2, (Function1<? super String, Unit>) function1, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1925938204, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AppLockScreenKt.AppLockScreen$lambda$15$lambda$10(type, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) new PasswordVisualTransformation((char) 0, 1, null), new KeyboardOptions(0, (Boolean) null, type == LockType.PIN ? KeyboardType.INSTANCE.m6972getNumberPasswordPjHm6EE() : KeyboardType.INSTANCE.m6973getPasswordPjHm6EE(), 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1572912, 12582912, 0, 8208316);
                composer2 = composer2;
                composer2.endReplaceGroup();
            }
            if (AppLockScreen$lambda$4(mutableState2)) {
                composer2.startReplaceGroup(864974032);
                ComposerKt.sourceInformation(composer2, "33@1816L11,33@1763L71");
                Composer composer3 = composer2;
                TextKt.m3342Text4IGK_g("بيانات القفل غير صحيحة", (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getError(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 6, 0, 131066);
                composer2 = composer3;
            } else {
                composer2.startReplaceGroup(1042639129);
            }
            composer2.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composer2, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, 864978370, "CC(remember):AppLockScreen.kt#9igjgp");
            boolean z3 = (i4 & 7168) == 2048 ? true : z2;
            Object objRememberedValue5 = composer2.rememberedValue();
            if (z3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppLockScreenKt.AppLockScreen$lambda$15$lambda$12$lambda$11(onUnlock, mutableState, mutableState2);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Composer composer4 = composer2;
            ButtonKt.Button((Function0) objRememberedValue5, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$AppLockScreenKt.INSTANCE.getLambda$837928121$app(), composer4, 805306416, 508);
            composerStartRestartGroup = composer4;
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1044677906);
                ComposerKt.sourceInformation(composerStartRestartGroup, "37@2064L111");
                int i5 = ((i4 >> 6) & 14) | 805306416;
                i3 = 1042639129;
                ButtonKt.OutlinedButton(onBiometric, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$AppLockScreenKt.INSTANCE.getLambda$475796493$app(), composerStartRestartGroup, i5, 508);
            } else {
                i3 = 1042639129;
                composerStartRestartGroup.startReplaceGroup(1042639129);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (type != LockType.PATTERN || AppLockScreen$lambda$1(mutableState).length() <= 0) {
                composerStartRestartGroup.startReplaceGroup(i3);
            } else {
                composerStartRestartGroup.startReplaceGroup(864989508);
                ComposerKt.sourceInformation(composerStartRestartGroup, "39@2268L15,39@2247L59");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 864990136, "CC(remember):AppLockScreen.kt#9igjgp");
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AppLockScreenKt.AppLockScreen$lambda$15$lambda$14$lambda$13(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.TextButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableSingletons$AppLockScreenKt.INSTANCE.getLambda$1236186521$app(), composerStartRestartGroup, 805306374, 510);
                composerStartRestartGroup = composerStartRestartGroup;
            }
            composerStartRestartGroup.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppLockScreenKt.AppLockScreen$lambda$16(type, z, onBiometric, onUnlock, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String AppLockScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AppLockScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AppLockScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit AppLockScreen$lambda$15$lambda$7$lambda$6(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(AppLockScreen$lambda$1(mutableState) + it);
        return Unit.INSTANCE;
    }

    static final Unit AppLockScreen$lambda$15$lambda$9$lambda$8(MutableState mutableState, MutableState mutableState2, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        AppLockScreen$lambda$5(mutableState2, false);
        return Unit.INSTANCE;
    }

    static final Unit AppLockScreen$lambda$15$lambda$10(LockType lockType, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C27@1367L65:AppLockScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1925938204, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppLockScreen.<anonymous>.<anonymous> (AppLockScreen.kt:27)");
            }
            TextKt.m3342Text4IGK_g(lockType == LockType.PIN ? "الرمز الرقمي" : "كلمة المرور", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit AppLockScreen$lambda$15$lambda$12$lambda$11(Function1 function1, MutableState mutableState, MutableState mutableState2) {
        AppLockScreen$lambda$5(mutableState2, !((Boolean) function1.invoke(AppLockScreen$lambda$1(mutableState))).booleanValue());
        return Unit.INSTANCE;
    }

    static final Unit AppLockScreen$lambda$15$lambda$14$lambda$13(MutableState mutableState) {
        mutableState.setValue("");
        return Unit.INSTANCE;
    }

    private static final void PatternPad(final String str, final Function1<? super String, Unit> function1, Composer composer, final int i) {
        int i2;
        String str2;
        Composer composerStartRestartGroup = composer.startRestartGroup(565455478);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PatternPad)P(1)45@2397L536:AppLockScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(565455478, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.PatternPad (AppLockScreen.kt:44)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2137432278, "C46@2466L90:AppLockScreen.kt#ska5t9");
            if (str.length() == 0) {
                str2 = "ارسم نقشًا من 4 نقاط على الأقل";
            } else {
                str2 = "النقاط: " + str.length();
            }
            int i3 = i2;
            String str3 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            String str4 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            String str5 = str2;
            int i4 = 0;
            TextKt.m3342Text4IGK_g(str5, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 0, 0, 131070);
            composerStartRestartGroup = composerStartRestartGroup;
            composerStartRestartGroup.startReplaceGroup(900236490);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*48@2597L320");
            int i5 = 0;
            while (true) {
                int i6 = 3;
                if (i5 >= 3) {
                    break;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                Modifier.Companion companion2 = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composerStartRestartGroup, i4);
                String str6 = str4;
                byte b = -1323940314;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str6);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i4);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                String str7 = str3;
                byte b2 = -692256719;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str7);
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
                Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2318381, "C:AppLockScreen.kt#ska5t9");
                composerStartRestartGroup.startReplaceGroup(-415567210);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*51@2741L68,51@2858L27,51@2716L169");
                int i7 = 0;
                while (i7 < i6) {
                    final int i8 = (i5 * 3) + i7 + 1;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -415563522, "CC(remember):AppLockScreen.kt#9igjgp");
                    boolean zChanged = ((i3 & 14) == 4) | composerStartRestartGroup.changed(i8) | ((i3 & 112) == 32);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AppLockScreenKt.PatternPad$lambda$21$lambda$20$lambda$18$lambda$17(str, i8, function1);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ButtonKt.OutlinedButton((Function0) objRememberedValue, SizeKt.m1703size3ABfNKs(PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(4.0f)), C1786Dp.m7249constructorimpl(72.0f)), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-595065114, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return AppLockScreenKt.PatternPad$lambda$21$lambda$20$lambda$19(i8, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306416, 508);
                    i7++;
                    i5 = i5;
                    i6 = 3;
                    b = -1323940314;
                    b2 = b2;
                    str7 = str7;
                    str6 = str6;
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                i5++;
                str3 = str7;
                str4 = str6;
                i4 = 0;
            }
            composerStartRestartGroup.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppLockScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppLockScreenKt.PatternPad$lambda$22(str, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PatternPad$lambda$21$lambda$20$lambda$18$lambda$17(String str, int i, Function1 function1) {
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) String.valueOf(i), false, 2, (Object) null)) {
            function1.invoke(String.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    static final Unit PatternPad$lambda$21$lambda$20$lambda$19(int i, RowScope OutlinedButton, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C51@2860L23:AppLockScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-595065114, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.PatternPad.<anonymous>.<anonymous>.<anonymous> (AppLockScreen.kt:51)");
            }
            TextKt.m3342Text4IGK_g(String.valueOf(i), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
