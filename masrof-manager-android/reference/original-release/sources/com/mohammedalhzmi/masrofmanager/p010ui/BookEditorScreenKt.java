package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import androidx.compose.foundation.ScrollKt;
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
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.graphics.Shape;
import androidx.compose.p000ui.input.pointer.SuspendingPointerInputFilterKt;
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
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.text.util.LocalePreferences;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: BookEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001a\f\u0010\r\u001a\u00020\u0005*\u00020\tH\u0002¨\u0006\u000e²\u0006\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u008a\u0084\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010 \u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\u0005X\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020\u0005X\u008a\u008e\u0002"}, m914d2 = {"BookEditorScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "bookTag", "", "onPreview", "Lkotlin/Function1;", "onOpenCanvas", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "onBack", "Lkotlin/Function0;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", LinkHeader.Parameters.Title, "app", "allDocuments", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "pageIndex", "", "showPreview", "", "showWordEditor", "hijri", LocalePreferences.CalendarType.GREGORIAN, "beneficiary", "beneficiaryId", "purpose", "details", "amount", "amountWords", "category", "costCenter", "funding", "notes", "tags", "attachments"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class BookEditorScreenKt {

    /* JADX INFO: compiled from: BookEditorScreen.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.ORDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BookEditorScreen$lambda$125(MasrofViewModel masrofViewModel, String str, Function1 function1, Function1 function2, Function0 function0, int i, Composer composer, int i2) {
        BookEditorScreen(masrofViewModel, str, function1, function2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r16v59 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r3v5, types: [androidx.compose.material3.MaterialTheme] */
    /* JADX WARN: Type inference failed for: r5v31, types: [androidx.compose.material3.MaterialTheme] */
    /* JADX WARN: Type inference failed for: r6v17, types: [androidx.compose.material3.CardDefaults] */
    /* JADX WARN: Type inference failed for: r98v1 */
    /* JADX WARN: Type inference failed for: r98v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r98v8 */
    /* JADX WARN: Type inference failed for: r9v24, types: [androidx.compose.material3.MaterialTheme] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void BookEditorScreen(MasrofViewModel masrofViewModel, final String bookTag, Function1<? super String, Unit> onPreview, final Function1<? super DocumentType, Unit> onOpenCanvas, final Function0<Unit> onBack, Composer composer, final int i) {
        ?? r98;
        ?? r16;
        boolean z;
        int iBookEditorScreen$lambda$5;
        final MutableState mutableState;
        String str;
        MutableIntState mutableIntState;
        BookEditorScreenKt$BookEditorScreen$1$2$1 bookEditorScreenKt$BookEditorScreen$1$2$1;
        int i2;
        SnapshotMutationPolicy snapshotMutationPolicy;
        Object objMutableStateOf$default;
        final Document document;
        Composer composer2;
        final MutableState mutableState2;
        final MutableState mutableState3;
        final MutableIntState mutableIntState2;
        final Function1<? super String, Unit> function1;
        final MasrofViewModel viewModel = masrofViewModel;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(bookTag, "bookTag");
        Intrinsics.checkNotNullParameter(onPreview, "onPreview");
        Intrinsics.checkNotNullParameter(onOpenCanvas, "onOpenCanvas");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1837234646);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(BookEditorScreen)P(4!1,3,2)24@930L16,25@963L157,28@1142L33,29@1199L34,31@1323L33,32@1388L7,34@1445L8183:BookEditorScreen.kt#ska5t9");
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(bookTag) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onPreview) ? 256 : 128;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onBack) ? 16384 : 8192;
        }
        if ((i3 & 8339) == 8338 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            function1 = onPreview;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1837234646, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen (BookEditorScreen.kt:23)");
            }
            char c = 0;
            int i4 = 1;
            State stateCollectAsState = SnapshotStateKt.collectAsState(viewModel.getAllDocuments(), null, composerStartRestartGroup, 0, 1);
            List<Document> listBookEditorScreen$lambda$0 = BookEditorScreen$lambda$0(stateCollectAsState);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -350927385, "CC(remember):BookEditorScreen.kt#9igjgp");
            boolean zChanged = ((i3 & 112) == 32) | composerStartRestartGroup.changed(listBookEditorScreen$lambda$0);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                List<Document> listBookEditorScreen$lambda$1 = BookEditorScreen$lambda$0(stateCollectAsState);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listBookEditorScreen$lambda$1) {
                    String tags = ((Document) obj).getTags();
                    char[] cArr = new char[i4];
                    cArr[c] = AbstractJsonLexerKt.COMMA;
                    List listSplit$default = StringsKt.split$default((CharSequence) tags, cArr, false, 0, 6, (Object) null);
                    char c2 = c;
                    int i5 = i4;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
                    Iterator it = listSplit$default.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(StringsKt.trim((CharSequence) it.next()).toString());
                    }
                    if (arrayList2.contains(bookTag)) {
                        arrayList.add(obj);
                    }
                    c = c2;
                    i4 = i5;
                }
                r98 = c;
                r16 = i4;
                objRememberedValue = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$BookEditorScreen$lambda$3$$inlined$sortedBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(((Document) t).getDocumentNumber(), ((Document) t2).getDocumentNumber());
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                r98 = 0;
                r16 = 1;
            }
            List list = (List) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -350921781, "CC(remember):BookEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(r98);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableIntState mutableIntState3 = (MutableIntState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -350919956, "CC(remember):BookEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf((boolean) r98), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MutableState mutableState4 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -350915989, "CC(remember):BookEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf((boolean) r16), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState5 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            final Document document2 = (Document) CollectionsKt.getOrNull(list, BookEditorScreen$lambda$5(mutableIntState3));
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, r16 == true ? 1 : 0, null), C1786Dp.m7249constructorimpl(12.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            ?? r9 = r98;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, r9 == true ? 1 : 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, r9 == true ? 1 : 0);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 804163338, "C35@1501L249,39@1862L10,39@1759L124:BookEditorScreen.kt#ska5t9");
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1282260243, "C36@1660L10,36@1594L88,37@1695L45:BookEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("تحرير دفتر المستندات بالمحرر المدمج", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
            final List list2 = list;
            ButtonKt.TextButton(onBack, null, false, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.m7641getLambda$813222335$app(), composerStartRestartGroup, ((i3 >> 12) & 14) | 805306368, 510);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (list2.isEmpty()) {
                z = true;
                iBookEditorScreen$lambda$5 = 0;
            } else {
                z = true;
                iBookEditorScreen$lambda$5 = BookEditorScreen$lambda$5(mutableIntState3) + 1;
            }
            TextKt.m3342Text4IGK_g(bookTag + " — " + iBookEditorScreen$lambda$5 + " من " + list2.size(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 0, 0, 65534);
            composerStartRestartGroup = composerStartRestartGroup;
            if (list2.isEmpty()) {
                composerStartRestartGroup.startReplaceGroup(804324413);
                ComposerKt.sourceInformation(composerStartRestartGroup, "41@1927L109");
                TextKt.m3342Text4IGK_g("لم تُحمّل صفحات الدفتر بعد. ارجع للقائمة ثم افتح الدفتر مرة أخرى.", PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(16.0f)), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 54, 0, 131068);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
                viewModel = masrofViewModel;
                mutableState = mutableState4;
                str = "CC(remember):BookEditorScreen.kt#9igjgp";
            } else {
                if (document2 != null) {
                    composerStartRestartGroup.startReplaceGroup(804713029);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "45@2203L344,51@2563L21,43@2087L7142,125@9242L370");
                    Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, z ? 1 : 0, null);
                    Integer numValueOf = Integer.valueOf(BookEditorScreen$lambda$5(mutableIntState3));
                    Integer numValueOf2 = Integer.valueOf(list2.size());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 303050104, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(list2);
                    Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        mutableIntState = mutableIntState3;
                        bookEditorScreenKt$BookEditorScreen$1$2$1 = new BookEditorScreenKt$BookEditorScreen$1$2$1(list2, mutableIntState, null);
                        composerStartRestartGroup.updateRememberedValue(bookEditorScreenKt$BookEditorScreen$1$2$1);
                    } else {
                        bookEditorScreenKt$BookEditorScreen$1$2$1 = objRememberedValue5;
                        mutableIntState = mutableIntState3;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SuspendingPointerInputFilterKt.pointerInput(modifierFillMaxWidth$default2, numValueOf, numValueOf2, (Function2) bookEditorScreenKt$BookEditorScreen$1$2$1), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, z ? 1 : 0), false, null, false, 14, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
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
                    Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -43926586, "C54@2778L11,54@2736L69,54@2807L396,54@2684L519,60@3233L58,61@3325L62,62@3423L74,63@3535L62,64@3629L66,65@3727L66,66@3824L77,67@3937L70,68@4040L66,69@4141L59,70@4232L62,71@4324L64,72@4417L53,73@4506L76,75@4645L10,75@4600L68,76@4685L416,80@5173L20,80@5118L159,81@5351L22,81@5294L158,82@5520L16,82@5469L153,83@5690L16,83@5639L148,84@5804L420,88@6293L17,88@6241L140,89@6398L416,93@6831L437,97@7334L14,97@7285L145,98@7464L698,109@8200L48,98@7447L801,111@8291L36,111@8365L153,111@8266L252,119@8810L22,119@8785L120,120@8947L194,120@8922L293:BookEditorScreen.kt#ska5t9");
                    final MutableIntState mutableIntState4 = mutableIntState;
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, z ? 1 : 0, null), null, CardDefaults.INSTANCE.m2477cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, composerStartRestartGroup, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-1926027380, z, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda26
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$16(document2, mutableIntState4, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 26);
                    long id = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278500556, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged2 = composerStartRestartGroup.changed(id);
                    Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                    if (zChanged2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        i2 = 2;
                        snapshotMutationPolicy = null;
                        objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                    } else {
                        objMutableStateOf$default = objRememberedValue6;
                        i2 = 2;
                        snapshotMutationPolicy = null;
                    }
                    final MutableState mutableState6 = (MutableState) objMutableStateOf$default;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id2 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278497608, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged3 = composerStartRestartGroup.changed(id2);
                    Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (zChanged3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), snapshotMutationPolicy, i2, snapshotMutationPolicy);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    }
                    final MutableState mutableState7 = (MutableState) objRememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id3 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278494460, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged4 = composerStartRestartGroup.changed(id3);
                    Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                    if (zChanged4 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        String beneficiaryName = document2.getBeneficiaryName();
                        if (beneficiaryName == null) {
                            beneficiaryName = "";
                        }
                        objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, snapshotMutationPolicy, i2, snapshotMutationPolicy);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                    }
                    final MutableState mutableState8 = (MutableState) objRememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id4 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278490888, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged5 = composerStartRestartGroup.changed(id4);
                    Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                    if (zChanged5 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), snapshotMutationPolicy, i2, snapshotMutationPolicy);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                    }
                    final MutableState mutableState9 = (MutableState) objRememberedValue9;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id5 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278487876, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged6 = composerStartRestartGroup.changed(id5);
                    Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                    if (zChanged6 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                        String purpose = document2.getPurpose();
                        if (purpose == null) {
                            purpose = "";
                        }
                        MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                        objRememberedValue10 = mutableStateMutableStateOf$default;
                    }
                    final MutableState mutableState10 = (MutableState) objRememberedValue10;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id6 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278484740, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged7 = composerStartRestartGroup.changed(id6);
                    Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                    if (zChanged7 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                        String details = document2.getDetails();
                        if (details == null) {
                            details = "";
                        }
                        MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                        objRememberedValue11 = mutableStateMutableStateOf$default2;
                    }
                    final MutableState mutableState11 = (MutableState) objRememberedValue11;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id7 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278481625, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged8 = composerStartRestartGroup.changed(id7);
                    Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                    if (zChanged8 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                        Double amount = document2.getAmount();
                        String string = amount != null ? amount.toString() : null;
                        if (string == null) {
                            string = "";
                        }
                        MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                        objRememberedValue12 = mutableStateMutableStateOf$default3;
                    }
                    final MutableState mutableState12 = (MutableState) objRememberedValue12;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id8 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278478016, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged9 = composerStartRestartGroup.changed(id8);
                    Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                    if (zChanged9 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                        String amountWords = document2.getAmountWords();
                        if (amountWords == null) {
                            amountWords = "";
                        }
                        MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                        objRememberedValue13 = mutableStateMutableStateOf$default4;
                    }
                    final MutableState mutableState13 = (MutableState) objRememberedValue13;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id9 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278474724, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged10 = composerStartRestartGroup.changed(id9);
                    Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
                    if (zChanged10 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                        MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getFinancialCategory(), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                        objRememberedValue14 = mutableStateMutableStateOf$default5;
                    }
                    final MutableState mutableState14 = (MutableState) objRememberedValue14;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id10 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278471499, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged11 = composerStartRestartGroup.changed(id10);
                    Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
                    if (zChanged11 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                        MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getCostCenter(), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                        objRememberedValue15 = mutableStateMutableStateOf$default6;
                    }
                    final MutableState mutableState15 = (MutableState) objRememberedValue15;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id11 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278468584, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged12 = composerStartRestartGroup.changed(id11);
                    Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
                    if (zChanged12 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                        MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getFundingSource(), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                        objRememberedValue16 = mutableStateMutableStateOf$default7;
                    }
                    final MutableState mutableState16 = (MutableState) objRememberedValue16;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id12 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278465638, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged13 = composerStartRestartGroup.changed(id12);
                    Object objRememberedValue17 = composerStartRestartGroup.rememberedValue();
                    if (zChanged13 || objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                        String notes = document2.getNotes();
                        MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes != null ? notes : "", null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                        objRememberedValue17 = mutableStateMutableStateOf$default8;
                    }
                    final MutableState mutableState17 = (MutableState) objRememberedValue17;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id13 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278462673, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged14 = composerStartRestartGroup.changed(id13);
                    Object objRememberedValue18 = composerStartRestartGroup.rememberedValue();
                    if (zChanged14 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                        MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getTags(), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                        objRememberedValue18 = mutableStateMutableStateOf$default9;
                    }
                    final MutableState mutableState18 = (MutableState) objRememberedValue18;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    long id14 = document2.getId();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278459802, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged15 = composerStartRestartGroup.changed(id14);
                    Object objRememberedValue19 = composerStartRestartGroup.rememberedValue();
                    if (zChanged15 || objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue19 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document2.getAttachmentsCount()), null, 2, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
                    }
                    final MutableState mutableState19 = (MutableState) objRememberedValue19;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    TextKt.m3342Text4IGK_g("بيانات المستند", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleMedium(), composerStartRestartGroup, 6, 0, 65534);
                    Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default3);
                    Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor4);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM4301constructorimpl4 = Updater.m4301constructorimpl(composerStartRestartGroup);
                    Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                        composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1851301314, "C77@4837L14,77@4788L132,78@4994L18,78@4941L142:BookEditorScreen.kt#ska5t9");
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$18 = BookEditorScreen$lambda$111$lambda$105$lambda$18(mutableState6);
                    Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1860833428, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged16 = composerStartRestartGroup.changed(mutableState6);
                    Object objRememberedValue20 = composerStartRestartGroup.rememberedValue();
                    if (zChanged16 || objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m740x5872eb6d(mutableState6, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$18, (Function1<? super String, Unit>) objRememberedValue20, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$240519704$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$21 = BookEditorScreen$lambda$111$lambda$105$lambda$21(mutableState7);
                    Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1860828400, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged17 = composerStartRestartGroup.changed(mutableState7);
                    Object objRememberedValue21 = composerStartRestartGroup.rememberedValue();
                    if (zChanged17 || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda16
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m741x823c5106(mutableState7, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$21, (Function1<? super String, Unit>) objRememberedValue21, modifierWeight$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$256859983$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$24 = BookEditorScreen$lambda$111$lambda$105$lambda$24(mutableState8);
                    Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278438514, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged18 = composerStartRestartGroup.changed(mutableState8);
                    Object objRememberedValue22 = composerStartRestartGroup.rememberedValue();
                    if (zChanged18 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda17
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$65$lambda$64(mutableState8, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$24, (Function1<? super String, Unit>) objRememberedValue22, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7640getLambda$376849356$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$27 = BookEditorScreen$lambda$111$lambda$105$lambda$27(mutableState9);
                    Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278432816, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged19 = composerStartRestartGroup.changed(mutableState9);
                    Object objRememberedValue23 = composerStartRestartGroup.rememberedValue();
                    if (zChanged19 || objRememberedValue23 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda18
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$67$lambda$66(mutableState9, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$27, (Function1<? super String, Unit>) objRememberedValue23, modifierFillMaxWidth$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7638getLambda$2059794005$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$30 = BookEditorScreen$lambda$111$lambda$105$lambda$30(mutableState10);
                    Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278427414, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged20 = composerStartRestartGroup.changed(mutableState10);
                    Object objRememberedValue24 = composerStartRestartGroup.rememberedValue();
                    if (zChanged20 || objRememberedValue24 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue24 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda19
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$69$lambda$68(mutableState10, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue24);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$30, (Function1<? super String, Unit>) objRememberedValue24, modifierFillMaxWidth$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1003880748$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 2, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$33 = BookEditorScreen$lambda$111$lambda$105$lambda$33(mutableState11);
                    Modifier modifierFillMaxWidth$default7 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278421974, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged21 = composerStartRestartGroup.changed(mutableState11);
                    Object objRememberedValue25 = composerStartRestartGroup.rememberedValue();
                    if (zChanged21 || objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue25 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda20
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$71$lambda$70(mutableState11, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue25);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$33, (Function1<? super String, Unit>) objRememberedValue25, modifierFillMaxWidth$default7, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7639getLambda$227411795$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                    Modifier modifierFillMaxWidth$default8 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default8);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor5);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM4301constructorimpl5 = Updater.m4301constructorimpl(composerStartRestartGroup);
                    Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                        composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                        composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1265276829, "C85@5957L15,85@5907L135,86@6118L20,86@6063L143:BookEditorScreen.kt#ska5t9");
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$36 = BookEditorScreen$lambda$111$lambda$105$lambda$36(mutableState12);
                    Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1760301252, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged22 = composerStartRestartGroup.changed(mutableState12);
                    Object objRememberedValue26 = composerStartRestartGroup.rememberedValue();
                    if (zChanged22 || objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue26 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda21
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m742xcb234468(mutableState12, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue26);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$36, (Function1<? super String, Unit>) objRememberedValue26, modifierWeight$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7637getLambda$1473614193$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$39 = BookEditorScreen$lambda$111$lambda$105$lambda$39(mutableState13);
                    Modifier modifierWeight$default4 = RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1760306409, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged23 = composerStartRestartGroup.changed(mutableState13);
                    Object objRememberedValue27 = composerStartRestartGroup.rememberedValue();
                    if (zChanged23 || objRememberedValue27 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue27 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda23
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m743xf4eca9ec(mutableState13, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue27);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$39, (Function1<? super String, Unit>) objRememberedValue27, modifierWeight$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1344492038$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$42 = BookEditorScreen$lambda$111$lambda$105$lambda$42(mutableState14);
                    Modifier modifierFillMaxWidth$default9 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278402677, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged24 = composerStartRestartGroup.changed(mutableState14);
                    Object objRememberedValue28 = composerStartRestartGroup.rememberedValue();
                    if (zChanged24 || objRememberedValue28 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue28 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda24
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$78$lambda$77(mutableState14, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue28);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$42, (Function1<? super String, Unit>) objRememberedValue28, modifierFillMaxWidth$default9, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7636getLambda$1458704338$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                    Modifier modifierFillMaxWidth$default10 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_7 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_7, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default10);
                    Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor6);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM4301constructorimpl6 = Updater.m4301constructorimpl(composerStartRestartGroup);
                    Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                        composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                        composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 614249670, "C90@6555L19,90@6501L140,91@6713L16,91@6662L134:BookEditorScreen.kt#ska5t9");
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$45 = BookEditorScreen$lambda$111$lambda$105$lambda$45(mutableState15);
                    Modifier modifierWeight$default5 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -395826039, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged25 = composerStartRestartGroup.changed(mutableState15);
                    Object objRememberedValue29 = composerStartRestartGroup.rememberedValue();
                    if (zChanged25 || objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue29 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda27
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m744x74e1aba7(mutableState15, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue29);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$45, (Function1<? super String, Unit>) objRememberedValue29, modifierWeight$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1590060560$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$48 = BookEditorScreen$lambda$111$lambda$105$lambda$48(mutableState16);
                    Modifier modifierWeight$default6 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -395820986, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged26 = composerStartRestartGroup.changed(mutableState16);
                    Object objRememberedValue30 = composerStartRestartGroup.rememberedValue();
                    if (zChanged26 || objRememberedValue30 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue30 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda28
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m745x9eab1140(mutableState16, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue30);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$48, (Function1<? super String, Unit>) objRememberedValue30, modifierWeight$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$113199495$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    Modifier modifierFillMaxWidth$default11 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_8 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_8, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default11);
                    Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor7);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM4301constructorimpl7 = Updater.m4301constructorimpl(composerStartRestartGroup);
                    Updater.m4308setimpl(composerM4301constructorimpl7, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                        composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                        composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1801190352, "C94@6989L42,94@6934L164,95@7167L13,95@7119L131:BookEditorScreen.kt#ska5t9");
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$57 = BookEditorScreen$lambda$111$lambda$105$lambda$57(mutableState19);
                    Modifier modifierWeight$default7 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1743013889, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged27 = composerStartRestartGroup.changed(mutableState19);
                    Object objRememberedValue31 = composerStartRestartGroup.rememberedValue();
                    if (zChanged27 || objRememberedValue31 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue31 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda29
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m746x3dd39d4e(mutableState19, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue31);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$57, (Function1<? super String, Unit>) objRememberedValue31, modifierWeight$default7, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$358768017$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$54 = BookEditorScreen$lambda$111$lambda$105$lambda$54(mutableState18);
                    Modifier modifierWeight$default8 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1743019556, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged28 = composerStartRestartGroup.changed(mutableState18);
                    Object objRememberedValue32 = composerStartRestartGroup.rememberedValue();
                    if (zChanged28 || objRememberedValue32 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue32 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda30
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.m747x679d02d2(mutableState18, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue32);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$54, (Function1<? super String, Unit>) objRememberedValue32, modifierWeight$default8, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.m7635getLambda$1118093048$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String strBookEditorScreen$lambda$111$lambda$105$lambda$51 = BookEditorScreen$lambda$111$lambda$105$lambda$51(mutableState17);
                    Modifier modifierFillMaxWidth$default12 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278369368, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChanged29 = composerStartRestartGroup.changed(mutableState17);
                    Object objRememberedValue33 = composerStartRestartGroup.rememberedValue();
                    if (zChanged29 || objRememberedValue33 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue33 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda31
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$91$lambda$90(mutableState17, (String) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue33);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    OutlinedTextFieldKt.OutlinedTextField(strBookEditorScreen$lambda$111$lambda$105$lambda$51, (Function1<? super String, Unit>) objRememberedValue33, modifierFillMaxWidth$default12, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1604970415$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 3, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -278364524, "CC(remember):BookEditorScreen.kt#9igjgp");
                    boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(masrofViewModel) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changed(mutableState7) | composerStartRestartGroup.changed(mutableState8) | composerStartRestartGroup.changed(mutableState9) | composerStartRestartGroup.changed(mutableState10) | composerStartRestartGroup.changed(mutableState11) | composerStartRestartGroup.changed(mutableState17) | composerStartRestartGroup.changed(mutableState12) | composerStartRestartGroup.changed(mutableState13) | composerStartRestartGroup.changed(mutableState14) | composerStartRestartGroup.changed(mutableState15) | composerStartRestartGroup.changed(mutableState16) | composerStartRestartGroup.changed(mutableState18) | composerStartRestartGroup.changed(mutableState19) | composerStartRestartGroup.changed(document2);
                    Object objRememberedValue34 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue34 == Composer.INSTANCE.getEmpty()) {
                        document = document2;
                        viewModel = masrofViewModel;
                        objRememberedValue34 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda32
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$93$lambda$92(viewModel, document, mutableState6, mutableState7, mutableState8, mutableState9, mutableState10, mutableState11, mutableState17, mutableState12, mutableState13, mutableState14, mutableState15, mutableState16, mutableState18, mutableState19);
                            }
                        };
                        composer2 = composerStartRestartGroup;
                        composer2.updateRememberedValue(objRememberedValue34);
                    } else {
                        composer2 = composerStartRestartGroup;
                        viewModel = masrofViewModel;
                        document = document2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    z = true;
                    Composer composer3 = composer2;
                    ButtonKt.Button((Function0) objRememberedValue34, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1783692938, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$94(document, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, composer2, 54), composer3, 805306416, 508);
                    ComposerKt.sourceInformationMarkerStart(composer3, -278338722, "CC(remember):BookEditorScreen.kt#9igjgp");
                    Object objRememberedValue35 = composer3.rememberedValue();
                    if (objRememberedValue35 == Composer.INSTANCE.getEmpty()) {
                        mutableState2 = mutableState5;
                        objRememberedValue35 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$96$lambda$95(mutableState2);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue35);
                    } else {
                        mutableState2 = mutableState5;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    final MutableState mutableState20 = mutableState2;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue35, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(391211916, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$97(mutableState2, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, composer3, 54), composer3, 805306422, 508);
                    if (BookEditorScreen$lambda$11(mutableState20)) {
                        composer3.startReplaceGroup(-38298413);
                        ComposerKt.sourceInformation(composer3, "115@8622L128,115@8577L173");
                        CardKt.Card(SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(680.0f)), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(536204839, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj2, Object obj3, Object obj4) {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$100(viewModel, document, mutableState20, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                            }
                        }, composer3, 54), composer3, 196614, 30);
                    } else {
                        composer3.startReplaceGroup(-46792568);
                    }
                    composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerStart(composer3, -278322128, "CC(remember):BookEditorScreen.kt#9igjgp");
                    Object objRememberedValue36 = composer3.rememberedValue();
                    if (objRememberedValue36 == Composer.INSTANCE.getEmpty()) {
                        mutableState3 = mutableState4;
                        objRememberedValue36 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$102$lambda$101(mutableState3);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue36);
                    } else {
                        mutableState3 = mutableState4;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    str = "CC(remember):BookEditorScreen.kt#9igjgp";
                    mutableState = mutableState3;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue36, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1607680771$app(), composer3, 805306422, 508);
                    ComposerKt.sourceInformationMarkerStart(composer3, -278317572, str);
                    boolean zChangedInstance3 = composer3.changedInstance(context) | composer3.changedInstance(list2);
                    Object objRememberedValue37 = composer3.rememberedValue();
                    if (zChangedInstance3 || objRememberedValue37 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue37 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$105$lambda$104$lambda$103(context, list2);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue37);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    list2 = list2;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue37, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$376388228$app(), composer3, 805306416, 508);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    Modifier modifierFillMaxWidth$default13 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_9 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                    ComposerKt.sourceInformationMarkerStart(composer3, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_9, Alignment.INSTANCE.getTop(), composer3, 6);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default13);
                    Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor8);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM4301constructorimpl8 = Updater.m4301constructorimpl(composer3);
                    Updater.m4308setimpl(composerM4301constructorimpl8, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                        composerM4301constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                        composerM4301constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer3, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer3, -1655704358, "C126@9391L15,126@9341L117,127@9531L15,127@9475L123:BookEditorScreen.kt#ska5t9");
                    boolean z2 = BookEditorScreen$lambda$5(r45) > 0;
                    Modifier modifierWeight$default9 = RowScope.weight$default(rowScopeInstance6, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1023239785, str);
                    Object objRememberedValue38 = composer3.rememberedValue();
                    if (objRememberedValue38 == Composer.INSTANCE.getEmpty()) {
                        mutableIntState2 = mutableIntState4;
                        objRememberedValue38 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$110$lambda$107$lambda$106(mutableIntState2);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue38);
                    } else {
                        mutableIntState2 = r45;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    final MutableIntState mutableIntState5 = mutableIntState2;
                    ButtonKt.OutlinedButton((Function0) objRememberedValue38, modifierWeight$default9, z2, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1548638586$app(), composer3, 805306374, 504);
                    boolean z3 = BookEditorScreen$lambda$5(mutableIntState5) < CollectionsKt.getLastIndex(list2);
                    Modifier modifierWeight$default10 = RowScope.weight$default(rowScopeInstance6, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1023235305, str);
                    Object objRememberedValue39 = composer3.rememberedValue();
                    if (objRememberedValue39 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue39 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda9
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BookEditorScreenKt.BookEditorScreen$lambda$111$lambda$110$lambda$109$lambda$108(mutableIntState5);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue39);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ButtonKt.Button((Function0) objRememberedValue39, modifierWeight$default10, z3, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$2134868728$app(), composer3, 805306374, 504);
                    composerStartRestartGroup = composer3;
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                } else {
                    viewModel = masrofViewModel;
                    mutableState = mutableState4;
                    str = "CC(remember):BookEditorScreen.kt#9igjgp";
                    composerStartRestartGroup.startReplaceGroup(802421602);
                }
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (BookEditorScreen$lambda$8(mutableState)) {
                composerStartRestartGroup.startReplaceGroup(2014786393);
                ComposerKt.sourceInformation(composerStartRestartGroup, "133@9704L23,136@10073L135,137@10238L67,135@9803L240,132@9660L655");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -350647807, str);
                Object objRememberedValue40 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue40 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue40 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BookEditorScreenKt.BookEditorScreen$lambda$113$lambda$112(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue40);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function1 = onPreview;
                final List list3 = list2;
                Composer composer4 = composerStartRestartGroup;
                AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0((Function0) objRememberedValue40, ComposableLambdaKt.rememberComposableLambda(335531901, z, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return BookEditorScreenKt.BookEditorScreen$lambda$117(function1, list3, mutableState, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-1032928709, z, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return BookEditorScreenKt.BookEditorScreen$lambda$120(mutableState, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1893577977$app(), ComposableLambdaKt.rememberComposableLambda(1209347672, z, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return BookEditorScreenKt.BookEditorScreen$lambda$124(list3, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer4, 1772598, 0, 16276);
                composerStartRestartGroup = composer4;
            } else {
                function1 = onPreview;
                composerStartRestartGroup.startReplaceGroup(2005191800);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return BookEditorScreenKt.BookEditorScreen$lambda$125(viewModel, bookTag, function1, onOpenCanvas, onBack, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int BookEditorScreen$lambda$5(MutableIntState mutableIntState) {
        return mutableIntState.getIntValue();
    }

    private static final boolean BookEditorScreen$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void BookEditorScreen$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean BookEditorScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void BookEditorScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$16(Document document, MutableIntState mutableIntState, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C55@2829L356:BookEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1926027380, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous>.<anonymous>.<anonymous> (BookEditorScreen.kt:55)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f));
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
            ComposerKt.sourceInformationMarkerStart(composer, 1297906413, "C56@2971L10,56@2887L107,57@3142L10,57@3019L144:BookEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("صفحة " + (BookEditorScreen$lambda$5(mutableIntState) + 1) + " — رقم " + document.getDocumentNumber(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleMedium(), composer, 0, 0, 65534);
            TextKt.m3342Text4IGK_g("حرر البيانات من الأعلى إلى الأسفل، ثم افتح محرر المستندات للتحكم بالعناصر داخل الصفحة نفسها.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 6, 0, 65534);
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

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$18(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$21(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$24(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$27(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$30(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$33(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$36(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$39(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$42(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$45(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$48(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$51(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$54(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String BookEditorScreen$lambda$111$lambda$105$lambda$57(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$63$lambda$60$lambda$59 */
    static final Unit m740x5872eb6d(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$63$lambda$62$lambda$61 */
    static final Unit m741x823c5106(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$65$lambda$64(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$67$lambda$66(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$69$lambda$68(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$71$lambda$70(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$76$lambda$73$lambda$72 */
    static final Unit m742xcb234468(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$76$lambda$75$lambda$74 */
    static final Unit m743xf4eca9ec(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$78$lambda$77(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$83$lambda$80$lambda$79 */
    static final Unit m744x74e1aba7(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$83$lambda$82$lambda$81 */
    static final Unit m745x9eab1140(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$89$lambda$88$lambda$87 */
    static final Unit m747x679d02d2(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$91$lambda$90(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$93$lambda$92(MasrofViewModel masrofViewModel, Document document, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, MutableState mutableState10, MutableState mutableState11, MutableState mutableState12, MutableState mutableState13, MutableState mutableState14) {
        String strBookEditorScreen$lambda$111$lambda$105$lambda$18 = BookEditorScreen$lambda$111$lambda$105$lambda$18(mutableState);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$21 = BookEditorScreen$lambda$111$lambda$105$lambda$21(mutableState2);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$24 = BookEditorScreen$lambda$111$lambda$105$lambda$24(mutableState3);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$27 = BookEditorScreen$lambda$111$lambda$105$lambda$27(mutableState4);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$30 = BookEditorScreen$lambda$111$lambda$105$lambda$30(mutableState5);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$33 = BookEditorScreen$lambda$111$lambda$105$lambda$33(mutableState6);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$51 = BookEditorScreen$lambda$111$lambda$105$lambda$51(mutableState7);
        Double doubleOrNull = StringsKt.toDoubleOrNull(BookEditorScreen$lambda$111$lambda$105$lambda$36(mutableState8));
        String strBookEditorScreen$lambda$111$lambda$105$lambda$39 = BookEditorScreen$lambda$111$lambda$105$lambda$39(mutableState9);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$42 = BookEditorScreen$lambda$111$lambda$105$lambda$42(mutableState10);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$45 = BookEditorScreen$lambda$111$lambda$105$lambda$45(mutableState11);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$48 = BookEditorScreen$lambda$111$lambda$105$lambda$48(mutableState12);
        String strBookEditorScreen$lambda$111$lambda$105$lambda$54 = BookEditorScreen$lambda$111$lambda$105$lambda$54(mutableState13);
        Integer intOrNull = StringsKt.toIntOrNull(BookEditorScreen$lambda$111$lambda$105$lambda$57(mutableState14));
        masrofViewModel.updateDocument(Document.copy$default(document, 0L, null, null, strBookEditorScreen$lambda$111$lambda$105$lambda$18, strBookEditorScreen$lambda$111$lambda$105$lambda$21, doubleOrNull, strBookEditorScreen$lambda$111$lambda$105$lambda$39, strBookEditorScreen$lambda$111$lambda$105$lambda$24, strBookEditorScreen$lambda$111$lambda$105$lambda$30, strBookEditorScreen$lambda$111$lambda$105$lambda$33, strBookEditorScreen$lambda$111$lambda$105$lambda$51, null, intOrNull != null ? intOrNull.intValue() : 0, 0L, false, null, System.currentTimeMillis(), strBookEditorScreen$lambda$111$lambda$105$lambda$54, strBookEditorScreen$lambda$111$lambda$105$lambda$42, strBookEditorScreen$lambda$111$lambda$105$lambda$45, strBookEditorScreen$lambda$111$lambda$105$lambda$48, strBookEditorScreen$lambda$111$lambda$105$lambda$27, null, null, null, null, null, null, null, 532736007, null));
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$94(Document document, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C109@8202L44:BookEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1783692938, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous>.<anonymous>.<anonymous> (BookEditorScreen.kt:109)");
            }
            TextKt.m3342Text4IGK_g("حفظ الصفحة " + document.getDocumentNumber(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$96$lambda$95(MutableState mutableState) {
        BookEditorScreen$lambda$12(mutableState, !BookEditorScreen$lambda$11(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$97(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C112@8387L113:BookEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(391211916, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous>.<anonymous>.<anonymous> (BookEditorScreen.kt:112)");
            }
            TextKt.m3342Text4IGK_g(BookEditorScreen$lambda$11(mutableState) ? "إغلاق محرر المستندات" : "تحرير الصفحة داخل المحرر المدمج — النصوص والمربعات والصور", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$100(MasrofViewModel masrofViewModel, Document document, final MutableState mutableState, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C116@8701L26,116@8648L80:BookEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(536204839, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous>.<anonymous>.<anonymous> (BookEditorScreen.kt:116)");
            }
            DocumentType type = document.getType();
            ComposerKt.sourceInformationMarkerStart(composer, -103222687, "CC(remember):BookEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BookEditorScreenKt.m739x195eb842(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            CanvasEditorScreenKt.CanvasEditorScreen(masrofViewModel, type, (Function0) objRememberedValue, composer, 384);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$100$lambda$99$lambda$98 */
    static final Unit m739x195eb842(MutableState mutableState) {
        BookEditorScreen$lambda$12(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$102$lambda$101(MutableState mutableState) {
        BookEditorScreen$lambda$9(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$105$lambda$104$lambda$103(Context context, List list) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportPdf(context, list), "مشاركة دفتر المستندات PDF");
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$110$lambda$107$lambda$106(MutableIntState mutableIntState) {
        mutableIntState.setIntValue(BookEditorScreen$lambda$5(mutableIntState) - 1);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$111$lambda$110$lambda$109$lambda$108(MutableIntState mutableIntState) {
        mutableIntState.setIntValue(BookEditorScreen$lambda$5(mutableIntState) + 1);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$113$lambda$112(MutableState mutableState) {
        BookEditorScreen$lambda$9(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$124(List list, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C135@9836L21,135@9805L236:BookEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1209347672, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous> (BookEditorScreen.kt:135)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(Modifier.INSTANCE, ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null);
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScroll$default);
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
            ComposerKt.sourceInformationMarkerStart(composer, -835058352, "C135@9862L34:BookEditorScreen.kt#ska5t9");
            int i2 = 0;
            TextKt.m3342Text4IGK_g("عدد الصفحات: " + list.size(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            composer.startReplaceGroup(1358537257);
            ComposerKt.sourceInformation(composer, "*135@9935L102");
            Iterator it = list.iterator();
            while (true) {
                int i3 = i2;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Document document = (Document) next;
                String documentNumber = document.getDocumentNumber();
                String beneficiaryName = document.getBeneficiaryName();
                if (beneficiaryName == null) {
                    beneficiaryName = "";
                }
                String str = beneficiaryName;
                if (StringsKt.isBlank(str)) {
                    str = "غير مكتمل";
                }
                i2 = i4;
                TextKt.m3342Text4IGK_g(i4 + ". " + documentNumber + " — " + ((Object) str), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            }
            composer.endReplaceGroup();
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

    static final Unit BookEditorScreen$lambda$117(final Function1 function1, final List list, final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C136@10092L80,136@10075L131:BookEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(335531901, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous> (BookEditorScreen.kt:136)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 323610349, "CC(remember):BookEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function1) | composer.changedInstance(list);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BookEditorScreenKt.BookEditorScreen$lambda$117$lambda$116$lambda$115(function1, list, mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.getLambda$1778314637$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$117$lambda$116$lambda$115(Function1 function1, List list, MutableState mutableState) {
        BookEditorScreen$lambda$9(mutableState, false);
        function1.invoke(CollectionsKt.joinToString$default(list, ",", null, null, 0, null, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BookEditorScreenKt.BookEditorScreen$lambda$117$lambda$116$lambda$115$lambda$114((Document) obj);
            }
        }, 30, null));
        return Unit.INSTANCE;
    }

    static final CharSequence BookEditorScreen$lambda$117$lambda$116$lambda$115$lambda$114(Document it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return String.valueOf(it.getId());
    }

    static final Unit BookEditorScreen$lambda$120(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C137@10261L23,137@10240L63:BookEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1032928709, i, -1, "com.mohammedalhzmi.masrofmanager.ui.BookEditorScreen.<anonymous> (BookEditorScreen.kt:137)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -507043214, "CC(remember):BookEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.BookEditorScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BookEditorScreenKt.BookEditorScreen$lambda$120$lambda$119$lambda$118(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$BookEditorScreenKt.INSTANCE.m7642getLambda$946957666$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit BookEditorScreen$lambda$120$lambda$119$lambda$118(MutableState mutableState) {
        BookEditorScreen$lambda$9(mutableState, false);
        return Unit.INSTANCE;
    }

    private static final String title(DocumentType documentType) {
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        if (i == 1) {
            return "طلب تقديم";
        }
        if (i == 2) {
            return "أمر صرف";
        }
        if (i == 3) {
            return "ورقة استلام";
        }
        return documentType.name();
    }

    private static final List<Document> BookEditorScreen$lambda$0(State<? extends List<Document>> state) {
        return state.getValue();
    }

    /* JADX INFO: renamed from: BookEditorScreen$lambda$111$lambda$105$lambda$89$lambda$86$lambda$85 */
    static final Unit m746x3dd39d4e(MutableState mutableState, String it) throws IOException {
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
        mutableState.setValue(sb.toString());
        return Unit.INSTANCE;
    }
}
