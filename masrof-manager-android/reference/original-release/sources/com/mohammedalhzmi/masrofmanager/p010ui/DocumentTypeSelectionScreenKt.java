package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
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
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.data.ModelsKt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DocumentTypeSelectionScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m914d2 = {"DocumentTypeSelectionScreen", "", "allowedTypes", "", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "onTypeSelected", "Lkotlin/Function1;", "(Ljava/util/Set;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "app"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class DocumentTypeSelectionScreenKt {
    static final Unit DocumentTypeSelectionScreen$lambda$7(Set set, Function1 function1, int i, int i2, Composer composer, int i3) {
        DocumentTypeSelectionScreen(set, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void DocumentTypeSelectionScreen(Set<? extends DocumentType> set, final Function1<? super DocumentType, Unit> onTypeSelected, Composer composer, final int i, final int i2) {
        Set<? extends DocumentType> set2;
        int i3;
        final Set<? extends DocumentType> set3;
        Intrinsics.checkNotNullParameter(onTypeSelected, "onTypeSelected");
        Composer composerStartRestartGroup = composer.startRestartGroup(1649195428);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentTypeSelectionScreen)14@671L21,14@615L606:DocumentTypeSelectionScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                set2 = set;
                int i4 = composerStartRestartGroup.changedInstance(set2) ? 4 : 2;
                i3 = i | i4;
            } else {
                set2 = set;
            }
            i3 = i | i4;
        } else {
            set2 = set;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onTypeSelected) ? 32 : 16;
        }
        if ((i3 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            set3 = set2;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
            } else if ((i2 & 1) != 0) {
                set2 = ArraysKt.toSet(DocumentType.values());
                i3 &= -15;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1649195428, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentTypeSelectionScreen (DocumentTypeSelectionScreen.kt:13)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null), C1786Dp.m7249constructorimpl(16.0f));
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -581176826, "C15@781L10,15@720L87,16@816L41:DocumentTypeSelectionScreen.kt#ska5t9");
            set3 = set2;
            int i5 = i3;
            int i6 = 32;
            TextKt.m3342Text4IGK_g("اختر نوع المستند الجديد", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineMedium(), composerStartRestartGroup, 6, 0, 65534);
            composerStartRestartGroup = composerStartRestartGroup;
            int i7 = 6;
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(24.0f)), composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceGroup(673997267);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*19@1033L24,19@1095L56,19@1016L135,22@1164L41");
            DocumentType[] documentTypeArrValues = DocumentType.values();
            ArrayList arrayList = new ArrayList();
            for (DocumentType documentType : documentTypeArrValues) {
                if (set3.contains(documentType) && documentType != DocumentType.BOOK) {
                    arrayList.add(documentType);
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList<DocumentType> arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (hashSet.add(ModelsKt.displayName((DocumentType) obj))) {
                    arrayList2.add(obj);
                }
            }
            for (final DocumentType documentType2 : arrayList2) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -315001614, "CC(remember):DocumentTypeSelectionScreen.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(documentType2.ordinal()) | ((i5 & 112) == i6);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentTypeSelectionScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DocumentTypeSelectionScreenKt.DocumentTypeSelectionScreen$lambda$6$lambda$5$lambda$3$lambda$2(onTypeSelected, documentType2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(868354666, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentTypeSelectionScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return DocumentTypeSelectionScreenKt.DocumentTypeSelectionScreen$lambda$6$lambda$5$lambda$4(documentType2, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composerStartRestartGroup, 54);
                int i8 = i7;
                ButtonKt.Button((Function0) objRememberedValue, modifierFillMaxWidth$default, false, null, null, null, null, null, null, composableLambdaRememberComposableLambda, composerStartRestartGroup, 805306416, 508);
                SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, i8);
                i7 = i8;
                i6 = 32;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DocumentTypeSelectionScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DocumentTypeSelectionScreenKt.DocumentTypeSelectionScreen$lambda$7(set3, onTypeSelected, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit DocumentTypeSelectionScreen$lambda$6$lambda$5$lambda$3$lambda$2(Function1 function1, DocumentType documentType) {
        function1.invoke(documentType);
        return Unit.INSTANCE;
    }

    static final Unit DocumentTypeSelectionScreen$lambda$6$lambda$5$lambda$4(DocumentType documentType, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C20@1113L24:DocumentTypeSelectionScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(868354666, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentTypeSelectionScreen.<anonymous>.<anonymous>.<anonymous> (DocumentTypeSelectionScreen.kt:20)");
            }
            TextKt.m3342Text4IGK_g(ModelsKt.displayName(documentType), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
