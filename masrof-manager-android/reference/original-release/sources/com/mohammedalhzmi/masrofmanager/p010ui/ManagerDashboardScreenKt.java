package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.graphics.Color;
import androidx.compose.p000ui.graphics.ColorKt;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.p000ui.unit.C1786Dp;
import androidx.compose.p000ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.profileinstaller.ProfileVerifier;
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSync;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ManagerDashboardScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u001a\u001b\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005²\u0006\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u008a\u008e\u0002²\u0006\f\u0010\t\u001a\u0004\u0018\u00010\nX\u008a\u008e\u0002"}, m914d2 = {"ManagerDashboardScreen", "", "onOpenFinance", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "requests", "", "Lcom/mohammedalhzmi/masrofmanager/cloud/FirebaseCloudSync$DeviceRequest;", "message", ""}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class ManagerDashboardScreenKt {
    static final Unit ManagerDashboardScreen$lambda$19(Function0 function0, int i, Composer composer, int i2) {
        ManagerDashboardScreen(function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void ManagerDashboardScreen(Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        final MutableState mutableState;
        final MutableState mutableState2;
        final Function0<Unit> onOpenFinance = function0;
        Intrinsics.checkNotNullParameter(onOpenFinance, "onOpenFinance");
        Composer composerStartRestartGroup = composer.startRestartGroup(-600764791);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ManagerDashboardScreen)19@717L24,20@762L79,21@861L42,23@1072L13,23@1051L34,25@1154L3157:ManagerDashboardScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changedInstance(onOpenFinance) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-600764791, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreen (ManagerDashboardScreen.kt:18)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1809342280, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1809339149, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1809332426, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope);
            ManagerDashboardScreenKt$ManagerDashboardScreen$1$1 managerDashboardScreenKt$ManagerDashboardScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || managerDashboardScreenKt$ManagerDashboardScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                managerDashboardScreenKt$ManagerDashboardScreen$1$1RememberedValue = new ManagerDashboardScreenKt$ManagerDashboardScreen$1$1(coroutineScope, mutableState3, mutableState4, null);
                composerStartRestartGroup.updateRememberedValue(managerDashboardScreenKt$ManagerDashboardScreen$1$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) managerDashboardScreenKt$ManagerDashboardScreen$1$1RememberedValue, composerStartRestartGroup, 6);
            final long jColor = ColorKt.Color(4279384925L);
            final long jColor2 = ColorKt.Color(4279665487L);
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(BackgroundKt.m1213backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(4293850101L), null, 2, null), C1786Dp.m7249constructorimpl(16.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 114169015, "C26@1292L16,26@1240L470,33@1719L30,34@1758L643,38@2410L30,39@2449L90,40@2548L29,43@2977L1013,43@2895L1095,55@4024L13,55@3999L101,56@4109L29,57@4203L35,57@4147L158:ManagerDashboardScreen.kt#ska5t9");
            int i3 = i2;
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(jColor, 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableSingletons$ManagerDashboardScreenKt.INSTANCE.getLambda$1566917025$app(), composerStartRestartGroup, 196614, 26);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -474915942, "C35@1901L23,35@1926L200,35@1853L273,36@2187L23,36@2212L179,36@2139L252:ManagerDashboardScreen.kt#ska5t9");
            CardKt.Card(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1587665469, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$18$lambda$11$lambda$8(jColor, mutableState3, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProfileVerifier.CompilationStatus.f255xf2722a21, 26);
            CardKt.Card(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1731595750, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$18$lambda$11$lambda$10(jColor2, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProfileVerifier.CompilationStatus.f255xf2722a21, 26);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            TextKt.m3342Text4IGK_g("طلبات اعتماد الأجهزة", (Modifier) null, jColor, TextUnitKt.getSp(18), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 200070, 0, 131026);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(6.0f)), composerStartRestartGroup, 6);
            if (ManagerDashboardScreen$lambda$4(mutableState4) != null) {
                composerStartRestartGroup.startReplaceGroup(696460279);
                ComposerKt.sourceInformation(composerStartRestartGroup, "41@2607L68");
                String strManagerDashboardScreen$lambda$4 = ManagerDashboardScreen$lambda$4(mutableState4);
                if (strManagerDashboardScreen$lambda$4 == null) {
                    strManagerDashboardScreen$lambda$4 = "";
                }
                TextKt.m3342Text4IGK_g(strManagerDashboardScreen$lambda$4, (Modifier) null, ColorKt.Color(4288752162L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3456, 0, 131058);
            } else {
                composerStartRestartGroup.startReplaceGroup(112842927);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ManagerDashboardScreen$lambda$1(mutableState3).isEmpty()) {
                composerStartRestartGroup.startReplaceGroup(696463621);
                ComposerKt.sourceInformation(composerStartRestartGroup, "42@2760L23,42@2708L178");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableSingletons$ManagerDashboardScreenKt.INSTANCE.m7685getLambda$40892273$app(), composerStartRestartGroup, 196614, 26);
            } else {
                composerStartRestartGroup.startReplaceGroup(112842927);
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 696473064, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$18$lambda$15$lambda$14(mutableState3, jColor, jColor2, coroutineScope, mutableState4, (LazyListScope) obj);
                    }
                };
                mutableState = mutableState3;
                mutableState2 = mutableState4;
                composerStartRestartGroup.updateRememberedValue(function1);
                objRememberedValue4 = function1;
            } else {
                mutableState2 = mutableState4;
                mutableState = mutableState3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LazyDslKt.LazyColumn(modifierWeight$default, null, null, false, horizontalOrVerticalM1538spacedBy0680j_5, null, null, false, (Function1) objRememberedValue4, composerStartRestartGroup, 24576, 238);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 696505568, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(coroutineScope);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$18$lambda$17$lambda$16(coroutineScope, mutableState, mutableState2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue5, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$ManagerDashboardScreenKt.INSTANCE.getLambda$1591704993$app(), composerStartRestartGroup, 805306416, 508);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(6.0f)), composerStartRestartGroup, 6);
            onOpenFinance = function0;
            ButtonKt.Button(onOpenFinance, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, ButtonDefaults.INSTANCE.m2457buttonColorsro_MJ88(jColor, 0L, 0L, 0L, composerStartRestartGroup, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$ManagerDashboardScreenKt.INSTANCE.getLambda$246211171$app(), composerStartRestartGroup, (i3 & 14) | 805306416, 492);
            composerStartRestartGroup = composerStartRestartGroup;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$19(onOpenFinance, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<FirebaseCloudSync.DeviceRequest> ManagerDashboardScreen$lambda$1(MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState) {
        return mutableState.getValue();
    }

    private static final String ManagerDashboardScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ManagerDashboardScreen$refresh(CoroutineScope coroutineScope, MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState, MutableState<String> mutableState2) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ManagerDashboardScreenKt$ManagerDashboardScreen$refresh$1(mutableState, mutableState2, null), 3, null);
    }

    static final Unit ManagerDashboardScreen$lambda$18$lambda$11$lambda$8(long j, MutableState mutableState, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C35@1928L196:ManagerDashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1587665469, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreen.<anonymous>.<anonymous>.<anonymous> (ManagerDashboardScreen.kt:35)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(14.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
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
            ComposerKt.sourceInformationMarkerStart(composer, -490617156, "C35@1962L66,35@2030L92:ManagerDashboardScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("طلبات الأجهزة", (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            TextKt.m3342Text4IGK_g(String.valueOf(ManagerDashboardScreen$lambda$1(mutableState).size()), (Modifier) null, j, TextUnitKt.getSp(28), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200064, 0, 131026);
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

    static final Unit ManagerDashboardScreen$lambda$18$lambda$11$lambda$10(long j, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C36@2214L175:ManagerDashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1731595750, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreen.<anonymous>.<anonymous>.<anonymous> (ManagerDashboardScreen.kt:36)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(14.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
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
            ComposerKt.sourceInformationMarkerStart(composer, -36528536, "C36@2248L60,36@2310L77:ManagerDashboardScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("الحماية", (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            TextKt.m3342Text4IGK_g("مفعّلة", (Modifier) null, j, TextUnitKt.getSp(18), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 131026);
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

    static final Unit ManagerDashboardScreen$lambda$18$lambda$15$lambda$14(final MutableState mutableState, final long j, final long j2, final CoroutineScope coroutineScope, final MutableState mutableState2, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<FirebaseCloudSync.DeviceRequest> listManagerDashboardScreen$lambda$1 = ManagerDashboardScreen$lambda$1(mutableState);
        final Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$lambda$12((FirebaseCloudSync.DeviceRequest) obj);
            }
        };
        final C4052xf702021d c4052xf702021d = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(FirebaseCloudSync.DeviceRequest deviceRequest) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((FirebaseCloudSync.DeviceRequest) obj);
            }
        };
        LazyColumn.items(listManagerDashboardScreen$lambda$1.size(), new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function1.invoke(listManagerDashboardScreen$lambda$1.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return c4052xf702021d.invoke(listManagerDashboardScreen$lambda$1.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$$inlined$items$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                int i3;
                ComposerKt.sourceInformation(composer, "C152@7074L22:LazyDsl.kt#428nma");
                if ((i2 & 6) == 0) {
                    i3 = i2 | (composer.changed(lazyItemScope) ? 4 : 2);
                } else {
                    i3 = i2;
                }
                if ((i2 & 48) == 0) {
                    i3 |= composer.changed(i) ? 32 : 16;
                }
                if ((i3 & 147) == 146 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-632812321, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                }
                final FirebaseCloudSync.DeviceRequest deviceRequest = (FirebaseCloudSync.DeviceRequest) listManagerDashboardScreen$lambda$1.get(i);
                composer.startReplaceGroup(180999622);
                ComposerKt.sourceInformation(composer, "C*45@3105L23,45@3130L836,45@3053L913:ManagerDashboardScreen.kt#ska5t9");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                CardColors cardColorsM2477cardColorsro_MJ88 = CardDefaults.INSTANCE.m2477cardColorsro_MJ88(Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, 0L, 0L, composer, (CardDefaults.$stable << 12) | 6, 14);
                final long j3 = j;
                final long j4 = j2;
                final CoroutineScope coroutineScope2 = coroutineScope;
                final MutableState mutableState3 = mutableState;
                final MutableState mutableState4 = mutableState2;
                CardKt.Card(modifierFillMaxWidth$default, null, cardColorsM2477cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(116691450, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$2$2$1$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) {
                        invoke(columnScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ColumnScope Card, Composer composer2, int i4) {
                        Intrinsics.checkNotNullParameter(Card, "$this$Card");
                        ComposerKt.sourceInformation(composer2, "C46@3152L796:ManagerDashboardScreen.kt#ska5t9");
                        if ((i4 & 17) == 16 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(116691450, i4, -1, "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ManagerDashboardScreen.kt:46)");
                        }
                        Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(14.0f));
                        Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(5.0f));
                        final FirebaseCloudSync.DeviceRequest deviceRequest2 = deviceRequest;
                        long j5 = j3;
                        long j6 = j4;
                        final CoroutineScope coroutineScope3 = coroutineScope2;
                        final MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState5 = mutableState3;
                        final MutableState<String> mutableState6 = mutableState4;
                        ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer2, 6);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierM1658padding3ABfNKs);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM4301constructorimpl = Updater.m4301constructorimpl(composer2);
                        Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer2, -1638523483, "C47@3260L63,48@3348L82,49@3455L85,50@3827L36,50@3582L219,50@3565L361:ManagerDashboardScreen.kt#ska5t9");
                        TextKt.m3342Text4IGK_g(deviceRequest2.getEmail(), (Modifier) null, j5, 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 196992, 0, 131034);
                        TextKt.m3342Text4IGK_g("الجهاز: " + deviceRequest2.getDeviceName(), (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3456, 0, 131058);
                        TextKt.m3342Text4IGK_g("معرف الجهاز: " + deviceRequest2.getDeviceId(), (Modifier) null, ColorKt.Color(4284510336L), TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3456, 0, 131058);
                        ButtonColors buttonColorsM2457buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2457buttonColorsro_MJ88(j6, 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                        Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composer2, -1853961057, "CC(remember):ManagerDashboardScreen.kt#9igjgp");
                        boolean zChangedInstance = composer2.changedInstance(coroutineScope3) | composer2.changed(deviceRequest2);
                        Object objRememberedValue = composer2.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$2$2$1$2$1$1$1$1

                                /* JADX INFO: renamed from: com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$2$2$1$2$1$1$1$1$1 */
                                /* JADX INFO: compiled from: ManagerDashboardScreen.kt */
                                @Metadata(m913d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, m914d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
                                @DebugMetadata(m937c = "com.mohammedalhzmi.masrofmanager.ui.ManagerDashboardScreenKt$ManagerDashboardScreen$2$2$1$2$1$1$1$1$1", m938f = "ManagerDashboardScreen.kt", m939i = {0, 0, 0}, m940l = {51}, m941m = "invokeSuspend", m942n = {"$this$launch", "$this$invokeSuspend_u24lambda_u241", "$i$a$-runCatching-ManagerDashboardScreenKt$ManagerDashboardScreen$2$2$1$2$1$1$1$1$1$1"}, m943s = {"L$0", "L$4", "I$0"})
                                static final class C40511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ MutableState<String> $message$delegate;
                                    final /* synthetic */ FirebaseCloudSync.DeviceRequest $request;
                                    final /* synthetic */ MutableState<List<FirebaseCloudSync.DeviceRequest>> $requests$delegate;
                                    int I$0;
                                    private /* synthetic */ Object L$0;
                                    Object L$1;
                                    Object L$2;
                                    Object L$3;
                                    Object L$4;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C40511(FirebaseCloudSync.DeviceRequest deviceRequest, MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState, MutableState<String> mutableState2, Continuation<? super C40511> continuation) {
                                        super(2, continuation);
                                        this.$request = deviceRequest;
                                        this.$requests$delegate = mutableState;
                                        this.$message$delegate = mutableState2;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        C40511 c40511 = new C40511(this.$request, this.$requests$delegate, this.$message$delegate, continuation);
                                        c40511.L$0 = obj;
                                        return c40511;
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C40511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object objM7781constructorimpl;
                                        FirebaseCloudSync.DeviceRequest deviceRequest;
                                        MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState;
                                        MutableState<String> mutableState2;
                                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        try {
                                            if (i == 0) {
                                                ResultKt.throwOnFailure(obj);
                                                deviceRequest = this.$request;
                                                MutableState<List<FirebaseCloudSync.DeviceRequest>> mutableState3 = this.$requests$delegate;
                                                MutableState<String> mutableState4 = this.$message$delegate;
                                                Result.Companion companion = Result.INSTANCE;
                                                FirebaseCloudSync firebaseCloudSync = FirebaseCloudSync.INSTANCE;
                                                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                this.L$1 = deviceRequest;
                                                this.L$2 = mutableState3;
                                                this.L$3 = mutableState4;
                                                this.L$4 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                                                this.I$0 = 0;
                                                this.label = 1;
                                                if (firebaseCloudSync.approveDevice(deviceRequest, this) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                mutableState = mutableState3;
                                                mutableState2 = mutableState4;
                                            } else {
                                                if (i != 1) {
                                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                                }
                                                mutableState2 = (MutableState) this.L$3;
                                                mutableState = (MutableState) this.L$2;
                                                deviceRequest = (FirebaseCloudSync.DeviceRequest) this.L$1;
                                                ResultKt.throwOnFailure(obj);
                                            }
                                            List listManagerDashboardScreen$lambda$1 = ManagerDashboardScreenKt.ManagerDashboardScreen$lambda$1(mutableState);
                                            ArrayList arrayList = new ArrayList();
                                            for (Object obj2 : listManagerDashboardScreen$lambda$1) {
                                                if (!Intrinsics.areEqual(((FirebaseCloudSync.DeviceRequest) obj2).getId(), deviceRequest.getId())) {
                                                    arrayList.add(obj2);
                                                }
                                            }
                                            mutableState.setValue(arrayList);
                                            mutableState2.setValue("تم اعتماد الجهاز وإرسال إشعار للمستخدم");
                                            objM7781constructorimpl = Result.m7781constructorimpl(Unit.INSTANCE);
                                        } catch (Throwable th) {
                                            Result.Companion companion2 = Result.INSTANCE;
                                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                        }
                                        MutableState<String> mutableState5 = this.$message$delegate;
                                        Throwable thM7784exceptionOrNullimpl = Result.m7784exceptionOrNullimpl(objM7781constructorimpl);
                                        if (thM7784exceptionOrNullimpl != null) {
                                            mutableState5.setValue(thM7784exceptionOrNullimpl.getMessage());
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new C40511(deviceRequest2, mutableState5, mutableState6, null), 3, null);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ButtonKt.Button((Function0) objRememberedValue, modifierFillMaxWidth$default2, false, null, buttonColorsM2457buttonColorsro_MJ88, null, null, null, null, ComposableSingletons$ManagerDashboardScreenKt.INSTANCE.getLambda$1149730644$app(), composer2, 805306416, 492);
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
                }, composer, 54), composer, 196614, 26);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    static final Object ManagerDashboardScreen$lambda$18$lambda$15$lambda$14$lambda$12(FirebaseCloudSync.DeviceRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    static final Unit ManagerDashboardScreen$lambda$18$lambda$17$lambda$16(CoroutineScope coroutineScope, MutableState mutableState, MutableState mutableState2) {
        ManagerDashboardScreen$refresh(coroutineScope, mutableState, mutableState2);
        return Unit.INSTANCE;
    }
}
