package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.print.PrintAttributes;
import android.print.PrintManager;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SliderKt;
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
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.view.ViewCompat;
import com.example.C2530R;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.AppPreferences;
import com.mohammedalhzmi.masrofmanager.util.DocumentHeader;
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter;
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentPrintAdapter;
import io.ktor.http.LinkHeader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PrintPreviewScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0011H\u0002\u001a\u0018\u0010\u0014\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0011H\u0002\u001a1\u0010\u0015\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003¢\u0006\u0002\u0010\u0018\u001a\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001a\u0015\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0011H\u0003¢\u0006\u0002\u0010\u001d¨\u0006\u001e²\u0006\u0010\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u008a\u0084\u0002²\u0006\n\u0010\u001f\u001a\u00020\u001bX\u008a\u008e\u0002²\u0006\n\u0010 \u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\"X\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\"X\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020\"X\u008a\u008e\u0002²\u0006\n\u0010%\u001a\u00020\"X\u008a\u008e\u0002²\u0006\n\u0010&\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020)X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020)X\u008a\u008e\u0002²\u0006\n\u0010+\u001a\u00020)X\u008a\u008e\u0002"}, m914d2 = {"PrintPreviewScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "documentIds", "", "onOpenEditor", "Lkotlin/Function1;", "", "onNavigateBack", "Lkotlin/Function0;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "sharePdf", "context", "Landroid/content/Context;", "docs", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "shareImage", "doc", "shareWord", "DocumentPageEditor", "documents", "onClose", "(Ljava/util/List;Landroid/content/Context;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "typeLabel", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "OfficialDocumentCard", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;Landroidx/compose/runtime/Composer;I)V", "app", "selectedType", "color", "opacity", "", "scale", "offsetX", "offsetY", "fontFamily", "textColor", "bold", "", "italic", "underline"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class PrintPreviewScreenKt {

    /* JADX INFO: compiled from: PrintPreviewScreen.kt */
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

    static final Unit DocumentPageEditor$lambda$139(List list, Context context, Function0 function0, int i, Composer composer, int i2) {
        DocumentPageEditor(list, context, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit OfficialDocumentCard$lambda$142(Document document, int i, Composer composer, int i2) {
        OfficialDocumentCard(document, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$38(MasrofViewModel masrofViewModel, String str, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        PrintPreviewScreen(masrofViewModel, str, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void PrintPreviewScreen(final MasrofViewModel viewModel, final String documentIds, final Function1<? super Long, Unit> onOpenEditor, final Function0<Unit> onNavigateBack, Composer composer, final int i) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(documentIds, "documentIds");
        Intrinsics.checkNotNullParameter(onOpenEditor, "onOpenEditor");
        Intrinsics.checkNotNullParameter(onNavigateBack, "onNavigateBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-771035373);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PrintPreviewScreen)P(3!1,2)27@1221L16,28@1252L89,30@1430L7,31@1442L7532:PrintPreviewScreen.kt#ska5t9");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(documentIds) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenEditor) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-771035373, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreen (PrintPreviewScreen.kt:26)");
            }
            State stateCollectAsState = SnapshotStateKt.collectAsState(viewModel.getAllDocuments(), null, composerStartRestartGroup, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2064355852, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean z = (i2 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                List listSplit$default = StringsKt.split$default((CharSequence) documentIds, new String[]{","}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList();
                Iterator it = listSplit$default.iterator();
                while (it.hasNext()) {
                    Long longOrNull = StringsKt.toLongOrNull((String) it.next());
                    if (longOrNull != null) {
                        arrayList.add(longOrNull);
                    }
                }
                objRememberedValue = CollectionsKt.toSet(arrayList);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Set set = (Set) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            List<Document> listPrintPreviewScreen$lambda$0 = PrintPreviewScreen$lambda$0(stateCollectAsState);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : listPrintPreviewScreen$lambda$0) {
                if (set.contains(Long.valueOf(((Document) obj).getId()))) {
                    arrayList2.add(obj);
                }
            }
            final ArrayList arrayList3 = arrayList2;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(16.0f));
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
            final ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -691142007, "C32@1548L10,32@1495L79,33@1703L10,33@1583L141,34@1733L621,39@2406L115,39@2363L158,43@2530L599,49@3168L5692,48@3138L5830:PrintPreviewScreen.kt#ska5t9");
            int i3 = i2;
            TextKt.m3342Text4IGK_g("معاينة النماذج الرسمية", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineMedium(), composerStartRestartGroup, 6, 0, 65534);
            TextKt.m3342Text4IGK_g("عدد الصفحات: " + arrayList3.size() + " — كل مستند محفوظ محليًا ويمكن تصديره منفردًا أو كمجموعة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 0, 0, 65534);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1583543645, "C35@1901L41,35@1839L161,36@2075L41,36@2013L163,37@2251L35,37@2189L155:PrintPreviewScreen.kt#ska5t9");
            ArrayList arrayList4 = arrayList3;
            boolean z2 = !arrayList4.isEmpty();
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1159462230, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            int i4 = i3 & 896;
            boolean zChangedInstance = (i4 == 256) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$10$lambda$5$lambda$4(onOpenEditor, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue2, modifierWeight$default, z2, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$587020767$app(), composerStartRestartGroup, 805306368, 504);
            boolean z3 = !arrayList4.isEmpty();
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1159467798, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(arrayList3) | (i4 == 256);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$10$lambda$7$lambda$6(onOpenEditor, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue3, modifierWeight$default2, z3, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1802454614$app(), composerStartRestartGroup, 805306368, 504);
            boolean z4 = !arrayList4.isEmpty();
            Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1159473424, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$10$lambda$9$lambda$8(context, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue4, modifierWeight$default3, z4, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7687getLambda$1844285225$app(), composerStartRestartGroup, 805306368, 504);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierWeight$default4 = ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1640294876, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$13$lambda$12(arrayList3, (LazyListScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LazyDslKt.LazyColumn(modifierWeight$default4, null, null, false, null, null, null, false, (Function1) objRememberedValue5, composerStartRestartGroup, 0, 254);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 528773916, "C44@2690L35,44@2636L146,45@2854L45,45@2795L158,46@3025L44,46@2966L153:PrintPreviewScreen.kt#ska5t9");
            boolean z5 = !arrayList4.isEmpty();
            Modifier modifierWeight$default5 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 17058503, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda40
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$20$lambda$15$lambda$14(context, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.Button((Function0) objRememberedValue6, modifierWeight$default5, z5, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1106291476$app(), composerStartRestartGroup, 805306368, 504);
            boolean z6 = arrayList3.size() == 1;
            Modifier modifierWeight$default6 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 17063761, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda41
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$20$lambda$17$lambda$16(context, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue7, modifierWeight$default6, z6, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1748026774$app(), composerStartRestartGroup, 805306368, 504);
            boolean z7 = arrayList3.size() == 1;
            Modifier modifierWeight$default7 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 17069232, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance7 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$20$lambda$19$lambda$18(context, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.OutlinedButton((Function0) objRememberedValue8, modifierWeight$default7, z7, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1548849229$app(), composerStartRestartGroup, 805306368, 504);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            boolean z8 = !arrayList4.isEmpty();
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1640324837, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(arrayList3);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance8 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.PrintPreviewScreen$lambda$37$lambda$36$lambda$35(context, columnScopeInstance, arrayList3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ButtonKt.Button((Function0) objRememberedValue9, modifierFillMaxWidth$default3, z8, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7689getLambda$2105326471$app(), composerStartRestartGroup, 805306416, 504);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.PrintPreviewScreen$lambda$38(viewModel, documentIds, onOpenEditor, onNavigateBack, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$10$lambda$5$lambda$4(Function1 function1, List list) {
        function1.invoke(Long.valueOf(((Document) CollectionsKt.first(list)).getId()));
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$10$lambda$7$lambda$6(Function1 function1, List list) {
        function1.invoke(Long.valueOf(((Document) CollectionsKt.first(list)).getId()));
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$10$lambda$9$lambda$8(Context context, List list) {
        sharePdf(context, list);
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$20$lambda$15$lambda$14(Context context, List list) {
        sharePdf(context, list);
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$20$lambda$17$lambda$16(Context context, List list) {
        shareImage(context, (Document) CollectionsKt.first(list));
        return Unit.INSTANCE;
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$20$lambda$19$lambda$18(Context context, List list) {
        shareWord(context, (Document) CollectionsKt.first(list));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:104:0x038c  */
    /* JADX WARN: Code duplicated, block: B:117:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:125:0x03fd  */
    /* JADX WARN: Code duplicated, block: B:152:0x020e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x01b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:50:0x018e  */
    /* JADX WARN: Code duplicated, block: B:55:0x0198 A[PHI: r16 r23
      0x0198: PHI (r16v10 char) = (r16v4 char), (r16v11 char) binds: [B:52:0x0191, B:54:0x0194] A[DONT_GENERATE, DONT_INLINE]
      0x0198: PHI (r23v7 char) = (r23v4 char), (r23v8 char) binds: [B:52:0x0191, B:54:0x0194] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:72:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:75:0x01f2  */
    /* JADX WARN: Code duplicated, block: B:93:0x0248  */
    /* JADX WARN: Code duplicated, block: B:96:0x024d  */
    static final Unit PrintPreviewScreen$lambda$37$lambda$36$lambda$35(Context context, ColumnScope columnScope, List list) {
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        Object objM7781constructorimpl3;
        char c;
        Object objM7781constructorimpl4;
        Bitmap bitmapDecodeResource;
        String strBackgroundImageUri;
        Object objM7781constructorimpl5;
        Bitmap bitmapDecodeResource2;
        InputStream inputStreamOpenInputStream;
        String strBackgroundImageUri2;
        Object objM7781constructorimpl6;
        Bitmap bitmapDecodeResource3;
        InputStream inputStreamOpenInputStream2;
        Object objM7781constructorimpl7;
        Integer numValueOf;
        int i;
        Object objM7781constructorimpl8;
        Integer numValueOf2;
        Object objM7781constructorimpl9;
        Integer numValueOf3;
        Object systemService = context.getSystemService("print");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.print.PrintManager");
        PrintManager printManager = (PrintManager) systemService;
        String strMinistry = AppPreferences.INSTANCE.ministry(context);
        String strAdministration = AppPreferences.INSTANCE.administration(context);
        String strBranch = AppPreferences.INSTANCE.branch(context);
        char c2 = 1;
        Map mapMapOf = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.loadLogo(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.loadLogo(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.loadLogo(context, DocumentType.RECEIPT)));
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.pageSize(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.pageSize(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.pageSize(context, DocumentType.RECEIPT)));
        Pair[] pairArr = new Pair[3];
        DocumentType documentType = DocumentType.ORDER;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.ORDER))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = -1;
        }
        pairArr[0] = TuplesKt.m921to(documentType, objM7781constructorimpl);
        DocumentType documentType2 = DocumentType.REQUEST;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.REQUEST))));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl2)) {
            objM7781constructorimpl2 = -1;
        }
        pairArr[1] = TuplesKt.m921to(documentType2, objM7781constructorimpl2);
        DocumentType documentType3 = DocumentType.RECEIPT;
        try {
            Result.Companion companion5 = Result.INSTANCE;
            objM7781constructorimpl3 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.RECEIPT))));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM7781constructorimpl3 = Result.m7781constructorimpl(ResultKt.createFailure(th3));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl3)) {
            objM7781constructorimpl3 = -1;
        }
        pairArr[2] = TuplesKt.m921to(documentType3, objM7781constructorimpl3);
        Map mapMapOf3 = MapsKt.mapOf(pairArr);
        Pair[] pairArr2 = new Pair[3];
        DocumentType documentType4 = DocumentType.ORDER;
        String strBackgroundImageUri3 = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.ORDER);
        try {
            try {
                try {
                    try {
                        if (strBackgroundImageUri3 != null) {
                            try {
                                Result.Companion companion7 = Result.INSTANCE;
                                c = 0;
                                try {
                                    InputStream inputStreamOpenInputStream3 = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri3));
                                    try {
                                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream3);
                                        CloseableKt.closeFinally(inputStreamOpenInputStream3, null);
                                        objM7781constructorimpl4 = Result.m7781constructorimpl(bitmapDecodeStream);
                                        c2 = 1;
                                    } catch (Throwable th4) {
                                        try {
                                            throw th4;
                                        } catch (Throwable th5) {
                                            try {
                                                CloseableKt.closeFinally(inputStreamOpenInputStream3, th4);
                                                throw th5;
                                            } catch (Throwable th6) {
                                                th = th6;
                                                Result.Companion companion8 = Result.INSTANCE;
                                                objM7781constructorimpl4 = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                                if (Result.m7787isFailureimpl(objM7781constructorimpl4)) {
                                                    objM7781constructorimpl4 = null;
                                                }
                                                bitmapDecodeResource = (Bitmap) objM7781constructorimpl4;
                                                if (bitmapDecodeResource == null) {
                                                    bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                                }
                                                pairArr2[c] = TuplesKt.m921to(documentType4, bitmapDecodeResource);
                                                DocumentType documentType5 = DocumentType.REQUEST;
                                                strBackgroundImageUri = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.REQUEST);
                                                if (strBackgroundImageUri != null) {
                                                    try {
                                                        Result.Companion companion9 = Result.INSTANCE;
                                                        inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri));
                                                        try {
                                                            Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                                                            CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                                                            objM7781constructorimpl5 = Result.m7781constructorimpl(bitmapDecodeStream2);
                                                        } catch (Throwable th7) {
                                                            try {
                                                                throw th7;
                                                            } catch (Throwable th8) {
                                                                CloseableKt.closeFinally(inputStreamOpenInputStream, th7);
                                                                throw th8;
                                                            }
                                                        }
                                                    } catch (Throwable th9) {
                                                        Result.Companion companion10 = Result.INSTANCE;
                                                        objM7781constructorimpl5 = Result.m7781constructorimpl(ResultKt.createFailure(th9));
                                                    }
                                                    if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
                                                        objM7781constructorimpl5 = null;
                                                    }
                                                    bitmapDecodeResource2 = (Bitmap) objM7781constructorimpl5;
                                                    if (bitmapDecodeResource2 == null) {
                                                        bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                                    }
                                                } else {
                                                    bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                                }
                                                pairArr2[c2] = TuplesKt.m921to(documentType5, bitmapDecodeResource2);
                                                DocumentType documentType6 = DocumentType.RECEIPT;
                                                strBackgroundImageUri2 = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.RECEIPT);
                                                if (strBackgroundImageUri2 != null) {
                                                    try {
                                                        Result.Companion companion11 = Result.INSTANCE;
                                                        inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri2));
                                                        try {
                                                            Bitmap bitmapDecodeStream3 = BitmapFactory.decodeStream(inputStreamOpenInputStream2);
                                                            CloseableKt.closeFinally(inputStreamOpenInputStream2, null);
                                                            objM7781constructorimpl6 = Result.m7781constructorimpl(bitmapDecodeStream3);
                                                        } catch (Throwable th10) {
                                                            try {
                                                                throw th10;
                                                            } catch (Throwable th11) {
                                                                CloseableKt.closeFinally(inputStreamOpenInputStream2, th10);
                                                                throw th11;
                                                            }
                                                        }
                                                    } catch (Throwable th12) {
                                                        Result.Companion companion12 = Result.INSTANCE;
                                                        objM7781constructorimpl6 = Result.m7781constructorimpl(ResultKt.createFailure(th12));
                                                    }
                                                    bitmapDecodeResource3 = (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl6) ? null : objM7781constructorimpl6);
                                                    if (bitmapDecodeResource3 == null) {
                                                        bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                                    }
                                                } else {
                                                    bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                                }
                                                pairArr2[2] = TuplesKt.m921to(documentType6, bitmapDecodeResource3);
                                                Map mapMapOf4 = MapsKt.mapOf(pairArr2);
                                                Pair[] pairArr3 = new Pair[3];
                                                pairArr3[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.ORDER)));
                                                pairArr3[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.REQUEST)));
                                                pairArr3[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.RECEIPT)));
                                                Map mapMapOf5 = MapsKt.mapOf(pairArr3);
                                                Pair[] pairArr4 = new Pair[3];
                                                pairArr4[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.ORDER)));
                                                pairArr4[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.REQUEST)));
                                                pairArr4[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.RECEIPT)));
                                                Map mapMapOf6 = MapsKt.mapOf(pairArr4);
                                                Pair[] pairArr5 = new Pair[3];
                                                pairArr5[c] = TuplesKt.m921to(DocumentType.ORDER, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.ORDER)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.ORDER))));
                                                pairArr5[c2] = TuplesKt.m921to(DocumentType.REQUEST, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.REQUEST)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.REQUEST))));
                                                pairArr5[2] = TuplesKt.m921to(DocumentType.RECEIPT, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.RECEIPT)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.RECEIPT))));
                                                Map mapMapOf7 = MapsKt.mapOf(pairArr5);
                                                Pair[] pairArr6 = new Pair[3];
                                                DocumentType documentType7 = DocumentType.ORDER;
                                                Result.Companion companion13 = Result.INSTANCE;
                                                objM7781constructorimpl7 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.ORDER))));
                                                numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
                                                if (Result.m7787isFailureimpl(objM7781constructorimpl7)) {
                                                    objM7781constructorimpl7 = numValueOf;
                                                }
                                                pairArr6[c] = TuplesKt.m921to(documentType7, objM7781constructorimpl7);
                                                DocumentType documentType8 = DocumentType.REQUEST;
                                                Result.Companion companion14 = Result.INSTANCE;
                                                i = -16777216;
                                                objM7781constructorimpl8 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.REQUEST))));
                                                numValueOf2 = Integer.valueOf(i);
                                                if (Result.m7787isFailureimpl(objM7781constructorimpl8)) {
                                                    objM7781constructorimpl8 = numValueOf2;
                                                }
                                                pairArr6[c2] = TuplesKt.m921to(documentType8, objM7781constructorimpl8);
                                                DocumentType documentType9 = DocumentType.RECEIPT;
                                                Result.Companion companion15 = Result.INSTANCE;
                                                objM7781constructorimpl9 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.RECEIPT))));
                                                numValueOf3 = Integer.valueOf(i);
                                                if (Result.m7787isFailureimpl(objM7781constructorimpl9)) {
                                                    objM7781constructorimpl9 = numValueOf3;
                                                }
                                                pairArr6[2] = TuplesKt.m921to(documentType9, objM7781constructorimpl9);
                                                Map mapMapOf8 = MapsKt.mapOf(pairArr6);
                                                Pair[] pairArr7 = new Pair[3];
                                                pairArr7[c] = TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.fontFamily(context, DocumentType.ORDER));
                                                pairArr7[c2] = TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.fontFamily(context, DocumentType.REQUEST));
                                                pairArr7[2] = TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.fontFamily(context, DocumentType.RECEIPT));
                                                Map mapMapOf9 = MapsKt.mapOf(pairArr7);
                                                Pair[] pairArr8 = new Pair[3];
                                                pairArr8[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.ORDER)));
                                                pairArr8[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.REQUEST)));
                                                pairArr8[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.RECEIPT)));
                                                Map mapMapOf10 = MapsKt.mapOf(pairArr8);
                                                Pair[] pairArr9 = new Pair[3];
                                                pairArr9[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.ORDER)));
                                                pairArr9[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.REQUEST)));
                                                pairArr9[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.RECEIPT)));
                                                Map mapMapOf11 = MapsKt.mapOf(pairArr9);
                                                Pair[] pairArr10 = new Pair[3];
                                                pairArr10[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.ORDER)));
                                                pairArr10[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.REQUEST)));
                                                pairArr10[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.RECEIPT)));
                                                printManager.print("مستندات مالية رسمية", new OfficialDocumentPrintAdapter(list, new DocumentHeader(strMinistry, strAdministration, strBranch, mapMapOf, mapMapOf2, mapMapOf3, mapMapOf4, mapMapOf5, mapMapOf6, mapMapOf7, mapMapOf8, mapMapOf9, mapMapOf10, mapMapOf11, MapsKt.mapOf(pairArr10)), context), new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build());
                                                return Unit.INSTANCE;
                                            }
                                        }
                                    }
                                } catch (Throwable th13) {
                                    th = th13;
                                    Result.Companion companion16 = Result.INSTANCE;
                                    objM7781constructorimpl4 = Result.m7781constructorimpl(ResultKt.createFailure(th));
                                    if (Result.m7787isFailureimpl(objM7781constructorimpl4)) {
                                        objM7781constructorimpl4 = null;
                                    }
                                    bitmapDecodeResource = (Bitmap) objM7781constructorimpl4;
                                    if (bitmapDecodeResource == null) {
                                        bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                    }
                                    pairArr2[c] = TuplesKt.m921to(documentType4, bitmapDecodeResource);
                                    DocumentType documentType10 = DocumentType.REQUEST;
                                    strBackgroundImageUri = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.REQUEST);
                                    if (strBackgroundImageUri != null) {
                                        Result.Companion companion17 = Result.INSTANCE;
                                        inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri));
                                        Bitmap bitmapDecodeStream4 = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                                        CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                                        objM7781constructorimpl5 = Result.m7781constructorimpl(bitmapDecodeStream4);
                                        if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
                                            objM7781constructorimpl5 = null;
                                        }
                                        bitmapDecodeResource2 = (Bitmap) objM7781constructorimpl5;
                                        if (bitmapDecodeResource2 == null) {
                                            bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                        }
                                    } else {
                                        bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                    }
                                    pairArr2[c2] = TuplesKt.m921to(documentType10, bitmapDecodeResource2);
                                    DocumentType documentType11 = DocumentType.RECEIPT;
                                    strBackgroundImageUri2 = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.RECEIPT);
                                    if (strBackgroundImageUri2 != null) {
                                        Result.Companion companion18 = Result.INSTANCE;
                                        inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri2));
                                        Bitmap bitmapDecodeStream5 = BitmapFactory.decodeStream(inputStreamOpenInputStream2);
                                        CloseableKt.closeFinally(inputStreamOpenInputStream2, null);
                                        objM7781constructorimpl6 = Result.m7781constructorimpl(bitmapDecodeStream5);
                                        bitmapDecodeResource3 = (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl6) ? null : objM7781constructorimpl6);
                                        if (bitmapDecodeResource3 == null) {
                                            bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                        }
                                    } else {
                                        bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                    }
                                    pairArr2[2] = TuplesKt.m921to(documentType11, bitmapDecodeResource3);
                                    Map mapMapOf12 = MapsKt.mapOf(pairArr2);
                                    Pair[] pairArr11 = new Pair[3];
                                    pairArr11[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.ORDER)));
                                    pairArr11[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.REQUEST)));
                                    pairArr11[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.RECEIPT)));
                                    Map mapMapOf13 = MapsKt.mapOf(pairArr11);
                                    Pair[] pairArr12 = new Pair[3];
                                    pairArr12[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.ORDER)));
                                    pairArr12[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.REQUEST)));
                                    pairArr12[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.RECEIPT)));
                                    Map mapMapOf14 = MapsKt.mapOf(pairArr12);
                                    Pair[] pairArr13 = new Pair[3];
                                    pairArr13[c] = TuplesKt.m921to(DocumentType.ORDER, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.ORDER)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.ORDER))));
                                    pairArr13[c2] = TuplesKt.m921to(DocumentType.REQUEST, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.REQUEST)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.REQUEST))));
                                    pairArr13[2] = TuplesKt.m921to(DocumentType.RECEIPT, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.RECEIPT)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.RECEIPT))));
                                    Map mapMapOf15 = MapsKt.mapOf(pairArr13);
                                    Pair[] pairArr14 = new Pair[3];
                                    DocumentType documentType12 = DocumentType.ORDER;
                                    Result.Companion companion19 = Result.INSTANCE;
                                    objM7781constructorimpl7 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.ORDER))));
                                    numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
                                    if (Result.m7787isFailureimpl(objM7781constructorimpl7)) {
                                        objM7781constructorimpl7 = numValueOf;
                                    }
                                    pairArr14[c] = TuplesKt.m921to(documentType12, objM7781constructorimpl7);
                                    DocumentType documentType13 = DocumentType.REQUEST;
                                    Result.Companion companion110 = Result.INSTANCE;
                                    i = -16777216;
                                    objM7781constructorimpl8 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.REQUEST))));
                                    numValueOf2 = Integer.valueOf(i);
                                    if (Result.m7787isFailureimpl(objM7781constructorimpl8)) {
                                        objM7781constructorimpl8 = numValueOf2;
                                    }
                                    pairArr14[c2] = TuplesKt.m921to(documentType13, objM7781constructorimpl8);
                                    DocumentType documentType14 = DocumentType.RECEIPT;
                                    Result.Companion companion111 = Result.INSTANCE;
                                    objM7781constructorimpl9 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.RECEIPT))));
                                    numValueOf3 = Integer.valueOf(i);
                                    if (Result.m7787isFailureimpl(objM7781constructorimpl9)) {
                                        objM7781constructorimpl9 = numValueOf3;
                                    }
                                    pairArr14[2] = TuplesKt.m921to(documentType14, objM7781constructorimpl9);
                                    Map mapMapOf16 = MapsKt.mapOf(pairArr14);
                                    Pair[] pairArr15 = new Pair[3];
                                    pairArr15[c] = TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.fontFamily(context, DocumentType.ORDER));
                                    pairArr15[c2] = TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.fontFamily(context, DocumentType.REQUEST));
                                    pairArr15[2] = TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.fontFamily(context, DocumentType.RECEIPT));
                                    Map mapMapOf17 = MapsKt.mapOf(pairArr15);
                                    Pair[] pairArr16 = new Pair[3];
                                    pairArr16[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.ORDER)));
                                    pairArr16[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.REQUEST)));
                                    pairArr16[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.RECEIPT)));
                                    Map mapMapOf18 = MapsKt.mapOf(pairArr16);
                                    Pair[] pairArr17 = new Pair[3];
                                    pairArr17[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.ORDER)));
                                    pairArr17[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.REQUEST)));
                                    pairArr17[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.RECEIPT)));
                                    Map mapMapOf19 = MapsKt.mapOf(pairArr17);
                                    Pair[] pairArr18 = new Pair[3];
                                    pairArr18[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.ORDER)));
                                    pairArr18[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.REQUEST)));
                                    pairArr18[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.RECEIPT)));
                                    printManager.print("مستندات مالية رسمية", new OfficialDocumentPrintAdapter(list, new DocumentHeader(strMinistry, strAdministration, strBranch, mapMapOf, mapMapOf2, mapMapOf3, mapMapOf12, mapMapOf13, mapMapOf14, mapMapOf15, mapMapOf16, mapMapOf17, mapMapOf18, mapMapOf19, MapsKt.mapOf(pairArr18)), context), new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build());
                                    return Unit.INSTANCE;
                                }
                            } catch (Throwable th14) {
                                th = th14;
                                c = 0;
                            }
                            if (Result.m7787isFailureimpl(objM7781constructorimpl4)) {
                                objM7781constructorimpl4 = null;
                            }
                            bitmapDecodeResource = (Bitmap) objM7781constructorimpl4;
                            if (bitmapDecodeResource == null) {
                            }
                            pairArr2[c] = TuplesKt.m921to(documentType4, bitmapDecodeResource);
                            DocumentType documentType15 = DocumentType.REQUEST;
                            strBackgroundImageUri = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.REQUEST);
                            if (strBackgroundImageUri != null) {
                                Result.Companion companion112 = Result.INSTANCE;
                                inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri));
                                Bitmap bitmapDecodeStream6 = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                                CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                                objM7781constructorimpl5 = Result.m7781constructorimpl(bitmapDecodeStream6);
                                if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
                                    objM7781constructorimpl5 = null;
                                }
                                bitmapDecodeResource2 = (Bitmap) objM7781constructorimpl5;
                                if (bitmapDecodeResource2 == null) {
                                    bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                }
                            } else {
                                bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                            }
                            pairArr2[c2] = TuplesKt.m921to(documentType15, bitmapDecodeResource2);
                            DocumentType documentType16 = DocumentType.RECEIPT;
                            strBackgroundImageUri2 = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.RECEIPT);
                            if (strBackgroundImageUri2 != null) {
                                Result.Companion companion113 = Result.INSTANCE;
                                inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri2));
                                Bitmap bitmapDecodeStream7 = BitmapFactory.decodeStream(inputStreamOpenInputStream2);
                                CloseableKt.closeFinally(inputStreamOpenInputStream2, null);
                                objM7781constructorimpl6 = Result.m7781constructorimpl(bitmapDecodeStream7);
                                bitmapDecodeResource3 = (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl6) ? null : objM7781constructorimpl6);
                                if (bitmapDecodeResource3 == null) {
                                    bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                                }
                            } else {
                                bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
                            }
                            pairArr2[2] = TuplesKt.m921to(documentType16, bitmapDecodeResource3);
                            Map mapMapOf110 = MapsKt.mapOf(pairArr2);
                            Pair[] pairArr19 = new Pair[3];
                            pairArr19[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.ORDER)));
                            pairArr19[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.REQUEST)));
                            pairArr19[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.RECEIPT)));
                            Map mapMapOf111 = MapsKt.mapOf(pairArr19);
                            Pair[] pairArr110 = new Pair[3];
                            pairArr110[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.ORDER)));
                            pairArr110[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.REQUEST)));
                            pairArr110[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.RECEIPT)));
                            Map mapMapOf112 = MapsKt.mapOf(pairArr110);
                            Pair[] pairArr111 = new Pair[3];
                            pairArr111[c] = TuplesKt.m921to(DocumentType.ORDER, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.ORDER)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.ORDER))));
                            pairArr111[c2] = TuplesKt.m921to(DocumentType.REQUEST, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.REQUEST)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.REQUEST))));
                            pairArr111[2] = TuplesKt.m921to(DocumentType.RECEIPT, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.RECEIPT)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.RECEIPT))));
                            Map mapMapOf113 = MapsKt.mapOf(pairArr111);
                            Pair[] pairArr112 = new Pair[3];
                            DocumentType documentType17 = DocumentType.ORDER;
                            Result.Companion companion114 = Result.INSTANCE;
                            objM7781constructorimpl7 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.ORDER))));
                            numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
                            if (Result.m7787isFailureimpl(objM7781constructorimpl7)) {
                                objM7781constructorimpl7 = numValueOf;
                            }
                            pairArr112[c] = TuplesKt.m921to(documentType17, objM7781constructorimpl7);
                            DocumentType documentType18 = DocumentType.REQUEST;
                            Result.Companion companion115 = Result.INSTANCE;
                            i = -16777216;
                            objM7781constructorimpl8 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.REQUEST))));
                            numValueOf2 = Integer.valueOf(i);
                            if (Result.m7787isFailureimpl(objM7781constructorimpl8)) {
                                objM7781constructorimpl8 = numValueOf2;
                            }
                            pairArr112[c2] = TuplesKt.m921to(documentType18, objM7781constructorimpl8);
                            DocumentType documentType19 = DocumentType.RECEIPT;
                            Result.Companion companion116 = Result.INSTANCE;
                            objM7781constructorimpl9 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.RECEIPT))));
                            numValueOf3 = Integer.valueOf(i);
                            if (Result.m7787isFailureimpl(objM7781constructorimpl9)) {
                                objM7781constructorimpl9 = numValueOf3;
                            }
                            pairArr112[2] = TuplesKt.m921to(documentType19, objM7781constructorimpl9);
                            Map mapMapOf114 = MapsKt.mapOf(pairArr112);
                            Pair[] pairArr113 = new Pair[3];
                            pairArr113[c] = TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.fontFamily(context, DocumentType.ORDER));
                            pairArr113[c2] = TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.fontFamily(context, DocumentType.REQUEST));
                            pairArr113[2] = TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.fontFamily(context, DocumentType.RECEIPT));
                            Map mapMapOf115 = MapsKt.mapOf(pairArr113);
                            Pair[] pairArr114 = new Pair[3];
                            pairArr114[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.ORDER)));
                            pairArr114[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.REQUEST)));
                            pairArr114[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.RECEIPT)));
                            Map mapMapOf116 = MapsKt.mapOf(pairArr114);
                            Pair[] pairArr115 = new Pair[3];
                            pairArr115[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.ORDER)));
                            pairArr115[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.REQUEST)));
                            pairArr115[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.RECEIPT)));
                            Map mapMapOf117 = MapsKt.mapOf(pairArr115);
                            Pair[] pairArr116 = new Pair[3];
                            pairArr116[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.ORDER)));
                            pairArr116[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.REQUEST)));
                            pairArr116[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.RECEIPT)));
                            printManager.print("مستندات مالية رسمية", new OfficialDocumentPrintAdapter(list, new DocumentHeader(strMinistry, strAdministration, strBranch, mapMapOf, mapMapOf2, mapMapOf3, mapMapOf110, mapMapOf111, mapMapOf112, mapMapOf113, mapMapOf114, mapMapOf115, mapMapOf116, mapMapOf117, MapsKt.mapOf(pairArr116)), context), new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build());
                            return Unit.INSTANCE;
                        }
                        c = 0;
                        c2 = 1;
                        Result.Companion companion117 = Result.INSTANCE;
                        objM7781constructorimpl7 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.ORDER))));
                    } catch (Throwable th15) {
                        Result.Companion companion20 = Result.INSTANCE;
                        objM7781constructorimpl7 = Result.m7781constructorimpl(ResultKt.createFailure(th15));
                    }
                    objM7781constructorimpl8 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.REQUEST))));
                } catch (Throwable th16) {
                    th = th16;
                    Result.Companion companion21 = Result.INSTANCE;
                    objM7781constructorimpl8 = Result.m7781constructorimpl(ResultKt.createFailure(th));
                }
                Result.Companion companion118 = Result.INSTANCE;
                i = -16777216;
            } catch (Throwable th17) {
                th = th17;
                i = -16777216;
            }
            Result.Companion companion119 = Result.INSTANCE;
            objM7781constructorimpl9 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.RECEIPT))));
        } catch (Throwable th18) {
            Result.Companion companion22 = Result.INSTANCE;
            objM7781constructorimpl9 = Result.m7781constructorimpl(ResultKt.createFailure(th18));
        }
        bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
        pairArr2[c] = TuplesKt.m921to(documentType4, bitmapDecodeResource);
        DocumentType documentType110 = DocumentType.REQUEST;
        strBackgroundImageUri = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.REQUEST);
        if (strBackgroundImageUri != null) {
            Result.Companion companion1110 = Result.INSTANCE;
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri));
            Bitmap bitmapDecodeStream8 = BitmapFactory.decodeStream(inputStreamOpenInputStream);
            CloseableKt.closeFinally(inputStreamOpenInputStream, null);
            objM7781constructorimpl5 = Result.m7781constructorimpl(bitmapDecodeStream8);
            if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
                objM7781constructorimpl5 = null;
            }
            bitmapDecodeResource2 = (Bitmap) objM7781constructorimpl5;
            if (bitmapDecodeResource2 == null) {
                bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
            }
        } else {
            bitmapDecodeResource2 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
        }
        pairArr2[c2] = TuplesKt.m921to(documentType110, bitmapDecodeResource2);
        DocumentType documentType111 = DocumentType.RECEIPT;
        strBackgroundImageUri2 = AppPreferences.INSTANCE.backgroundImageUri(context, DocumentType.RECEIPT);
        if (strBackgroundImageUri2 != null) {
            Result.Companion companion1111 = Result.INSTANCE;
            inputStreamOpenInputStream2 = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri2));
            Bitmap bitmapDecodeStream9 = BitmapFactory.decodeStream(inputStreamOpenInputStream2);
            CloseableKt.closeFinally(inputStreamOpenInputStream2, null);
            objM7781constructorimpl6 = Result.m7781constructorimpl(bitmapDecodeStream9);
            bitmapDecodeResource3 = (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl6) ? null : objM7781constructorimpl6);
            if (bitmapDecodeResource3 == null) {
                bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
            }
        } else {
            bitmapDecodeResource3 = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
        }
        pairArr2[2] = TuplesKt.m921to(documentType111, bitmapDecodeResource3);
        Map mapMapOf118 = MapsKt.mapOf(pairArr2);
        Pair[] pairArr117 = new Pair[3];
        pairArr117[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.ORDER)));
        pairArr117[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.REQUEST)));
        pairArr117[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.RECEIPT)));
        Map mapMapOf119 = MapsKt.mapOf(pairArr117);
        Pair[] pairArr118 = new Pair[3];
        pairArr118[c] = TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.ORDER)));
        pairArr118[c2] = TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.REQUEST)));
        pairArr118[2] = TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.RECEIPT)));
        Map mapMapOf1110 = MapsKt.mapOf(pairArr118);
        Pair[] pairArr119 = new Pair[3];
        pairArr119[c] = TuplesKt.m921to(DocumentType.ORDER, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.ORDER)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.ORDER))));
        pairArr119[c2] = TuplesKt.m921to(DocumentType.REQUEST, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.REQUEST)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.REQUEST))));
        pairArr119[2] = TuplesKt.m921to(DocumentType.RECEIPT, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.RECEIPT)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.RECEIPT))));
        Map mapMapOf1111 = MapsKt.mapOf(pairArr119);
        Pair[] pairArr1110 = new Pair[3];
        DocumentType documentType112 = DocumentType.ORDER;
        numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        if (Result.m7787isFailureimpl(objM7781constructorimpl7)) {
            objM7781constructorimpl7 = numValueOf;
        }
        pairArr1110[c] = TuplesKt.m921to(documentType112, objM7781constructorimpl7);
        DocumentType documentType113 = DocumentType.REQUEST;
        numValueOf2 = Integer.valueOf(i);
        if (Result.m7787isFailureimpl(objM7781constructorimpl8)) {
            objM7781constructorimpl8 = numValueOf2;
        }
        pairArr1110[c2] = TuplesKt.m921to(documentType113, objM7781constructorimpl8);
        DocumentType documentType114 = DocumentType.RECEIPT;
        numValueOf3 = Integer.valueOf(i);
        if (Result.m7787isFailureimpl(objM7781constructorimpl9)) {
            objM7781constructorimpl9 = numValueOf3;
        }
        pairArr1110[2] = TuplesKt.m921to(documentType114, objM7781constructorimpl9);
        Map mapMapOf1112 = MapsKt.mapOf(pairArr1110);
        Pair[] pairArr1111 = new Pair[3];
        pairArr1111[c] = TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.fontFamily(context, DocumentType.ORDER));
        pairArr1111[c2] = TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.fontFamily(context, DocumentType.REQUEST));
        pairArr1111[2] = TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.fontFamily(context, DocumentType.RECEIPT));
        Map mapMapOf1113 = MapsKt.mapOf(pairArr1111);
        Pair[] pairArr1112 = new Pair[3];
        pairArr1112[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.ORDER)));
        pairArr1112[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.REQUEST)));
        pairArr1112[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.RECEIPT)));
        Map mapMapOf1114 = MapsKt.mapOf(pairArr1112);
        Pair[] pairArr1113 = new Pair[3];
        pairArr1113[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.ORDER)));
        pairArr1113[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.REQUEST)));
        pairArr1113[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.RECEIPT)));
        Map mapMapOf1115 = MapsKt.mapOf(pairArr1113);
        Pair[] pairArr1114 = new Pair[3];
        pairArr1114[c] = TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.ORDER)));
        pairArr1114[c2] = TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.REQUEST)));
        pairArr1114[2] = TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.RECEIPT)));
        printManager.print("مستندات مالية رسمية", new OfficialDocumentPrintAdapter(list, new DocumentHeader(strMinistry, strAdministration, strBranch, mapMapOf, mapMapOf2, mapMapOf3, mapMapOf118, mapMapOf119, mapMapOf1110, mapMapOf1111, mapMapOf1112, mapMapOf1113, mapMapOf1114, mapMapOf1115, MapsKt.mapOf(pairArr1114)), context), new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build());
        return Unit.INSTANCE;
    }

    private static final void sharePdf(Context context, List<Document> list) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportPdf(context, list), "مشاركة المستندات الرسمية PDF");
    }

    private static final void shareImage(Context context, Document document) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportPng(context, document), "مشاركة المستند كصورة");
    }

    private static final void shareWord(Context context, Document document) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportDocx(context, document), "مشاركة ملف DOCX");
    }

    private static final void DocumentPageEditor(final List<Document> list, final Context context, Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composer2;
        final Context context2;
        DocumentType type;
        final Function0<Unit> function1 = function0;
        Composer composerStartRestartGroup = composer.startRestartGroup(-200517420);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentPageEditor)P(1)114@9713L80,115@9811L96,116@9927L98,117@10043L96,118@10159L98,119@10277L98,120@10398L91,121@10511L90,122@10618L89,123@10726L91,124@10839L94,125@11095L247,125@10987L355,151@14721L764,151@15503L51,126@11427L3276,126@11347L4208:PrintPreviewScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(context) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            context2 = context;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-200517420, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor (PrintPreviewScreen.kt:113)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576292100, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Document document = (Document) CollectionsKt.firstOrNull((List) list);
                if (document == null || (type = document.getType()) == null) {
                    type = DocumentType.ORDER;
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(type, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$40 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576295252, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$40.ordinal());
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.backgroundColor(context, DocumentPageEditor$lambda$40(mutableState)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$41 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576298966, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$41.ordinal());
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$42 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576302676, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$42.ordinal());
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$43 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576306390, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged4 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$43.ordinal());
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$44 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576310166, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged5 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$44.ordinal());
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$45 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576314031, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged6 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$45.ordinal());
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.fontFamily(context, DocumentPageEditor$lambda$40(mutableState)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue7 = mutableStateMutableStateOf$default;
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$46 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576317646, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged7 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$46.ordinal());
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged7 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.textColor(context, DocumentPageEditor$lambda$40(mutableState)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue8 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState8 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$47 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576321069, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged8 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$47.ordinal());
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChanged8 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue9 = mutableStateMutableStateOf$default3;
            }
            final MutableState mutableState9 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$48 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576324527, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged9 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$48.ordinal());
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChanged9 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue10 = mutableStateMutableStateOf$default4;
            }
            final MutableState mutableState10 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            DocumentType documentTypeDocumentPageEditor$lambda$49 = DocumentPageEditor$lambda$40(mutableState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576328146, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged10 = composerStartRestartGroup.changed(documentTypeDocumentPageEditor$lambda$49.ordinal());
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (zChanged10 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentPageEditor$lambda$40(mutableState))), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            final MutableState mutableState11 = (MutableState) objRememberedValue11;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ActivityResultContracts.OpenDocument openDocument = new ActivityResultContracts.OpenDocument();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 576336491, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$75$lambda$74(context, mutableState, (Uri) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(openDocument, (Function1) objRememberedValue12, composerStartRestartGroup, 0);
            composer2 = composerStartRestartGroup;
            context2 = context;
            AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-1526691444, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$80(mutableState2, context, mutableState8, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState9, mutableState10, mutableState11, function1, mutableState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-1108802742, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$81(function1, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7693getLambda$690914040$app(), ComposableLambdaKt.rememberComposableLambda(1665513959, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$138(context, mutableState2, managedActivityResultLauncherRememberLauncherForActivityResult, mutableState3, mutableState4, mutableState5, mutableState6, mutableState8, mutableState, mutableState7, mutableState9, mutableState10, mutableState11, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, ((i2 >> 6) & 14) | 1772592, 0, 16276);
            function1 = function0;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$139(list, context2, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final DocumentType DocumentPageEditor$lambda$40(MutableState<DocumentType> mutableState) {
        return mutableState.getValue();
    }

    private static final String DocumentPageEditor$lambda$43(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final float DocumentPageEditor$lambda$46(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void DocumentPageEditor$lambda$47(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float DocumentPageEditor$lambda$49(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void DocumentPageEditor$lambda$50(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float DocumentPageEditor$lambda$52(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void DocumentPageEditor$lambda$53(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float DocumentPageEditor$lambda$55(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void DocumentPageEditor$lambda$56(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final String DocumentPageEditor$lambda$58(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String DocumentPageEditor$lambda$61(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean DocumentPageEditor$lambda$64(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DocumentPageEditor$lambda$65(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DocumentPageEditor$lambda$67(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DocumentPageEditor$lambda$68(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DocumentPageEditor$lambda$70(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DocumentPageEditor$lambda$71(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit DocumentPageEditor$lambda$75$lambda$74(Context context, MutableState mutableState, Uri uri) {
        if (uri != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                context.getContentResolver().takePersistableUriPermission(uri, 1);
                Result.m7781constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            AppPreferences.INSTANCE.saveBackgroundImage(context, DocumentPageEditor$lambda$40(mutableState), uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138(final Context context, final MutableState mutableState, final ManagedActivityResultLauncher managedActivityResultLauncher, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, MutableState mutableState8, final MutableState mutableState9, final MutableState mutableState10, final MutableState mutableState11, Composer composer, int i) {
        Composer composer2;
        Composer composer3;
        final MutableState mutableState12;
        Composer composer4;
        Composer composer5;
        Composer composer6;
        int i2;
        ComposerKt.sourceInformation(composer, "C127@11437L3260:PrintPreviewScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1665513959, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous> (PrintPreviewScreen.kt:127)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
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
            ComposerKt.sourceInformationMarkerStart(composer, -1284720537, "C128@11508L81,129@11602L276,130@11891L19,132@11993L517,133@12548L14,133@12523L140,134@12701L47,134@12786L124,134@12676L234,135@12923L50,136@13026L16,136@12986L78,137@13077L45,138@13173L14,138@13135L76,139@13224L47,140@13324L16,140@13284L83,141@13380L47,142@13480L16,142@13440L83,143@13565L18,143@13536L145,144@13694L640,149@14347L340:PrintPreviewScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("اختر النوع ثم غيّر المقاس والخلفية. الشعارات تدار من الإعدادات لكل مستند.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            Composer composer7 = composer;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer7, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composer7, 6);
            ComposerKt.sourceInformationMarkerStart(composer7, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer7, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer7.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer7, companion2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer7, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer7.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer7.startReusableNode();
            if (composer7.getInserting()) {
                composer7.createNode(constructor2);
            } else {
                composer7.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composer7);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer7, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer7, -2133136514, "C:PrintPreviewScreen.kt#ska5t9");
            composer7.startReplaceGroup(346831823);
            ComposerKt.sourceInformation(composer7, "");
            DocumentType[] documentTypeArrValues = DocumentType.values();
            int length = documentTypeArrValues.length;
            String str = "C:PrintPreviewScreen.kt#ska5t9";
            int i3 = 0;
            while (i3 < length) {
                int i4 = i3;
                final DocumentType documentType = documentTypeArrValues[i4];
                DocumentType[] documentTypeArr = documentTypeArrValues;
                if (documentType == DocumentPageEditor$lambda$40(mutableState7)) {
                    composer7.startReplaceGroup(-186498212);
                    ComposerKt.sourceInformation(composer7, "129@11743L23,129@11768L25,129@11726L67");
                    ComposerKt.sourceInformationMarkerStart(composer7, -186497712, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                    boolean zChanged = composer7.changed(documentType.ordinal());
                    Object objRememberedValue = composer7.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PrintPreviewScreenKt.m844x62a82370(documentType, mutableState7);
                            }
                        };
                        composer7.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    i2 = length;
                    ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-389406812, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return PrintPreviewScreenKt.m845xb80efa5e(documentType, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer7, 54), composer, 805306368, 510);
                    composer.endReplaceGroup();
                } else {
                    i2 = length;
                    composer7.startReplaceGroup(-186495868);
                    ComposerKt.sourceInformation(composer7, "129@11824L23,129@11849L25,129@11799L75");
                    ComposerKt.sourceInformationMarkerStart(composer7, -186495120, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                    boolean zChanged2 = composer7.changed(documentType.ordinal());
                    Object objRememberedValue2 = composer7.rememberedValue();
                    if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda30
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PrintPreviewScreenKt.m846x21563bb6(documentType, mutableState7);
                            }
                        };
                        composer7.updateRememberedValue(objRememberedValue2);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ButtonKt.OutlinedButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1981759249, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda31
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return PrintPreviewScreenKt.m847xb80efa61(documentType, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer7, 54), composer, 805306368, 510);
                    composer.endReplaceGroup();
                }
                composer7 = composer;
                str = str;
                i3 = i4 + 1;
                documentTypeArrValues = documentTypeArr;
                length = i2;
            }
            String str2 = str;
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextKt.m3342Text4IGK_g("مقاس الصفحة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            String strPageSize = AppPreferences.INSTANCE.pageSize(context, DocumentPageEditor$lambda$40(mutableState7));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, companion3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 42770532, str2);
            if (Intrinsics.areEqual(strPageSize, "A4")) {
                composer.startReplaceGroup(555569234);
                ComposerKt.sourceInformation(composer, "132@12086L59,132@12069L92");
                ComposerKt.sourceInformationMarkerStart(composer, 555569745, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChangedInstance = composer.changedInstance(context);
                Object objRememberedValue3 = composer.rememberedValue();
                if (zChangedInstance || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda32
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m848x276c81af(context, mutableState7);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer2 = composer;
                ButtonKt.Button((Function0) objRememberedValue3, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$2120216075$app(), composer2, 805306368, 510);
            } else {
                composer.startReplaceGroup(555572378);
                ComposerKt.sourceInformation(composer, "132@12192L59,132@12167L100");
                ComposerKt.sourceInformationMarkerStart(composer, 555573137, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChangedInstance2 = composer.changedInstance(context);
                Object objRememberedValue4 = composer.rememberedValue();
                if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m849x5135e733(context, mutableState7);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer2 = composer;
                ButtonKt.OutlinedButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1444947616$app(), composer2, 805306368, 510);
            }
            Composer composer8 = composer2;
            composer8.endReplaceGroup();
            if (Intrinsics.areEqual(strPageSize, "HALF_A4")) {
                composer8.startReplaceGroup(555576379);
                ComposerKt.sourceInformation(composer8, "132@12309L64,132@12292L101");
                ComposerKt.sourceInformationMarkerStart(composer8, 555576886, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChangedInstance3 = composer8.changedInstance(context);
                Object objRememberedValue5 = composer8.rememberedValue();
                if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda35
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m850x7aff4cb7(context, mutableState7);
                        }
                    };
                    composer8.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer8);
                composer3 = composer8;
                ButtonKt.Button((Function0) objRememberedValue5, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7686getLambda$138045068$app(), composer3, 805306368, 510);
            } else {
                composer8.startReplaceGroup(555579811);
                ComposerKt.sourceInformation(composer8, "132@12424L64,132@12399L109");
                ComposerKt.sourceInformationMarkerStart(composer8, 555580566, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChangedInstance4 = composer8.changedInstance(context);
                Object objRememberedValue6 = composer8.rememberedValue();
                if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda36
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m851xa4c8b23b(context, mutableState7);
                        }
                    };
                    composer8.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composer8);
                composer3 = composer8;
                ButtonKt.OutlinedButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7691getLambda$530972471$app(), composer3, 805306368, 510);
            }
            Composer composer9 = composer3;
            composer9.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer9);
            ComposerKt.sourceInformationMarkerEnd(composer9);
            composer9.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer9);
            ComposerKt.sourceInformationMarkerEnd(composer9);
            ComposerKt.sourceInformationMarkerEnd(composer9);
            String strDocumentPageEditor$lambda$43 = DocumentPageEditor$lambda$43(mutableState);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer9, -2119622465, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged3 = composer9.changed(mutableState);
            Object objRememberedValue7 = composer9.rememberedValue();
            if (zChanged3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$100$lambda$99(mutableState, (String) obj);
                    }
                };
                composer9.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer9);
            OutlinedTextFieldKt.OutlinedTextField(strDocumentPageEditor$lambda$43, (Function1<? super String, Unit>) objRememberedValue7, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7696getLambda$880914089$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer9, 1573248, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerStart(composer9, -2119617536, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChangedInstance5 = composer9.changedInstance(managedActivityResultLauncher);
            Object objRememberedValue8 = composer9.rememberedValue();
            if (zChangedInstance5 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$102$lambda$101(managedActivityResultLauncher);
                    }
                };
                composer9.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer9);
            ButtonKt.OutlinedButton((Function0) objRememberedValue8, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1164260095, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$103(context, mutableState7, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer9, 54), composer9, 805306416, 508);
            TextKt.m3342Text4IGK_g("شفافية الصورة: " + ((int) (DocumentPageEditor$lambda$46(mutableState2) * 100.0f)) + "%", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            float fDocumentPageEditor$lambda$46 = DocumentPageEditor$lambda$46(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, -2119607167, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged4 = composer.changed(mutableState2);
            Object objRememberedValue9 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$105$lambda$104(mutableState2, ((Float) obj).floatValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SliderKt.Slider(fDocumentPageEditor$lambda$46, (Function1) objRememberedValue9, null, false, RangesKt.rangeTo(0.0f, 1.0f), 0, null, null, null, composer, 0, 492);
            TextKt.m3342Text4IGK_g("حجم الصورة: " + ((int) (DocumentPageEditor$lambda$49(mutableState3) * 100.0f)) + "%", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            float fDocumentPageEditor$lambda$49 = DocumentPageEditor$lambda$49(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, -2119602465, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged5 = composer.changed(mutableState3);
            Object objRememberedValue10 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$107$lambda$106(mutableState3, ((Float) obj).floatValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SliderKt.Slider(fDocumentPageEditor$lambda$49, (Function1) objRememberedValue10, null, false, RangesKt.rangeTo(0.2f, 3.0f), 0, null, null, null, composer, 0, 492);
            TextKt.m3342Text4IGK_g("تحريك الصورة أفقيًا: " + ((int) DocumentPageEditor$lambda$52(mutableState4)), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            float fDocumentPageEditor$lambda$52 = DocumentPageEditor$lambda$52(mutableState4);
            ComposerKt.sourceInformationMarkerStart(composer, -2119597631, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged6 = composer.changed(mutableState4);
            Object objRememberedValue11 = composer.rememberedValue();
            if (zChanged6 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$109$lambda$108(mutableState4, ((Float) obj).floatValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SliderKt.Slider(fDocumentPageEditor$lambda$52, (Function1) objRememberedValue11, null, false, RangesKt.rangeTo(-300.0f, 300.0f), 0, null, null, null, composer, 0, 492);
            TextKt.m3342Text4IGK_g("تحريك الصورة رأسيًا: " + ((int) DocumentPageEditor$lambda$55(mutableState5)), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            float fDocumentPageEditor$lambda$55 = DocumentPageEditor$lambda$55(mutableState5);
            ComposerKt.sourceInformationMarkerStart(composer, -2119592639, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged7 = composer.changed(mutableState5);
            Object objRememberedValue12 = composer.rememberedValue();
            if (zChanged7 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$111$lambda$110(mutableState5, ((Float) obj).floatValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            SliderKt.Slider(fDocumentPageEditor$lambda$55, (Function1) objRememberedValue12, null, false, RangesKt.rangeTo(-300.0f, 300.0f), 0, null, null, null, composer, 0, 492);
            String strDocumentPageEditor$lambda$61 = DocumentPageEditor$lambda$61(mutableState6);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer, -2119589917, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged8 = composer.changed(mutableState6);
            Object objRememberedValue13 = composer.rememberedValue();
            if (zChanged8 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$113$lambda$112(mutableState6, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue13);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strDocumentPageEditor$lambda$61, (Function1<? super String, Unit>) objRememberedValue13, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1885426304$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573248, 12582912, 0, 8257464);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_7 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion4 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_7, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, companion4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
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
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1261068966, str2);
            if (Intrinsics.areEqual(DocumentPageEditor$lambda$58(mutableState8), "SANS")) {
                composer.startReplaceGroup(1149058644);
                ComposerKt.sourceInformation(composer, "145@13811L23,145@13794L63");
                ComposerKt.sourceInformationMarkerStart(composer, 1149059148, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                mutableState12 = mutableState8;
                boolean zChanged9 = composer.changed(mutableState12);
                Object objRememberedValue14 = composer.rememberedValue();
                if (zChanged9 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue14 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m835x30ece625(mutableState12);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue14);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer4 = composer;
                ButtonKt.Button((Function0) objRememberedValue14, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7692getLambda$55700246$app(), composer4, 805306368, 510);
            } else {
                mutableState12 = mutableState8;
                composer.startReplaceGroup(1149060860);
                ComposerKt.sourceInformation(composer, "145@13888L23,145@13863L71");
                ComposerKt.sourceInformationMarkerStart(composer, 1149061612, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChanged10 = composer.changed(mutableState12);
                Object objRememberedValue15 = composer.rememberedValue();
                if (zChanged10 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue15 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m836x405030e5(mutableState12);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue15);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer4 = composer;
                ButtonKt.OutlinedButton((Function0) objRememberedValue15, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7694getLambda$730968705$app(), composer4, 805306368, 510);
            }
            Composer composer10 = composer4;
            composer10.endReplaceGroup();
            if (Intrinsics.areEqual(DocumentPageEditor$lambda$58(mutableState12), "SERIF")) {
                composer10.startReplaceGroup(1149064534);
                ComposerKt.sourceInformation(composer10, "146@13995L24,146@13978L65");
                ComposerKt.sourceInformationMarkerStart(composer10, 1149065037, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChanged11 = composer10.changed(mutableState12);
                Object objRememberedValue16 = composer10.rememberedValue();
                if (zChanged11 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue16 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m837x4fb37ba5(mutableState12);
                        }
                    };
                    composer10.updateRememberedValue(objRememberedValue16);
                }
                ComposerKt.sourceInformationMarkerEnd(composer10);
                composer5 = composer10;
                ButtonKt.Button((Function0) objRememberedValue16, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1981005907$app(), composer5, 805306368, 510);
            } else {
                composer10.startReplaceGroup(1149066814);
                ComposerKt.sourceInformation(composer10, "146@14074L24,146@14049L73");
                ComposerKt.sourceInformationMarkerStart(composer10, 1149067565, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChanged12 = composer10.changed(mutableState12);
                Object objRememberedValue17 = composer10.rememberedValue();
                if (zChanged12 || objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue17 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda19
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m838xa95745(mutableState12);
                        }
                    };
                    composer10.updateRememberedValue(objRememberedValue17);
                }
                ComposerKt.sourceInformationMarkerEnd(composer10);
                composer5 = composer10;
                ButtonKt.OutlinedButton((Function0) objRememberedValue17, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1588078504$app(), composer5, 805306368, 510);
            }
            Composer composer11 = composer5;
            composer11.endReplaceGroup();
            if (Intrinsics.areEqual(DocumentPageEditor$lambda$58(mutableState12), "MONOSPACE")) {
                composer11.startReplaceGroup(1149070681);
                ComposerKt.sourceInformation(composer11, "147@14187L28,147@14170L68");
                ComposerKt.sourceInformationMarkerStart(composer11, 1149071185, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChanged13 = composer11.changed(mutableState12);
                Object objRememberedValue18 = composer11.rememberedValue();
                if (zChanged13 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue18 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m839x100ca205(mutableState12);
                        }
                    };
                    composer11.updateRememberedValue(objRememberedValue18);
                }
                ComposerKt.sourceInformationMarkerEnd(composer11);
                composer6 = composer11;
                ButtonKt.Button((Function0) objRememberedValue18, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7690getLambda$434434766$app(), composer6, 805306368, 510);
            } else {
                composer11.startReplaceGroup(1149073057);
                ComposerKt.sourceInformation(composer11, "147@14269L28,147@14244L76");
                ComposerKt.sourceInformationMarkerStart(composer11, 1149073809, "CC(remember):PrintPreviewScreen.kt#9igjgp");
                boolean zChanged14 = composer11.changed(mutableState12);
                Object objRememberedValue19 = composer11.rememberedValue();
                if (zChanged14 || objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue19 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PrintPreviewScreenKt.m840x1f6fecc5(mutableState12);
                        }
                    };
                    composer11.updateRememberedValue(objRememberedValue19);
                }
                ComposerKt.sourceInformationMarkerEnd(composer11);
                composer6 = composer11;
                ButtonKt.OutlinedButton((Function0) objRememberedValue19, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7695getLambda$827362169$app(), composer6, 805306368, 510);
            }
            Composer composer12 = composer6;
            composer12.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ComposerKt.sourceInformationMarkerEnd(composer12);
            composer12.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ComposerKt.sourceInformationMarkerEnd(composer12);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_8 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer12, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion5 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_8, Alignment.INSTANCE.getTop(), composer12, 6);
            ComposerKt.sourceInformationMarkerStart(composer12, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer12, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer12.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer12, companion5);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer12, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer12.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer12.startReusableNode();
            if (composer12.getInserting()) {
                composer12.createNode(constructor5);
            } else {
                composer12.useNode();
            }
            Composer composerM4301constructorimpl5 = Updater.m4301constructorimpl(composer12);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer12, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer12, -1815643017, "C149@14430L16,149@14448L40,149@14405L83,149@14515L20,149@14537L42,149@14490L89,149@14606L26,149@14634L51,149@14581L104:PrintPreviewScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer12, 1742546724, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged15 = composer12.changed(mutableState9);
            Object objRememberedValue20 = composer12.rememberedValue();
            if (zChanged15 || objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue20 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.m841x32866bc4(mutableState9);
                    }
                };
                composer12.updateRememberedValue(objRememberedValue20);
            }
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ButtonKt.OutlinedButton((Function0) objRememberedValue20, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(722369154, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$129(mutableState9, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer12, 54), composer12, 805306368, 510);
            ComposerKt.sourceInformationMarkerStart(composer12, 1742549448, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged16 = composer12.changed(mutableState10);
            Object objRememberedValue21 = composer12.rememberedValue();
            if (zChanged16 || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue21 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.m842xeb2decc4(mutableState10);
                    }
                };
                composer12.updateRememberedValue(objRememberedValue21);
            }
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ButtonKt.OutlinedButton((Function0) objRememberedValue21, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-388015189, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$132(mutableState10, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer12, 54), composer12, 805306368, 510);
            ComposerKt.sourceInformationMarkerStart(composer12, 1742552366, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged17 = composer12.changed(mutableState11);
            Object objRememberedValue22 = composer12.rememberedValue();
            if (zChanged17 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue22 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.m843x242dce4(mutableState11);
                    }
                };
                composer12.updateRememberedValue(objRememberedValue22);
            }
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ButtonKt.OutlinedButton((Function0) objRememberedValue22, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1491511434, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$135(mutableState11, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer12, 54), composer12, 805306368, 510);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
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

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$89$lambda$88$lambda$83$lambda$82 */
    static final Unit m844x62a82370(DocumentType documentType, MutableState mutableState) {
        mutableState.setValue(documentType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$89$lambda$88$lambda$84 */
    static final Unit m845xb80efa5e(DocumentType documentType, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C129@11770L21:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-389406812, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:129)");
            }
            TextKt.m3342Text4IGK_g(typeLabel(documentType), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$89$lambda$88$lambda$86$lambda$85 */
    static final Unit m846x21563bb6(DocumentType documentType, MutableState mutableState) {
        mutableState.setValue(documentType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$89$lambda$88$lambda$87 */
    static final Unit m847xb80efa61(DocumentType documentType, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C129@11851L21:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1981759249, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:129)");
            }
            TextKt.m3342Text4IGK_g(typeLabel(documentType), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$98$lambda$91$lambda$90 */
    static final Unit m848x276c81af(Context context, MutableState mutableState) {
        AppPreferences.INSTANCE.setPageSize(context, DocumentPageEditor$lambda$40(mutableState), "A4");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$98$lambda$93$lambda$92 */
    static final Unit m849x5135e733(Context context, MutableState mutableState) {
        AppPreferences.INSTANCE.setPageSize(context, DocumentPageEditor$lambda$40(mutableState), "A4");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$98$lambda$95$lambda$94 */
    static final Unit m850x7aff4cb7(Context context, MutableState mutableState) {
        AppPreferences.INSTANCE.setPageSize(context, DocumentPageEditor$lambda$40(mutableState), "HALF_A4");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$98$lambda$97$lambda$96 */
    static final Unit m851xa4c8b23b(Context context, MutableState mutableState) {
        AppPreferences.INSTANCE.setPageSize(context, DocumentPageEditor$lambda$40(mutableState), "HALF_A4");
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$100$lambda$99(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$102$lambda$101(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch(new String[]{"image/*"});
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$103(Context context, MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C134@12788L120:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1164260095, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:134)");
            }
            TextKt.m3342Text4IGK_g(AppPreferences.INSTANCE.backgroundImageUri(context, DocumentPageEditor$lambda$40(mutableState)) == null ? "إضافة صورة خلفية" : "تغيير صورة الخلفية", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$105$lambda$104(MutableState mutableState, float f) {
        DocumentPageEditor$lambda$47(mutableState, f);
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$107$lambda$106(MutableState mutableState, float f) {
        DocumentPageEditor$lambda$50(mutableState, f);
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$109$lambda$108(MutableState mutableState, float f) {
        DocumentPageEditor$lambda$53(mutableState, f);
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$111$lambda$110(MutableState mutableState, float f) {
        DocumentPageEditor$lambda$56(mutableState, f);
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$113$lambda$112(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$115$lambda$114 */
    static final Unit m835x30ece625(MutableState mutableState) {
        mutableState.setValue("SANS");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$117$lambda$116 */
    static final Unit m836x405030e5(MutableState mutableState) {
        mutableState.setValue("SANS");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$119$lambda$118 */
    static final Unit m837x4fb37ba5(MutableState mutableState) {
        mutableState.setValue("SERIF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$121$lambda$120 */
    static final Unit m838xa95745(MutableState mutableState) {
        mutableState.setValue("SERIF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$123$lambda$122 */
    static final Unit m839x100ca205(MutableState mutableState) {
        mutableState.setValue("MONOSPACE");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$126$lambda$125$lambda$124 */
    static final Unit m840x1f6fecc5(MutableState mutableState) {
        mutableState.setValue("MONOSPACE");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$128$lambda$127 */
    static final Unit m841x32866bc4(MutableState mutableState) {
        DocumentPageEditor$lambda$65(mutableState, !DocumentPageEditor$lambda$64(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$129(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C149@14450L36:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(722369154, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:149)");
            }
            TextKt.m3342Text4IGK_g(DocumentPageEditor$lambda$64(mutableState) ? "عريض ✓" : "عريض", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$131$lambda$130 */
    static final Unit m842xeb2decc4(MutableState mutableState) {
        DocumentPageEditor$lambda$68(mutableState, !DocumentPageEditor$lambda$67(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$132(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C149@14539L38:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-388015189, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:149)");
            }
            TextKt.m3342Text4IGK_g(DocumentPageEditor$lambda$67(mutableState) ? "مائل ✓" : "مائل", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$134$lambda$133 */
    static final Unit m843x242dce4(MutableState mutableState) {
        DocumentPageEditor$lambda$71(mutableState, !DocumentPageEditor$lambda$70(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$138$lambda$137$lambda$136$lambda$135(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C149@14636L47:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1491511434, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PrintPreviewScreen.kt:149)");
            }
            TextKt.m3342Text4IGK_g(DocumentPageEditor$lambda$70(mutableState) ? "تحته خط ✓" : "تحته خط", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$80(final MutableState mutableState, final Context context, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, final MutableState mutableState9, final MutableState mutableState10, final Function0 function0, final MutableState mutableState11, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C151@14740L716,151@14723L760:PrintPreviewScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1526691444, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous> (PrintPreviewScreen.kt:151)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1728338872, "CC(remember):PrintPreviewScreen.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState) | composer.changedInstance(context) | composer.changed(mutableState2) | composer.changed(mutableState3) | composer.changed(mutableState4) | composer.changed(mutableState5) | composer.changed(mutableState6) | composer.changed(mutableState7) | composer.changed(mutableState8) | composer.changed(mutableState9) | composer.changed(mutableState10) | composer.changed(function0);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PrintPreviewScreenKt.DocumentPageEditor$lambda$80$lambda$79$lambda$78(context, function0, mutableState, mutableState11, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9, mutableState10);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$108883868$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$80$lambda$79$lambda$78(Context context, Function0 function0, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, MutableState mutableState10, MutableState mutableState11) {
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(DocumentPageEditor$lambda$43(mutableState))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7788isSuccessimpl(objM7781constructorimpl)) {
            AppPreferences.INSTANCE.setBackgroundColor(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$43(mutableState));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(DocumentPageEditor$lambda$61(mutableState3))));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m7788isSuccessimpl(objM7781constructorimpl2)) {
            AppPreferences.INSTANCE.setTextColor(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$61(mutableState3));
        }
        AppPreferences.INSTANCE.setBackgroundOpacity(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$46(mutableState4));
        AppPreferences.INSTANCE.setBackgroundScale(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$49(mutableState5));
        AppPreferences.INSTANCE.setBackgroundOffset(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$52(mutableState6), DocumentPageEditor$lambda$55(mutableState7));
        AppPreferences.INSTANCE.setFontFamily(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$58(mutableState8));
        AppPreferences.INSTANCE.setTextBold(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$64(mutableState9));
        AppPreferences.INSTANCE.setTextItalic(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$67(mutableState10));
        AppPreferences.INSTANCE.setTextUnderline(context, DocumentPageEditor$lambda$40(mutableState2), DocumentPageEditor$lambda$70(mutableState11));
        function0.invoke();
        return Unit.INSTANCE;
    }

    static final Unit DocumentPageEditor$lambda$81(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C151@15505L47:PrintPreviewScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1108802742, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentPageEditor.<anonymous> (PrintPreviewScreen.kt:151)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.getLambda$1911998765$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final String typeLabel(DocumentType documentType) {
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        if (i == 1) {
            return "أمر صرف";
        }
        if (i != 2) {
            return i != 3 ? documentType.name() : "استلام";
        }
        return "تقديم";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void OfficialDocumentCard(final Document document, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(935582659);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OfficialDocumentCard)158@15870L576,158@15804L642:PrintPreviewScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(document) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(935582659, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.OfficialDocumentCard (PrintPreviewScreen.kt:157)");
            }
            CardKt.Card(SizeKt.fillMaxWidth$default(PaddingKt.m1660paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, C1786Dp.m7249constructorimpl(8.0f), 1, null), 0.0f, 1, null), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(995560245, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PrintPreviewScreenKt.OfficialDocumentCard$lambda$141(document, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 30);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PrintPreviewScreenKt.OfficialDocumentCard$lambda$142(document, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit OfficialDocumentCard$lambda$141(Document document, ColumnScope Card, Composer composer, int i) {
        String strName;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C159@15880L560:PrintPreviewScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(995560245, i, -1, "com.mohammedalhzmi.masrofmanager.ui.OfficialDocumentCard.<anonymous> (PrintPreviewScreen.kt:159)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(16.0f));
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
            ComposerKt.sourceInformationMarkerStart(composer, -1363788053, "C160@16121L10,160@15937L207,161@16157L42,162@16212L47,164@16374L56:PrintPreviewScreen.kt#ska5t9");
            int i2 = WhenMappings.$EnumSwitchMapping$0[document.getType().ordinal()];
            if (i2 == 1) {
                strName = "أمر صرف";
            } else if (i2 != 2) {
                strName = i2 != 3 ? document.getType().name() : "ورقة استلام";
            } else {
                strName = "ورقة تقديم طلب";
            }
            TextKt.m3342Text4IGK_g(strName, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleMedium(), composer, 0, 0, 65534);
            TextKt.m3342Text4IGK_g("رقم المستند: " + document.getDocumentNumber(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            String beneficiaryName = document.getBeneficiaryName();
            if (beneficiaryName == null) {
                beneficiaryName = "";
            }
            TextKt.m3342Text4IGK_g("الاسم: " + beneficiaryName, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            Composer composer2 = composer;
            if (document.getAmount() != null) {
                composer2.startReplaceGroup(-1013813428);
                ComposerKt.sourceInformation(composer2, "163@16296L65");
                Double amount = document.getAmount();
                String amountWords = document.getAmountWords();
                if (amountWords == null) {
                    amountWords = "";
                }
                TextKt.m3342Text4IGK_g("المبلغ: " + amount + " ريال — " + amountWords, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
                composer2 = composer;
            } else {
                composer2.startReplaceGroup(-1379613833);
            }
            composer2.endReplaceGroup();
            TextKt.m3342Text4IGK_g("التاريخ: " + document.getDateHijri() + " / " + document.getDateGregorian(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
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

    private static final List<Document> PrintPreviewScreen$lambda$0(State<? extends List<Document>> state) {
        return state.getValue();
    }

    static final Unit PrintPreviewScreen$lambda$37$lambda$13$lambda$12(final List list, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final C4086x87871584 c4086x87871584 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$PrintPreviewScreen$lambda$37$lambda$13$lambda$12$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(Document document) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((Document) obj);
            }
        };
        LazyColumn.items(list.size(), null, new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$PrintPreviewScreen$lambda$37$lambda$13$lambda$12$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return c4086x87871584.invoke(list.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.PrintPreviewScreenKt$PrintPreviewScreen$lambda$37$lambda$13$lambda$12$$inlined$items$default$4
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
                    i3 = (composer.changed(lazyItemScope) ? 4 : 2) | i2;
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
                Document document = (Document) list.get(i);
                composer.startReplaceGroup(-1715868640);
                ComposerKt.sourceInformation(composer, "C*40@2449L25:PrintPreviewScreen.kt#ska5t9");
                PrintPreviewScreenKt.OfficialDocumentCard(document, composer, 0);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$PrintPreviewScreenKt.INSTANCE.m7688getLambda$2083216611$app(), 3, null);
        return Unit.INSTANCE;
    }
}
