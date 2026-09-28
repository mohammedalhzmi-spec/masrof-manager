package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import androidx.compose.foundation.interaction.MutableInteractionSource;
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
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenu_androidKt;
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
import androidx.compose.p000ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.input.VisualTransformation;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.p000ui.unit.C1786Dp;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DocumentBookScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\t\u001a\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\bX\u008a\u008e\u0002"}, m914d2 = {"DocumentBookScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "onNavigateBack", "Lkotlin/Function0;", "onOpenBook", "Lkotlin/Function1;", "", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "typeTitle", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "app", "expanded", "", "countText", "startText", "bookName", "message"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class DocumentBookScreenKt {

    /* JADX INFO: compiled from: DocumentBookScreen.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit DocumentBookScreen$lambda$44(MasrofViewModel masrofViewModel, Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        DocumentBookScreen(masrofViewModel, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void DocumentBookScreen(MasrofViewModel masrofViewModel, Function0<Unit> onNavigateBack, final Function1<? super String, Unit> onOpenBook, Composer composer, final int i) {
        int i2;
        float f;
        final Function0<Unit> function0;
        final Function1<? super String, Unit> function1;
        Composer composer2;
        final MasrofViewModel viewModel = masrofViewModel;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onNavigateBack, "onNavigateBack");
        Intrinsics.checkNotNullParameter(onOpenBook, "onOpenBook");
        Composer composerStartRestartGroup = composer.startRestartGroup(234357636);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentBookScreen)P(2)32@1504L47,33@1572L34,34@1628L33,35@1683L31,36@1735L43,37@1798L31,38@1890L7,41@1964L2968:DocumentBookScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onNavigateBack) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenBook) ? 256 : 128;
        }
        int i3 = i2;
        if ((i3 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            function1 = onOpenBook;
            viewModel = viewModel;
            function0 = onNavigateBack;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(234357636, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreen (DocumentBookScreen.kt:31)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879394579, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DocumentType.ORDER, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879396742, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879398533, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("10", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879400291, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879401967, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("دفتر مستندات", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1879403971, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            final int next = DocumentNumbering.INSTANCE.next(context, DocumentBookScreen$lambda$1(mutableState));
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(16.0f));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(10.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 910184608, "C42@2139L10,42@2057L108,43@2339L10,43@2174L186,44@2432L24,44@2458L518,44@2369L607,50@3013L17,50@2985L142,51@3136L454,55@3734L10,55@3599L157,56@3782L790,56@3765L882,69@4774L29,70@4812L114:DocumentBookScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("إنشاء دفتر مستندات مرقّم", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineMedium(), composerStartRestartGroup, 6, 0, 65534);
            TextKt.m3342Text4IGK_g("ينشئ صفحات محفوظة محليًا. افتح أي صفحة من القائمة لتعبئة بياناتها، ثم حدد صفحات الدفتر لتصديرها أو طباعتها.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65534);
            boolean zDocumentBookScreen$lambda$4 = DocumentBookScreen$lambda$4(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1414843270, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$19$lambda$18(mutableState2, ((Boolean) obj).booleanValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox(zDocumentBookScreen$lambda$4, (Function1) objRememberedValue7, null, ComposableLambdaKt.rememberComposableLambda(-426208392, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$30(mutableState, mutableState2, (ExposedDropdownMenuBoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 3120, 4);
            String strDocumentBookScreen$lambda$13 = DocumentBookScreen$lambda$13(mutableState5);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1414861855, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$32$lambda$31(mutableState5, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strDocumentBookScreen$lambda$13, (Function1<? super String, Unit>) objRememberedValue8, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DocumentBookScreenKt.INSTANCE.getLambda$1592417012$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573296, 12582912, 0, 8257464);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1523156828, "C52@3260L48,52@3231L162,53@3435L48,53@3406L174:DocumentBookScreen.kt#ska5t9");
            String strDocumentBookScreen$lambda$7 = DocumentBookScreen$lambda$7(mutableState3);
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -89412614, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$38$lambda$34$lambda$33(mutableState3, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strDocumentBookScreen$lambda$7, (Function1<? super String, Unit>) objRememberedValue9, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DocumentBookScreenKt.INSTANCE.m7678getLambda$1651758448$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572912, 12582912, 0, 8257464);
            String strDocumentBookScreen$lambda$10 = DocumentBookScreen$lambda$10(mutableState4);
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -89407014, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$38$lambda$37$lambda$36(mutableState4, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strDocumentBookScreen$lambda$10, (Function1<? super String, Unit>) objRememberedValue10, modifierWeight$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DocumentBookScreenKt.INSTANCE.m7677getLambda$1195544583$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572912, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            TextKt.m3342Text4IGK_g("الرقم التلقائي الحالي لهذا النوع: " + StringsKt.padStart(String.valueOf(next), 4, '0'), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getLabelSmall(), composerStartRestartGroup, 0, 0, 65534);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1414887236, "CC(remember):DocumentBookScreen.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(next) | composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context) | ((i3 & 896) == 256);
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                f = 0.0f;
                Function0 function2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$42$lambda$41(next, context, onOpenBook, mutableState3, mutableState4, mutableState5, viewModel, mutableState, mutableState6);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function2);
                objRememberedValue11 = function2;
            } else {
                f = 0.0f;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.Button((Function0) objRememberedValue11, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$DocumentBookScreenKt.INSTANCE.getLambda$1230787806$app(), composerStartRestartGroup, 805306416, 508);
            Composer composer3 = composerStartRestartGroup;
            if (StringsKt.isBlank(DocumentBookScreen$lambda$16(mutableState6))) {
                composer3.startReplaceGroup(908054132);
            } else {
                composer3.startReplaceGroup(1414915329);
                ComposerKt.sourceInformation(composer3, "68@4745L11,68@4682L83");
                TextKt.m3342Text4IGK_g(DocumentBookScreen$lambda$16(mutableState6), (Modifier) null, MaterialTheme.INSTANCE.getColorScheme(composer3, MaterialTheme.$stable).getPrimary(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 0, 0, 131066);
                composer3 = composer3;
            }
            composer3.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(6.0f)), composer3, 6);
            function0 = onNavigateBack;
            Composer composer4 = composer3;
            function1 = onOpenBook;
            ButtonKt.Button(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$DocumentBookScreenKt.INSTANCE.m7680getLambda$621844601$app(), composer4, ((i3 >> 3) & 14) | 805306416, 508);
            composer2 = composer4;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DocumentBookScreenKt.DocumentBookScreen$lambda$44(viewModel, function0, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final DocumentType DocumentBookScreen$lambda$1(MutableState<DocumentType> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean DocumentBookScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DocumentBookScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String DocumentBookScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String DocumentBookScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String DocumentBookScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String DocumentBookScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$19$lambda$18(MutableState mutableState, boolean z) {
        DocumentBookScreen$lambda$5(mutableState, !DocumentBookScreen$lambda$4(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$30(final MutableState mutableState, final MutableState mutableState2, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer composer, int i) {
        int i2;
        final MutableState mutableState3;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation(composer, "C45@2531L2,45@2599L54,45@2472L231,46@2769L20,46@2791L175,46@2716L250:DocumentBookScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = i | ((i & 8) == 0 ? composer.changed(ExposedDropdownMenuBox) : composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i2 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-426208392, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreen.<anonymous>.<anonymous> (DocumentBookScreen.kt:45)");
            }
            String strTypeTitle = typeTitle(DocumentBookScreen$lambda$1(mutableState));
            Modifier modifierMenuAnchor = ExposedDropdownMenuBox.menuAnchor(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null));
            ComposerKt.sourceInformationMarkerStart(composer, -1564369254, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$30$lambda$21$lambda$20((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strTypeTitle, (Function1<? super String, Unit>) objRememberedValue, modifierMenuAnchor, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DocumentBookScreenKt.INSTANCE.m7679getLambda$59631458$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1005717535, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$30$lambda$22(mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 806903856, 0, 0, 8388008);
            boolean zDocumentBookScreen$lambda$4 = DocumentBookScreen$lambda$4(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, -1564361620, "CC(remember):DocumentBookScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                mutableState3 = mutableState2;
                objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$30$lambda$24$lambda$23(mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState3 = mutableState2;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            AndroidMenu_androidKt.m2413DropdownMenuIlH_yew(zDocumentBookScreen$lambda$4, (Function0) objRememberedValue2, null, 0L, null, null, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-178587917, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return DocumentBookScreenKt.DocumentBookScreen$lambda$43$lambda$30$lambda$29(mutableState, mutableState3, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 48, 48, 2044);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$30$lambda$21$lambda$20(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$30$lambda$22(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C45@2629L22:DocumentBookScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1005717535, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreen.<anonymous>.<anonymous>.<anonymous> (DocumentBookScreen.kt:45)");
            }
            ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(DocumentBookScreen$lambda$4(mutableState), null, composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$30$lambda$24$lambda$23(MutableState mutableState) {
        DocumentBookScreen$lambda$5(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$30$lambda$29(final MutableState mutableState, final MutableState mutableState2, ColumnScope DropdownMenu, Composer composer, int i) {
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(DropdownMenu, "$this$DropdownMenu");
        ComposerKt.sourceInformation(composer2, "C*47@2875L27,47@2914L35,47@2851L99:DocumentBookScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-178587917, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreen.<anonymous>.<anonymous>.<anonymous> (DocumentBookScreen.kt:47)");
            }
            DocumentType[] documentTypeArrValues = DocumentType.values();
            int length = documentTypeArrValues.length;
            int i2 = 0;
            while (i2 < length) {
                final DocumentType documentType = documentTypeArrValues[i2];
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(259934487, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return DocumentBookScreenKt.m822x5ab75a79(documentType, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer2, 54);
                ComposerKt.sourceInformationMarkerStart(composer2, 271727274, "CC(remember):DocumentBookScreen.kt#9igjgp");
                boolean zChanged = composer2.changed(documentType.ordinal());
                Object objRememberedValue = composer2.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DocumentBookScreenKt.m823x9257558(documentType, mutableState, mutableState2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, false, null, null, null, composer2, 6, 508);
                i2++;
                composer2 = composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentBookScreen$lambda$43$lambda$30$lambda$29$lambda$28$lambda$25 */
    static final Unit m822x5ab75a79(DocumentType documentType, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C47@2877L23:DocumentBookScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(259934487, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentBookScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DocumentBookScreen.kt:47)");
            }
            TextKt.m3342Text4IGK_g(typeTitle(documentType), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentBookScreen$lambda$43$lambda$30$lambda$29$lambda$28$lambda$27$lambda$26 */
    static final Unit m823x9257558(DocumentType documentType, MutableState mutableState, MutableState mutableState2) {
        mutableState.setValue(documentType);
        DocumentBookScreen$lambda$5(mutableState2, false);
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$32$lambda$31(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$42$lambda$41(int i, Context context, Function1 function1, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MasrofViewModel masrofViewModel, MutableState mutableState4, MutableState mutableState5) {
        Integer intOrNull = StringsKt.toIntOrNull(DocumentBookScreen$lambda$7(mutableState));
        int iCoerceIn = intOrNull != null ? RangesKt.coerceIn(intOrNull.intValue(), 1, 200) : 10;
        Integer intOrNull2 = StringsKt.toIntOrNull(DocumentBookScreen$lambda$10(mutableState2));
        int iCoerceAtLeast = intOrNull2 != null ? RangesKt.coerceAtLeast(intOrNull2.intValue(), 1) : i;
        String string = StringsKt.trim((CharSequence) DocumentBookScreen$lambda$13(mutableState3)).toString();
        if (StringsKt.isBlank(string)) {
            string = "دفتر مستندات";
        }
        String str = "دفتر:" + ((Object) string);
        for (int i2 = 0; i2 < iCoerceIn; i2++) {
            masrofViewModel.addDocument(new Document(0L, DocumentBookScreen$lambda$1(mutableState4), StringsKt.padStart(String.valueOf(iCoerceAtLeast + i2), 4, '0'), "", "", null, null, "", "", "", "", DocumentStatus.DRAFT, 0, 0L, false, null, 0L, str, null, null, null, null, null, null, null, null, null, null, null, 536735745, null));
        }
        DocumentNumbering.INSTANCE.setStart(context, DocumentBookScreen$lambda$1(mutableState4), iCoerceAtLeast + iCoerceIn);
        mutableState5.setValue("تم إنشاء " + iCoerceIn + " صفحات مترابطة في دفتر واحد");
        function1.invoke(str);
        return Unit.INSTANCE;
    }

    private static final String typeTitle(DocumentType documentType) {
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        if (i == 1) {
            return "أمر صرف";
        }
        if (i == 2) {
            return "ورقة تقديم طلب";
        }
        if (i == 3) {
            return "ورقة استلام";
        }
        return documentType.name();
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$38$lambda$34$lambda$33(MutableState mutableState, String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        mutableState.setValue(StringsKt.take(sb.toString(), 3));
        return Unit.INSTANCE;
    }

    static final Unit DocumentBookScreen$lambda$43$lambda$38$lambda$37$lambda$36(MutableState mutableState, String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        mutableState.setValue(StringsKt.take(sb.toString(), 9));
        return Unit.INSTANCE;
    }
}
