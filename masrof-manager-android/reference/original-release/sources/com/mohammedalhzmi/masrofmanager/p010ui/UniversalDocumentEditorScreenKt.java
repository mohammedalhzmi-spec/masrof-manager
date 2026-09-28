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
import androidx.compose.material3.CardKt;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.text.util.LocalePreferences;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UniversalDocumentEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000f\u001aW\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\f¨\u0006\r²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\bX\u008a\u008e\u0002"}, m914d2 = {"UniversalDocumentEditorScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "document", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "onPreview", "Lkotlin/Function1;", "", "onBack", "Lkotlin/Function0;", "onSaveNew", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lcom/mohammedalhzmi/masrofmanager/data/Document;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "app", "showPreview", "", "showDocumentEditor", "hijri", LocalePreferences.CalendarType.GREGORIAN, "beneficiary", "beneficiaryId", "purpose", "details", "amount", "amountWords", "category", "costCenter", "funding", "notes", "tags", "attachments"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class UniversalDocumentEditorScreenKt {
    static final Unit UniversalDocumentEditorScreen$lambda$110(MasrofViewModel masrofViewModel, Document document, Function1 function1, Function0 function0, Function1 function2, int i, int i2, Composer composer, int i3) {
        UniversalDocumentEditorScreen(masrofViewModel, document, function1, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x022b  */
    /* JADX WARN: Code duplicated, block: B:104:0x024e  */
    /* JADX WARN: Code duplicated, block: B:108:0x0259  */
    /* JADX WARN: Code duplicated, block: B:110:0x025f  */
    /* JADX WARN: Code duplicated, block: B:111:0x0264  */
    /* JADX WARN: Code duplicated, block: B:113:0x0267  */
    /* JADX WARN: Code duplicated, block: B:117:0x028a  */
    /* JADX WARN: Code duplicated, block: B:121:0x0295  */
    /* JADX WARN: Code duplicated, block: B:123:0x029b  */
    /* JADX WARN: Code duplicated, block: B:127:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:131:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:134:0x02f3  */
    /* JADX WARN: Code duplicated, block: B:138:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:141:0x0326  */
    /* JADX WARN: Code duplicated, block: B:145:0x0331  */
    /* JADX WARN: Code duplicated, block: B:148:0x0359  */
    /* JADX WARN: Code duplicated, block: B:152:0x0364  */
    /* JADX WARN: Code duplicated, block: B:155:0x036b  */
    /* JADX WARN: Code duplicated, block: B:159:0x038e  */
    /* JADX WARN: Code duplicated, block: B:161:0x0396  */
    /* JADX WARN: Code duplicated, block: B:164:0x03bf  */
    /* JADX WARN: Code duplicated, block: B:168:0x03ca  */
    /* JADX WARN: Code duplicated, block: B:171:0x0442  */
    /* JADX WARN: Code duplicated, block: B:174:0x044e  */
    /* JADX WARN: Code duplicated, block: B:175:0x0452  */
    /* JADX WARN: Code duplicated, block: B:178:0x0477  */
    /* JADX WARN: Code duplicated, block: B:180:0x0485  */
    /* JADX WARN: Code duplicated, block: B:183:0x0503  */
    /* JADX WARN: Code duplicated, block: B:186:0x050f  */
    /* JADX WARN: Code duplicated, block: B:187:0x0513  */
    /* JADX WARN: Code duplicated, block: B:190:0x0538  */
    /* JADX WARN: Code duplicated, block: B:192:0x0546  */
    /* JADX WARN: Code duplicated, block: B:195:0x06f3  */
    /* JADX WARN: Code duplicated, block: B:198:0x06ff  */
    /* JADX WARN: Code duplicated, block: B:199:0x0703  */
    /* JADX WARN: Code duplicated, block: B:202:0x0728  */
    /* JADX WARN: Code duplicated, block: B:204:0x0736  */
    /* JADX WARN: Code duplicated, block: B:207:0x07f6  */
    /* JADX WARN: Code duplicated, block: B:210:0x0802  */
    /* JADX WARN: Code duplicated, block: B:211:0x0806  */
    /* JADX WARN: Code duplicated, block: B:214:0x082b  */
    /* JADX WARN: Code duplicated, block: B:216:0x0839  */
    /* JADX WARN: Code duplicated, block: B:219:0x0890  */
    /* JADX WARN: Code duplicated, block: B:221:0x0898  */
    /* JADX WARN: Code duplicated, block: B:224:0x091f  */
    /* JADX WARN: Code duplicated, block: B:226:0x0927  */
    /* JADX WARN: Code duplicated, block: B:229:0x09a0  */
    /* JADX WARN: Code duplicated, block: B:231:0x09a8  */
    /* JADX WARN: Code duplicated, block: B:234:0x0a10  */
    /* JADX WARN: Code duplicated, block: B:236:0x0a18  */
    /* JADX WARN: Code duplicated, block: B:239:0x0a80  */
    /* JADX WARN: Code duplicated, block: B:241:0x0a88  */
    /* JADX WARN: Code duplicated, block: B:244:0x0af0  */
    /* JADX WARN: Code duplicated, block: B:246:0x0af8  */
    /* JADX WARN: Code duplicated, block: B:249:0x0b92  */
    /* JADX WARN: Code duplicated, block: B:252:0x0b9e  */
    /* JADX WARN: Code duplicated, block: B:253:0x0ba2  */
    /* JADX WARN: Code duplicated, block: B:256:0x0bc7  */
    /* JADX WARN: Code duplicated, block: B:258:0x0bd5  */
    /* JADX WARN: Code duplicated, block: B:261:0x0c2a  */
    /* JADX WARN: Code duplicated, block: B:263:0x0c32  */
    /* JADX WARN: Code duplicated, block: B:266:0x0caa  */
    /* JADX WARN: Code duplicated, block: B:268:0x0cb2  */
    /* JADX WARN: Code duplicated, block: B:271:0x0d4d  */
    /* JADX WARN: Code duplicated, block: B:273:0x0d55  */
    /* JADX WARN: Code duplicated, block: B:276:0x0df0  */
    /* JADX WARN: Code duplicated, block: B:279:0x0dfc  */
    /* JADX WARN: Code duplicated, block: B:280:0x0e00  */
    /* JADX WARN: Code duplicated, block: B:283:0x0e25  */
    /* JADX WARN: Code duplicated, block: B:285:0x0e33  */
    /* JADX WARN: Code duplicated, block: B:288:0x0e88  */
    /* JADX WARN: Code duplicated, block: B:290:0x0e90  */
    /* JADX WARN: Code duplicated, block: B:293:0x0f09  */
    /* JADX WARN: Code duplicated, block: B:295:0x0f11  */
    /* JADX WARN: Code duplicated, block: B:298:0x0fbd  */
    /* JADX WARN: Code duplicated, block: B:301:0x0fc9  */
    /* JADX WARN: Code duplicated, block: B:302:0x0fcd  */
    /* JADX WARN: Code duplicated, block: B:305:0x0ff2  */
    /* JADX WARN: Code duplicated, block: B:307:0x1000  */
    /* JADX WARN: Code duplicated, block: B:310:0x1051  */
    /* JADX WARN: Code duplicated, block: B:312:0x1059  */
    /* JADX WARN: Code duplicated, block: B:315:0x10c7  */
    /* JADX WARN: Code duplicated, block: B:317:0x10cf  */
    /* JADX WARN: Code duplicated, block: B:320:0x1149  */
    /* JADX WARN: Code duplicated, block: B:322:0x1151  */
    /* JADX WARN: Code duplicated, block: B:325:0x120c  */
    /* JADX WARN: Code duplicated, block: B:326:0x120f  */
    /* JADX WARN: Code duplicated, block: B:329:0x121e  */
    /* JADX WARN: Code duplicated, block: B:330:0x1220  */
    /* JADX WARN: Code duplicated, block: B:333:0x122f  */
    /* JADX WARN: Code duplicated, block: B:337:0x1246  */
    /* JADX WARN: Code duplicated, block: B:340:0x12d4  */
    /* JADX WARN: Code duplicated, block: B:341:0x12df  */
    /* JADX WARN: Code duplicated, block: B:344:0x1323  */
    /* JADX WARN: Code duplicated, block: B:345:0x1360  */
    /* JADX WARN: Code duplicated, block: B:348:0x137d  */
    /* JADX WARN: Code duplicated, block: B:349:0x1388  */
    /* JADX WARN: Code duplicated, block: B:352:0x13cd  */
    /* JADX WARN: Code duplicated, block: B:353:0x13cf  */
    /* JADX WARN: Code duplicated, block: B:356:0x13d8  */
    /* JADX WARN: Code duplicated, block: B:358:0x13e0  */
    /* JADX WARN: Code duplicated, block: B:361:0x142a  */
    /* JADX WARN: Code duplicated, block: B:362:0x142c  */
    /* JADX WARN: Code duplicated, block: B:365:0x1436  */
    /* JADX WARN: Code duplicated, block: B:367:0x143e  */
    /* JADX WARN: Code duplicated, block: B:370:0x149c  */
    /* JADX WARN: Code duplicated, block: B:372:0x14b9  */
    /* JADX WARN: Code duplicated, block: B:373:0x14c4  */
    /* JADX WARN: Code duplicated, block: B:375:0x1524  */
    /* JADX WARN: Code duplicated, block: B:378:0x1535  */
    /* JADX WARN: Code duplicated, block: B:382:0x1540  */
    /* JADX WARN: Code duplicated, block: B:384:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:0x009d  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:51:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:57:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:60:0x0100  */
    /* JADX WARN: Code duplicated, block: B:63:0x0126  */
    /* JADX WARN: Code duplicated, block: B:65:0x012e  */
    /* JADX WARN: Code duplicated, block: B:68:0x0154  */
    /* JADX WARN: Code duplicated, block: B:70:0x015c  */
    /* JADX WARN: Code duplicated, block: B:73:0x0185  */
    /* JADX WARN: Code duplicated, block: B:75:0x018d  */
    /* JADX WARN: Code duplicated, block: B:77:0x0193  */
    /* JADX WARN: Code duplicated, block: B:81:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:83:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:86:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:88:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:90:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:94:0x021a  */
    /* JADX WARN: Code duplicated, block: B:98:0x0225  */
    public static final void UniversalDocumentEditorScreen(final MasrofViewModel viewModel, final Document document, Function1<? super String, Unit> onPreview, final Function0<Unit> onBack, Function1<? super Document, Unit> function1, Composer composer, final int i, final int i2) {
        int i3;
        Function1<? super Document, Unit> function2;
        Function1<? super Document, Unit> function3;
        final Context context;
        Object objRememberedValue;
        MutableState mutableState;
        Object objRememberedValue2;
        MutableState mutableState2;
        boolean zChanged;
        Object objRememberedValue3;
        final MutableState mutableState3;
        boolean zChanged2;
        Object objRememberedValue4;
        final MutableState mutableState4;
        int i4;
        boolean zChanged3;
        Object objRememberedValue5;
        String beneficiaryName;
        final MutableState mutableState5;
        boolean zChanged4;
        Object objRememberedValue6;
        final MutableState mutableState6;
        boolean zChanged5;
        Object objRememberedValue7;
        String purpose;
        final MutableState mutableState7;
        boolean zChanged6;
        String details;
        Object objMutableStateOf$default;
        final MutableState mutableState8;
        boolean zChanged7;
        Double amount;
        String string;
        Object objMutableStateOf$default2;
        final MutableState mutableState9;
        boolean zChanged8;
        String amountWords;
        Object objMutableStateOf$default3;
        final MutableState mutableState10;
        boolean zChanged9;
        Object objMutableStateOf$default4;
        final MutableState mutableState11;
        boolean zChanged10;
        Object objMutableStateOf$default5;
        final MutableState mutableState12;
        boolean zChanged11;
        Object objMutableStateOf$default6;
        final MutableState mutableState13;
        boolean zChanged12;
        Object objMutableStateOf$default7;
        final MutableState mutableState14;
        boolean zChanged13;
        Object objRememberedValue8;
        final MutableState mutableState15;
        boolean zChanged14;
        Object objRememberedValue9;
        Object obj;
        final MutableState mutableState16;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM4301constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Composer composerM4301constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        int currentCompositeKeyHash3;
        Function0<ComposeUiNode> constructor3;
        Composer composerM4301constructorimpl3;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3;
        int currentCompositeKeyHash4;
        Function0<ComposeUiNode> constructor4;
        Composer composerM4301constructorimpl4;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4;
        boolean zChanged15;
        Object objRememberedValue10;
        boolean zChanged16;
        Object objRememberedValue11;
        boolean zChanged17;
        Object objRememberedValue12;
        boolean zChanged18;
        Object objRememberedValue13;
        boolean zChanged19;
        Object objRememberedValue14;
        boolean zChanged20;
        Object objRememberedValue15;
        int currentCompositeKeyHash5;
        Function0<ComposeUiNode> constructor5;
        Composer composerM4301constructorimpl5;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5;
        boolean zChanged21;
        Object objRememberedValue16;
        boolean zChanged22;
        Object objRememberedValue17;
        boolean zChanged23;
        Object objRememberedValue18;
        int currentCompositeKeyHash6;
        Function0<ComposeUiNode> constructor6;
        Composer composerM4301constructorimpl6;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6;
        boolean zChanged24;
        Object objRememberedValue19;
        boolean zChanged25;
        Object objRememberedValue20;
        int currentCompositeKeyHash7;
        Function0<ComposeUiNode> constructor7;
        Composer composerM4301constructorimpl7;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7;
        boolean zChanged26;
        Object objRememberedValue21;
        boolean zChanged27;
        Object objRememberedValue22;
        boolean zChanged28;
        Object objRememberedValue23;
        int i5;
        boolean z;
        boolean z2;
        final MasrofViewModel masrofViewModel;
        boolean zChangedInstance;
        Object objRememberedValue24;
        boolean z3;
        Function1<? super Document, Unit> function4;
        final MutableState mutableState17;
        Composer composer2;
        MutableState mutableState18;
        Composer composer3;
        final MutableState mutableState19;
        Object objRememberedValue25;
        final MutableState mutableState20;
        final MutableState mutableState21;
        Object objRememberedValue26;
        final MutableState mutableState22;
        MutableState mutableState23;
        boolean z4;
        boolean z5;
        Object objRememberedValue27;
        boolean z6;
        boolean z7;
        Object objRememberedValue28;
        final Function1<? super String, Unit> function5;
        final Function1<? super Document, Unit> function6;
        Object objRememberedValue29;
        final MutableState mutableState24;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final Document document2 = document;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(document2, "document");
        Intrinsics.checkNotNullParameter(onPreview, "onPreview");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(276897434);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UniversalDocumentEditorScreen)P(4!1,2)21@748L7,22@779L34,24@929L33,25@980L60,26@1062L64,27@1150L76,28@1252L64,29@1336L68,30@1424L68,31@1511L79,32@1614L72,33@1707L68,34@1798L61,35@1879L64,36@1961L66,37@2044L55,38@2123L78,40@2207L5386:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(document2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onPreview) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onBack) ? 2048 : 1024;
        }
        int i6 = i2 & 16;
        if (i6 == 0) {
            if ((i & 24576) == 0) {
                function2 = function1;
                i3 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
            }
            if ((i3 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                if (i6 != 0) {
                    function3 = null;
                } else {
                    function3 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(276897434, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen (UniversalDocumentEditorScreen.kt:20)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189529604, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189524805, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189523146, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(id);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                mutableState3 = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id2 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189520518, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(id2);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                    objRememberedValue4 = mutableStateMutableStateOf$default;
                }
                mutableState4 = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                i4 = i3;
                long id3 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189517690, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(id3);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    beneficiaryName = document2.getBeneficiaryName();
                    if (beneficiaryName == null) {
                        beneficiaryName = "";
                    }
                    objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                mutableState5 = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id4 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189514438, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged4 = composerStartRestartGroup.changed(id4);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (zChanged4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                    objRememberedValue6 = mutableStateMutableStateOf$default2;
                }
                mutableState6 = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id5 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189511746, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged5 = composerStartRestartGroup.changed(id5);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChanged5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    purpose = document2.getPurpose();
                    if (purpose == null) {
                        purpose = "";
                    }
                    objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                mutableState7 = (MutableState) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id6 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189508930, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged6 = composerStartRestartGroup.changed(id6);
                Object objRememberedValue30 = composerStartRestartGroup.rememberedValue();
                if (!zChanged6 || objRememberedValue30 == Composer.INSTANCE.getEmpty()) {
                    details = document2.getDetails();
                    if (details == null) {
                        details = "";
                    }
                    objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                } else {
                    objMutableStateOf$default = objRememberedValue30;
                }
                mutableState8 = (MutableState) objMutableStateOf$default;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id7 = document2.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189506135, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged7 = composerStartRestartGroup.changed(id7);
                Object objRememberedValue31 = composerStartRestartGroup.rememberedValue();
                if (!zChanged7 || objRememberedValue31 == Composer.INSTANCE.getEmpty()) {
                    amount = document.getAmount();
                    if (amount != null) {
                        string = amount.toString();
                    } else {
                        string = null;
                    }
                    if (string == null) {
                        string = "";
                    }
                    objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
                } else {
                    objMutableStateOf$default2 = objRememberedValue31;
                }
                mutableState9 = (MutableState) objMutableStateOf$default2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id8 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189502846, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged8 = composerStartRestartGroup.changed(id8);
                Object objRememberedValue32 = composerStartRestartGroup.rememberedValue();
                if (!zChanged8 || objRememberedValue32 == Composer.INSTANCE.getEmpty()) {
                    amountWords = document.getAmountWords();
                    if (amountWords == null) {
                        amountWords = "";
                    }
                    objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
                } else {
                    objMutableStateOf$default3 = objRememberedValue32;
                }
                mutableState10 = (MutableState) objMutableStateOf$default3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id9 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189499874, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged9 = composerStartRestartGroup.changed(id9);
                Object objRememberedValue33 = composerStartRestartGroup.rememberedValue();
                if (!zChanged9 || objRememberedValue33 == Composer.INSTANCE.getEmpty()) {
                    objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFinancialCategory(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
                } else {
                    objMutableStateOf$default4 = objRememberedValue33;
                }
                mutableState11 = (MutableState) objMutableStateOf$default4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id10 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189496969, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged10 = composerStartRestartGroup.changed(id10);
                Object objRememberedValue34 = composerStartRestartGroup.rememberedValue();
                if (!zChanged10 || objRememberedValue34 == Composer.INSTANCE.getEmpty()) {
                    objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getCostCenter(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
                } else {
                    objMutableStateOf$default5 = objRememberedValue34;
                }
                mutableState12 = (MutableState) objMutableStateOf$default5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id11 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189494374, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged11 = composerStartRestartGroup.changed(id11);
                Object objRememberedValue35 = composerStartRestartGroup.rememberedValue();
                if (!zChanged11 || objRememberedValue35 == Composer.INSTANCE.getEmpty()) {
                    objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFundingSource(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default6);
                } else {
                    objMutableStateOf$default6 = objRememberedValue35;
                }
                mutableState13 = (MutableState) objMutableStateOf$default6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id12 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189491748, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged12 = composerStartRestartGroup.changed(id12);
                Object objRememberedValue36 = composerStartRestartGroup.rememberedValue();
                if (!zChanged12 || objRememberedValue36 == Composer.INSTANCE.getEmpty()) {
                    String notes = document.getNotes();
                    objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes != null ? notes : "", null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default7);
                } else {
                    objMutableStateOf$default7 = objRememberedValue36;
                }
                mutableState14 = (MutableState) objMutableStateOf$default7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id13 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189489103, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged13 = composerStartRestartGroup.changed(id13);
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChanged13 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getTags(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                mutableState15 = (MutableState) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                long id14 = document.getId();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189486552, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged14 = composerStartRestartGroup.changed(id14);
                objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (!zChanged14 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    obj = null;
                    objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document.getAttachmentsCount()), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                } else {
                    obj = null;
                }
                mutableState16 = (MutableState) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), C1786Dp.m7249constructorimpl(12.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1658padding3ABfNKs);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 771332839, "C41@2263L236,45@2627L10,45@2508L140,46@2699L21,46@2657L4930:UniversalDocumentEditorScreen.kt#ska5t9");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl2 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -629629462, "C42@2409L10,42@2356L75,43@2444L45:UniversalDocumentEditorScreen.kt#ska5t9");
                TextKt.m3342Text4IGK_g("محرر المستندات المكتبي", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
                ButtonKt.TextButton(onBack, null, false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7706getLambda$2029005263$app(), composerStartRestartGroup, ((i4 >> 9) & 14) | 805306368, 510);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                TextKt.m3342Text4IGK_g(document.getType().name() + " — رقم " + document.getDocumentNumber() + " — الحالة: " + document.getStatus().name(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 0, 0, 65534);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl3 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 932283655, "C47@2853L10,47@2787L89,48@2889L404,52@3361L20,52@3306L169,53@3545L22,53@3488L158,54@3710L16,54@3659L168,55@3891L16,55@3840L156,56@4009L408,60@4482L17,60@4430L150,61@4593L404,65@5010L425,69@5497L14,69@5448L145,70@5623L761,82@6422L80,70@5606L896,83@6540L44,83@6622L89,83@6515L196,85@6910L22,85@6885L115,86@7038L186,86@7013L286,90@7337L175,90@7312L265:UniversalDocumentEditorScreen.kt#ska5t9");
                TextKt.m3342Text4IGK_g("تحرير جميع البيانات داخل صفحة العمل", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleMedium(), composerStartRestartGroup, 6, 0, 65534);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default2);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl4 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1609021994, "C49@3037L14,49@2988L132,50@3190L18,50@3137L142:UniversalDocumentEditorScreen.kt#ska5t9");
                String strUniversalDocumentEditorScreen$lambda$7 = UniversalDocumentEditorScreen$lambda$7(mutableState3);
                Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363739352, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged15 = composerStartRestartGroup.changed(mutableState3);
                objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (zChanged15 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m863x8fa703b5(mutableState3, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$7, (Function1<? super String, Unit>) objRememberedValue10, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7704getLambda$1768396028$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$10 = UniversalDocumentEditorScreen$lambda$10(mutableState4);
                Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363744252, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged16 = composerStartRestartGroup.changed(mutableState4);
                objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (zChanged16 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m864xb970694e(mutableState4, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$10, (Function1<? super String, Unit>) objRememberedValue11, modifierWeight$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1086747195$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String strUniversalDocumentEditorScreen$lambda$13 = UniversalDocumentEditorScreen$lambda$13(mutableState5);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969749914, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged17 = composerStartRestartGroup.changed(mutableState5);
                objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                if (zChanged17 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m865x65c011d(mutableState5, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$13, (Function1<? super String, Unit>) objRememberedValue12, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7703getLambda$1698929376$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$16 = UniversalDocumentEditorScreen$lambda$16(mutableState6);
                Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969755804, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged18 = composerStartRestartGroup.changed(mutableState6);
                objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                if (zChanged18 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m866x302566a1(mutableState6, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$16, (Function1<? super String, Unit>) objRememberedValue13, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1831396503$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$19 = UniversalDocumentEditorScreen$lambda$19(mutableState7);
                Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969761078, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged19 = composerStartRestartGroup.changed(mutableState7);
                objRememberedValue14 = composerStartRestartGroup.rememberedValue();
                if (zChanged19 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue14 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m867x59eecc25(mutableState7, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$19, (Function1<? super String, Unit>) objRememberedValue14, modifierFillMaxWidth$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$412072472$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 2, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                String strUniversalDocumentEditorScreen$lambda$22 = UniversalDocumentEditorScreen$lambda$22(mutableState8);
                Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969766870, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged20 = composerStartRestartGroup.changed(mutableState8);
                objRememberedValue15 = composerStartRestartGroup.rememberedValue();
                if (zChanged20 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m868xba7adb93(mutableState8, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$22, (Function1<? super String, Unit>) objRememberedValue15, modifierFillMaxWidth$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7700getLambda$1007251559$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                Modifier modifierFillMaxWidth$default7 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default7);
                constructor5 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl5 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1199580677, "C57@4158L15,57@4108L135,58@4315L20,58@4260L143:UniversalDocumentEditorScreen.kt#ska5t9");
                String strUniversalDocumentEditorScreen$lambda$25 = UniversalDocumentEditorScreen$lambda$25(mutableState9);
                Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147073488, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged21 = composerStartRestartGroup.changed(mutableState9);
                objRememberedValue16 = composerStartRestartGroup.rememberedValue();
                if (zChanged21 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue16 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m869x2575cb0(mutableState9, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$25, (Function1<? super String, Unit>) objRememberedValue16, modifierWeight$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7708getLambda$501546629$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$28 = UniversalDocumentEditorScreen$lambda$28(mutableState10);
                Modifier modifierWeight$default4 = RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147068459, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged22 = composerStartRestartGroup.changed(mutableState10);
                objRememberedValue17 = composerStartRestartGroup.rememberedValue();
                if (zChanged22 || objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue17 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m870x2c20c234(mutableState10, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$28, (Function1<? super String, Unit>) objRememberedValue17, modifierWeight$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7702getLambda$1193011470$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String strUniversalDocumentEditorScreen$lambda$31 = UniversalDocumentEditorScreen$lambda$31(mutableState11);
                Modifier modifierFillMaxWidth$default8 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969785783, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged23 = composerStartRestartGroup.changed(mutableState11);
                objRememberedValue18 = composerStartRestartGroup.rememberedValue();
                if (zChanged23 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue18 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m871xccbbbee1(mutableState11, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$31, (Function1<? super String, Unit>) objRememberedValue18, modifierFillMaxWidth$default8, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1868391706$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
                Modifier modifierFillMaxWidth$default9 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_7 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_7, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default9);
                constructor6 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl6 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -328759202, "C62@4746L19,62@4692L140,63@4900L16,63@4849L134:UniversalDocumentEditorScreen.kt#ska5t9");
                String strUniversalDocumentEditorScreen$lambda$34 = UniversalDocumentEditorScreen$lambda$34(mutableState12);
                Modifier modifierWeight$default5 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682132981, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged24 = composerStartRestartGroup.changed(mutableState12);
                objRememberedValue19 = composerStartRestartGroup.rememberedValue();
                if (zChanged24 || objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue19 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m872xac15c3ef(mutableState12, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$34, (Function1<? super String, Unit>) objRememberedValue19, modifierWeight$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7705getLambda$1920870660$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$37 = UniversalDocumentEditorScreen$lambda$37(mutableState13);
                Modifier modifierWeight$default6 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682137906, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged25 = composerStartRestartGroup.changed(mutableState13);
                objRememberedValue20 = composerStartRestartGroup.rememberedValue();
                if (zChanged25 || objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m873xd5df2988(mutableState13, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$37, (Function1<? super String, Unit>) objRememberedValue20, modifierWeight$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1682631795$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierFillMaxWidth$default10 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_8 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_8, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default10);
                constructor7 = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM4301constructorimpl7 = Updater.m4301constructorimpl(composerStartRestartGroup);
                Updater.m4308setimpl(composerM4301constructorimpl7, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                    composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                    composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                }
                Updater.m4308setimpl(composerM4301constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 542063048, "C66@5164L42,66@5109L164,67@5338L13,67@5290L131:UniversalDocumentEditorScreen.kt#ska5t9");
                String strUniversalDocumentEditorScreen$lambda$46 = UniversalDocumentEditorScreen$lambda$46(mutableState16);
                Modifier modifierWeight$default7 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783627923, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged26 = composerStartRestartGroup.changed(mutableState16);
                objRememberedValue21 = composerStartRestartGroup.rememberedValue();
                if (zChanged26 || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m874xd38a0d53(mutableState16, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$46, (Function1<? super String, Unit>) objRememberedValue21, modifierWeight$default7, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$954772605$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                String strUniversalDocumentEditorScreen$lambda$43 = UniversalDocumentEditorScreen$lambda$43(mutableState15);
                Modifier modifierWeight$default8 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783622384, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged27 = composerStartRestartGroup.changed(mutableState15);
                objRememberedValue22 = composerStartRestartGroup.rememberedValue();
                if (zChanged27 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m875xfd5372d7(mutableState15, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$43, (Function1<? super String, Unit>) objRememberedValue22, modifierWeight$default8, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$263307764$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String strUniversalDocumentEditorScreen$lambda$40 = UniversalDocumentEditorScreen$lambda$40(mutableState14);
                Modifier modifierFillMaxWidth$default11 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969818260, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                zChanged28 = composerStartRestartGroup.changed(mutableState14);
                objRememberedValue23 = composerStartRestartGroup.rememberedValue();
                if (zChanged28 || objRememberedValue23 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return UniversalDocumentEditorScreenKt.m876x34f973b8(mutableState14, (String) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$40, (Function1<? super String, Unit>) objRememberedValue23, modifierFillMaxWidth$default11, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$449067675$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 3, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969823039, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                boolean zChanged29 = composerStartRestartGroup.changed(mutableState3) | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changed(mutableState5) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changed(mutableState7) | composerStartRestartGroup.changed(mutableState8) | composerStartRestartGroup.changed(mutableState14) | composerStartRestartGroup.changed(mutableState9) | composerStartRestartGroup.changed(mutableState10) | composerStartRestartGroup.changed(mutableState11) | composerStartRestartGroup.changed(mutableState12) | composerStartRestartGroup.changed(mutableState13) | composerStartRestartGroup.changed(mutableState15) | composerStartRestartGroup.changed(mutableState16);
                i5 = i4 & 112;
                if (i5 == 32) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z8 = zChanged29 | z;
                if ((i4 & 57344) == 16384) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                masrofViewModel = viewModel;
                zChangedInstance = z2 | z8 | composerStartRestartGroup.changedInstance(masrofViewModel);
                objRememberedValue24 = composerStartRestartGroup.rememberedValue();
                if (!zChangedInstance || objRememberedValue24 == Composer.INSTANCE.getEmpty()) {
                    final Function1<? super Document, Unit> function7 = function3;
                    z3 = false;
                    Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda25
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.m877x5ec2d951(document, function7, masrofViewModel, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState14, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, mutableState15, mutableState16);
                        }
                    };
                    function4 = function7;
                    mutableState17 = mutableState7;
                    composer2 = composerStartRestartGroup;
                    document2 = document;
                    masrofViewModel = masrofViewModel;
                    mutableState18 = mutableState5;
                    composer2.updateRememberedValue(function0);
                    objRememberedValue24 = function0;
                } else {
                    document2 = document;
                    composer2 = composerStartRestartGroup;
                    mutableState18 = mutableState5;
                    mutableState17 = mutableState7;
                    function4 = function3;
                    z3 = false;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer3 = composer2;
                mutableState19 = mutableState18;
                Function1<? super Document, Unit> function8 = function4;
                ButtonKt.Button((Function0) objRememberedValue24, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1848624266, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$83(document2, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer2, 54), composer3, 805306416, 508);
                ComposerKt.sourceInformationMarkerStart(composer3, 1969851666, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue25 = composer3.rememberedValue();
                if (objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                    mutableState20 = mutableState2;
                    objRememberedValue25 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda27
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.m878x1d70f197(mutableState20);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue25);
                } else {
                    mutableState20 = mutableState2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                mutableState21 = mutableState20;
                ButtonKt.OutlinedButton((Function0) objRememberedValue25, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(866804856, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$86(mutableState20, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer3, 54), composer3, 805306422, 508);
                if (UniversalDocumentEditorScreen$lambda$4(mutableState21)) {
                    composer3.startReplaceGroup(1969858402);
                    ComposerKt.sourceInformation(composer3, "84@6793L79,84@6748L124");
                    CardKt.Card(SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(680.0f)), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1592510189, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda29
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$89(masrofViewModel, document2, mutableState21, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    }, composer3, 54), composer3, 196614, 30);
                } else {
                    composer3.startReplaceGroup(929369468);
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerStart(composer3, 1969863484, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue26 = composer3.rememberedValue();
                if (objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                    mutableState22 = mutableState;
                    objRememberedValue26 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.m880xd18fcc0d(mutableState22);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue26);
                } else {
                    mutableState22 = mutableState;
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                mutableState23 = mutableState22;
                ButtonKt.OutlinedButton((Function0) objRememberedValue26, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7707getLambda$2104201745$app(), composer3, 805306422, 508);
                ComposerKt.sourceInformationMarkerStart(composer3, 1969867744, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                boolean zChangedInstance2 = composer3.changedInstance(context);
                if (i5 == 32) {
                    z4 = true;
                } else {
                    z4 = z3;
                }
                z5 = zChangedInstance2 | z4;
                objRememberedValue27 = composer3.rememberedValue();
                if (z5 || objRememberedValue27 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue27 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.m881xfb593191(context, document2);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue27);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.OutlinedButton((Function0) objRememberedValue27, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$771441520$app(), composer3, 805306416, 508);
                ComposerKt.sourceInformationMarkerStart(composer3, 1969877301, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                boolean zChangedInstance3 = composer3.changedInstance(context);
                if (i5 == 32) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                z7 = zChangedInstance3 | z6;
                objRememberedValue28 = composer3.rememberedValue();
                if (z7 || objRememberedValue28 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue28 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.m882x25229715(context, document2);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue28);
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ButtonKt.OutlinedButton((Function0) objRememberedValue28, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7709getLambda$647882511$app(), composer3, 805306416, 508);
                composerStartRestartGroup = composer3;
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
                if (UniversalDocumentEditorScreen$lambda$1(mutableState23)) {
                    composerStartRestartGroup.startReplaceGroup(-189310266);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "97@7655L23,100@8023L113,101@8162L67,99@7739L258,96@7615L620");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189309583, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                    objRememberedValue29 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                        mutableState24 = mutableState23;
                        objRememberedValue29 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$99$lambda$98(mutableState24);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue29);
                    } else {
                        mutableState24 = mutableState23;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    function5 = onPreview;
                    AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0((Function0) objRememberedValue29, ComposableLambdaKt.rememberComposableLambda(1439858029, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$102(function5, document2, mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(1653475115, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$105(mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1867092201$app(), ComposableLambdaKt.rememberComposableLambda(-173582904, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$109(document2, mutableState19, mutableState17, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composerStartRestartGroup, 1772598, 0, 16276);
                    composerStartRestartGroup = composerStartRestartGroup;
                } else {
                    function5 = onPreview;
                    composerStartRestartGroup.startReplaceGroup(-1581225240);
                }
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function6 = function8;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function5 = onPreview;
                function6 = function2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$110(viewModel, document2, function5, onBack, function6, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function2 = function1;
        if ((i3 & 9363) == 9362) {
            if (i6 != 0) {
                function3 = null;
            } else {
                function3 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(276897434, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen (UniversalDocumentEditorScreen.kt:20)");
            }
            ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localContext2);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            context = (Context) objConsume2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189529604, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189524805, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id15 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189523146, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(id15);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id16 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189520518, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(id16);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue4 = mutableStateMutableStateOf$default3;
            } else {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue4 = mutableStateMutableStateOf$default4;
            }
            mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            i4 = i3;
            long id17 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189517690, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(id17);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                beneficiaryName = document2.getBeneficiaryName();
                if (beneficiaryName == null) {
                    beneficiaryName = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                beneficiaryName = document2.getBeneficiaryName();
                if (beneficiaryName == null) {
                    beneficiaryName = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id18 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189514438, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(id18);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged4) {
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue6 = mutableStateMutableStateOf$default5;
            } else {
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue6 = mutableStateMutableStateOf$default6;
            }
            mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id19 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189511746, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(id19);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged5) {
                purpose = document2.getPurpose();
                if (purpose == null) {
                    purpose = "";
                }
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                purpose = document2.getPurpose();
                if (purpose == null) {
                    purpose = "";
                }
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id20 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189508930, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(id20);
            Object objRememberedValue37 = composerStartRestartGroup.rememberedValue();
            if (zChanged6) {
                details = document2.getDetails();
                if (details == null) {
                    details = "";
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            } else {
                details = document2.getDetails();
                if (details == null) {
                    details = "";
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            }
            mutableState8 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id21 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189506135, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(id21);
            Object objRememberedValue38 = composerStartRestartGroup.rememberedValue();
            if (zChanged7) {
                amount = document.getAmount();
                if (amount != null) {
                    string = amount.toString();
                } else {
                    string = null;
                }
                if (string == null) {
                    string = "";
                }
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
            } else {
                amount = document.getAmount();
                if (amount != null) {
                    string = amount.toString();
                } else {
                    string = null;
                }
                if (string == null) {
                    string = "";
                }
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
            }
            mutableState9 = (MutableState) objMutableStateOf$default2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id22 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189502846, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(id22);
            Object objRememberedValue39 = composerStartRestartGroup.rememberedValue();
            if (zChanged8) {
                amountWords = document.getAmountWords();
                if (amountWords == null) {
                    amountWords = "";
                }
                objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
            } else {
                amountWords = document.getAmountWords();
                if (amountWords == null) {
                    amountWords = "";
                }
                objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
            }
            mutableState10 = (MutableState) objMutableStateOf$default3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id23 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189499874, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(id23);
            Object objRememberedValue310 = composerStartRestartGroup.rememberedValue();
            if (zChanged9) {
                objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFinancialCategory(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
            } else {
                objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFinancialCategory(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
            }
            mutableState11 = (MutableState) objMutableStateOf$default4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id110 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189496969, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged10 = composerStartRestartGroup.changed(id110);
            Object objRememberedValue311 = composerStartRestartGroup.rememberedValue();
            if (zChanged10) {
                objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getCostCenter(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
            } else {
                objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getCostCenter(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
            }
            mutableState12 = (MutableState) objMutableStateOf$default5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id111 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189494374, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged11 = composerStartRestartGroup.changed(id111);
            Object objRememberedValue312 = composerStartRestartGroup.rememberedValue();
            if (zChanged11) {
                objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFundingSource(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default6);
            } else {
                objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFundingSource(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default6);
            }
            mutableState13 = (MutableState) objMutableStateOf$default6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id112 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189491748, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged12 = composerStartRestartGroup.changed(id112);
            Object objRememberedValue313 = composerStartRestartGroup.rememberedValue();
            if (zChanged12) {
                String notes2 = document.getNotes();
                objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes2 != null ? notes2 : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default7);
            } else {
                String notes3 = document.getNotes();
                objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes3 != null ? notes3 : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default7);
            }
            mutableState14 = (MutableState) objMutableStateOf$default7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id113 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189489103, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged13 = composerStartRestartGroup.changed(id113);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged13) {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getTags(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            } else {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getTags(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            mutableState15 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id114 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189486552, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged14 = composerStartRestartGroup.changed(id114);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChanged14) {
                obj = null;
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document.getAttachmentsCount()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                obj = null;
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document.getAttachmentsCount()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            mutableState16 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1658padding3ABfNKs2 = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), C1786Dp.m7249constructorimpl(12.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1658padding3ABfNKs2);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting()) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 771332839, "C41@2263L236,45@2627L10,45@2508L140,46@2699L21,46@2657L4930:UniversalDocumentEditorScreen.kt#ska5t9");
            Modifier modifierFillMaxWidth$default12 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(spaceBetween2, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default12);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl2 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting()) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -629629462, "C42@2409L10,42@2356L75,43@2444L45:UniversalDocumentEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("محرر المستندات المكتبي", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
            ButtonKt.TextButton(onBack, null, false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7706getLambda$2029005263$app(), composerStartRestartGroup, ((i4 >> 9) & 14) | 805306368, 510);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            TextKt.m3342Text4IGK_g(document.getType().name() + " — رقم " + document.getDocumentNumber() + " — الحالة: " + document.getStatus().name(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 0, 0, 65534);
            Modifier modifierVerticalScroll$default2 = ScrollKt.verticalScroll$default(ColumnScope.weight$default(columnScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_9 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_9, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default2);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl3 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting()) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            } else {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 932283655, "C47@2853L10,47@2787L89,48@2889L404,52@3361L20,52@3306L169,53@3545L22,53@3488L158,54@3710L16,54@3659L168,55@3891L16,55@3840L156,56@4009L408,60@4482L17,60@4430L150,61@4593L404,65@5010L425,69@5497L14,69@5448L145,70@5623L761,82@6422L80,70@5606L896,83@6540L44,83@6622L89,83@6515L196,85@6910L22,85@6885L115,86@7038L186,86@7013L286,90@7337L175,90@7312L265:UniversalDocumentEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("تحرير جميع البيانات داخل صفحة العمل", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleMedium(), composerStartRestartGroup, 6, 0, 65534);
            Modifier modifierFillMaxWidth$default13 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_10 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_10, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default13);
            constructor4 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl4 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting()) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            } else {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1609021994, "C49@3037L14,49@2988L132,50@3190L18,50@3137L142:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$8 = UniversalDocumentEditorScreen$lambda$7(mutableState3);
            Modifier modifierWeight$default9 = RowScope.weight$default(rowScopeInstance7, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363739352, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged15 = composerStartRestartGroup.changed(mutableState3);
            objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChanged15) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m863x8fa703b5(mutableState3, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            } else {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m863x8fa703b5(mutableState3, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$8, (Function1<? super String, Unit>) objRememberedValue10, modifierWeight$default9, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7704getLambda$1768396028$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$11 = UniversalDocumentEditorScreen$lambda$10(mutableState4);
            Modifier modifierWeight$default10 = RowScope.weight$default(rowScopeInstance7, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363744252, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged16 = composerStartRestartGroup.changed(mutableState4);
            objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (zChanged16) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m864xb970694e(mutableState4, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            } else {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m864xb970694e(mutableState4, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$11, (Function1<? super String, Unit>) objRememberedValue11, modifierWeight$default10, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1086747195$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$14 = UniversalDocumentEditorScreen$lambda$13(mutableState5);
            Modifier modifierFillMaxWidth$default14 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969749914, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged17 = composerStartRestartGroup.changed(mutableState5);
            objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (zChanged17) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m865x65c011d(mutableState5, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            } else {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m865x65c011d(mutableState5, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$14, (Function1<? super String, Unit>) objRememberedValue12, modifierFillMaxWidth$default14, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7703getLambda$1698929376$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$17 = UniversalDocumentEditorScreen$lambda$16(mutableState6);
            Modifier modifierFillMaxWidth$default15 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969755804, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged18 = composerStartRestartGroup.changed(mutableState6);
            objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (zChanged18) {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m866x302566a1(mutableState6, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
            } else {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m866x302566a1(mutableState6, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$17, (Function1<? super String, Unit>) objRememberedValue13, modifierFillMaxWidth$default15, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1831396503$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$110 = UniversalDocumentEditorScreen$lambda$19(mutableState7);
            Modifier modifierFillMaxWidth$default16 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969761078, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged19 = composerStartRestartGroup.changed(mutableState7);
            objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (zChanged19) {
                objRememberedValue14 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m867x59eecc25(mutableState7, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            } else {
                objRememberedValue14 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m867x59eecc25(mutableState7, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$110, (Function1<? super String, Unit>) objRememberedValue14, modifierFillMaxWidth$default16, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$412072472$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 2, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            String strUniversalDocumentEditorScreen$lambda$23 = UniversalDocumentEditorScreen$lambda$22(mutableState8);
            Modifier modifierFillMaxWidth$default17 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969766870, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged20 = composerStartRestartGroup.changed(mutableState8);
            objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            if (zChanged20) {
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m868xba7adb93(mutableState8, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            } else {
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m868xba7adb93(mutableState8, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$23, (Function1<? super String, Unit>) objRememberedValue15, modifierFillMaxWidth$default17, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7700getLambda$1007251559$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            Modifier modifierFillMaxWidth$default18 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_11 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy8 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_11, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default18);
            constructor5 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl5 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting()) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            } else {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1199580677, "C57@4158L15,57@4108L135,58@4315L20,58@4260L143:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$26 = UniversalDocumentEditorScreen$lambda$25(mutableState9);
            Modifier modifierWeight$default11 = RowScope.weight$default(rowScopeInstance8, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147073488, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged21 = composerStartRestartGroup.changed(mutableState9);
            objRememberedValue16 = composerStartRestartGroup.rememberedValue();
            if (zChanged21) {
                objRememberedValue16 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m869x2575cb0(mutableState9, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            } else {
                objRememberedValue16 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m869x2575cb0(mutableState9, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$26, (Function1<? super String, Unit>) objRememberedValue16, modifierWeight$default11, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7708getLambda$501546629$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$29 = UniversalDocumentEditorScreen$lambda$28(mutableState10);
            Modifier modifierWeight$default12 = RowScope.weight$default(rowScopeInstance8, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147068459, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged22 = composerStartRestartGroup.changed(mutableState10);
            objRememberedValue17 = composerStartRestartGroup.rememberedValue();
            if (zChanged22) {
                objRememberedValue17 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m870x2c20c234(mutableState10, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
            } else {
                objRememberedValue17 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m870x2c20c234(mutableState10, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$29, (Function1<? super String, Unit>) objRememberedValue17, modifierWeight$default12, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7702getLambda$1193011470$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$32 = UniversalDocumentEditorScreen$lambda$31(mutableState11);
            Modifier modifierFillMaxWidth$default19 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969785783, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged23 = composerStartRestartGroup.changed(mutableState11);
            objRememberedValue18 = composerStartRestartGroup.rememberedValue();
            if (zChanged23) {
                objRememberedValue18 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m871xccbbbee1(mutableState11, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
            } else {
                objRememberedValue18 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m871xccbbbee1(mutableState11, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$32, (Function1<? super String, Unit>) objRememberedValue18, modifierFillMaxWidth$default19, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1868391706$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            Modifier modifierFillMaxWidth$default20 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_12 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy9 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_12, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default20);
            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl6 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl6.getInserting()) {
                composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            } else {
                composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance9 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -328759202, "C62@4746L19,62@4692L140,63@4900L16,63@4849L134:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$35 = UniversalDocumentEditorScreen$lambda$34(mutableState12);
            Modifier modifierWeight$default13 = RowScope.weight$default(rowScopeInstance9, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682132981, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged24 = composerStartRestartGroup.changed(mutableState12);
            objRememberedValue19 = composerStartRestartGroup.rememberedValue();
            if (zChanged24) {
                objRememberedValue19 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m872xac15c3ef(mutableState12, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
            } else {
                objRememberedValue19 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m872xac15c3ef(mutableState12, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$35, (Function1<? super String, Unit>) objRememberedValue19, modifierWeight$default13, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7705getLambda$1920870660$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$38 = UniversalDocumentEditorScreen$lambda$37(mutableState13);
            Modifier modifierWeight$default14 = RowScope.weight$default(rowScopeInstance9, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682137906, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged25 = composerStartRestartGroup.changed(mutableState13);
            objRememberedValue20 = composerStartRestartGroup.rememberedValue();
            if (zChanged25) {
                objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m873xd5df2988(mutableState13, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
            } else {
                objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m873xd5df2988(mutableState13, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$38, (Function1<? super String, Unit>) objRememberedValue20, modifierWeight$default14, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1682631795$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFillMaxWidth$default110 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_13 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy10 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_13, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default110);
            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl7 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl7, measurePolicyRowMeasurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl7, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl7.getInserting()) {
                composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            } else {
                composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            }
            Updater.m4308setimpl(composerM4301constructorimpl7, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance10 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 542063048, "C66@5164L42,66@5109L164,67@5338L13,67@5290L131:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$47 = UniversalDocumentEditorScreen$lambda$46(mutableState16);
            Modifier modifierWeight$default15 = RowScope.weight$default(rowScopeInstance10, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783627923, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged26 = composerStartRestartGroup.changed(mutableState16);
            objRememberedValue21 = composerStartRestartGroup.rememberedValue();
            if (zChanged26) {
                objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m874xd38a0d53(mutableState16, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
            } else {
                objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m874xd38a0d53(mutableState16, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$47, (Function1<? super String, Unit>) objRememberedValue21, modifierWeight$default15, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$954772605$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$44 = UniversalDocumentEditorScreen$lambda$43(mutableState15);
            Modifier modifierWeight$default16 = RowScope.weight$default(rowScopeInstance10, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783622384, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged27 = composerStartRestartGroup.changed(mutableState15);
            objRememberedValue22 = composerStartRestartGroup.rememberedValue();
            if (zChanged27) {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m875xfd5372d7(mutableState15, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
            } else {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m875xfd5372d7(mutableState15, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$44, (Function1<? super String, Unit>) objRememberedValue22, modifierWeight$default16, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$263307764$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$41 = UniversalDocumentEditorScreen$lambda$40(mutableState14);
            Modifier modifierFillMaxWidth$default111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969818260, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged28 = composerStartRestartGroup.changed(mutableState14);
            objRememberedValue23 = composerStartRestartGroup.rememberedValue();
            if (zChanged28) {
                objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m876x34f973b8(mutableState14, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
            } else {
                objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m876x34f973b8(mutableState14, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$41, (Function1<? super String, Unit>) objRememberedValue23, modifierFillMaxWidth$default111, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$449067675$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 3, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969823039, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChanged210 = composerStartRestartGroup.changed(mutableState3) | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changed(mutableState5) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changed(mutableState7) | composerStartRestartGroup.changed(mutableState8) | composerStartRestartGroup.changed(mutableState14) | composerStartRestartGroup.changed(mutableState9) | composerStartRestartGroup.changed(mutableState10) | composerStartRestartGroup.changed(mutableState11) | composerStartRestartGroup.changed(mutableState12) | composerStartRestartGroup.changed(mutableState13) | composerStartRestartGroup.changed(mutableState15) | composerStartRestartGroup.changed(mutableState16);
            i5 = i4 & 112;
            if (i5 == 32) {
                z = true;
            } else {
                z = false;
            }
            boolean z9 = zChanged210 | z;
            if ((i4 & 57344) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            masrofViewModel = viewModel;
            zChangedInstance = z2 | z9 | composerStartRestartGroup.changedInstance(masrofViewModel);
            objRememberedValue24 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                final Function1 function9 = function3;
                z3 = false;
                Function0 function10 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m877x5ec2d951(document, function9, masrofViewModel, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState14, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, mutableState15, mutableState16);
                    }
                };
                function4 = function9;
                mutableState17 = mutableState7;
                composer2 = composerStartRestartGroup;
                document2 = document;
                masrofViewModel = masrofViewModel;
                mutableState18 = mutableState5;
                composer2.updateRememberedValue(function10);
                objRememberedValue24 = function10;
            } else {
                final Function1 function11 = function3;
                z3 = false;
                Function0 function12 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m877x5ec2d951(document, function11, masrofViewModel, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState14, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, mutableState15, mutableState16);
                    }
                };
                function4 = function11;
                mutableState17 = mutableState7;
                composer2 = composerStartRestartGroup;
                document2 = document;
                masrofViewModel = masrofViewModel;
                mutableState18 = mutableState5;
                composer2.updateRememberedValue(function12);
                objRememberedValue24 = function12;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer3 = composer2;
            mutableState19 = mutableState18;
            Function1<? super Document, Unit> function13 = function4;
            ButtonKt.Button((Function0) objRememberedValue24, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1848624266, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$83(document2, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer2, 54), composer3, 805306416, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969851666, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue25 = composer3.rememberedValue();
            if (objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                mutableState20 = mutableState2;
                objRememberedValue25 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m878x1d70f197(mutableState20);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue25);
            } else {
                mutableState20 = mutableState2;
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            mutableState21 = mutableState20;
            ButtonKt.OutlinedButton((Function0) objRememberedValue25, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(866804856, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$86(mutableState20, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer3, 54), composer3, 805306422, 508);
            if (UniversalDocumentEditorScreen$lambda$4(mutableState21)) {
                composer3.startReplaceGroup(1969858402);
                ComposerKt.sourceInformation(composer3, "84@6793L79,84@6748L124");
                CardKt.Card(SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(680.0f)), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1592510189, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$89(masrofViewModel, document2, mutableState21, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer3, 54), composer3, 196614, 30);
            } else {
                composer3.startReplaceGroup(929369468);
            }
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart(composer3, 1969863484, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue26 = composer3.rememberedValue();
            if (objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                mutableState22 = mutableState;
                objRememberedValue26 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m880xd18fcc0d(mutableState22);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue26);
            } else {
                mutableState22 = mutableState;
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            mutableState23 = mutableState22;
            ButtonKt.OutlinedButton((Function0) objRememberedValue26, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7707getLambda$2104201745$app(), composer3, 805306422, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969867744, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChangedInstance4 = composer3.changedInstance(context);
            if (i5 == 32) {
                z4 = true;
            } else {
                z4 = z3;
            }
            z5 = zChangedInstance4 | z4;
            objRememberedValue27 = composer3.rememberedValue();
            if (z5) {
                objRememberedValue27 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m881xfb593191(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue27);
            } else {
                objRememberedValue27 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m881xfb593191(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue27);
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ButtonKt.OutlinedButton((Function0) objRememberedValue27, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$771441520$app(), composer3, 805306416, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969877301, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChangedInstance5 = composer3.changedInstance(context);
            if (i5 == 32) {
                z6 = true;
            } else {
                z6 = z3;
            }
            z7 = zChangedInstance5 | z6;
            objRememberedValue28 = composer3.rememberedValue();
            if (z7) {
                objRememberedValue28 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m882x25229715(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue28);
            } else {
                objRememberedValue28 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m882x25229715(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue28);
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ButtonKt.OutlinedButton((Function0) objRememberedValue28, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7709getLambda$647882511$app(), composer3, 805306416, 508);
            composerStartRestartGroup = composer3;
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
            if (UniversalDocumentEditorScreen$lambda$1(mutableState23)) {
                composerStartRestartGroup.startReplaceGroup(-189310266);
                ComposerKt.sourceInformation(composerStartRestartGroup, "97@7655L23,100@8023L113,101@8162L67,99@7739L258,96@7615L620");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189309583, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue29 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                    mutableState24 = mutableState23;
                    objRememberedValue29 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$99$lambda$98(mutableState24);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue29);
                } else {
                    mutableState24 = mutableState23;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function5 = onPreview;
                AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0((Function0) objRememberedValue29, ComposableLambdaKt.rememberComposableLambda(1439858029, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$102(function5, document2, mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(1653475115, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$105(mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1867092201$app(), ComposableLambdaKt.rememberComposableLambda(-173582904, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$109(document2, mutableState19, mutableState17, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composerStartRestartGroup, 1772598, 0, 16276);
                composerStartRestartGroup = composerStartRestartGroup;
            } else {
                function5 = onPreview;
                composerStartRestartGroup.startReplaceGroup(-1581225240);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function13;
        } else {
            if (i6 != 0) {
                function3 = null;
            } else {
                function3 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(276897434, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen (UniversalDocumentEditorScreen.kt:20)");
            }
            ProvidableCompositionLocal<Context> localContext3 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localContext3);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            context = (Context) objConsume3;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189529604, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189524805, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id115 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189523146, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(id115);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateHijri(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id116 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189520518, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(id116);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue4 = mutableStateMutableStateOf$default7;
            } else {
                MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getDateGregorian(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                objRememberedValue4 = mutableStateMutableStateOf$default8;
            }
            mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            i4 = i3;
            long id117 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189517690, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(id117);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                beneficiaryName = document2.getBeneficiaryName();
                if (beneficiaryName == null) {
                    beneficiaryName = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                beneficiaryName = document2.getBeneficiaryName();
                if (beneficiaryName == null) {
                    beneficiaryName = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id118 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189514438, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(id118);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChanged4) {
                MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                objRememberedValue6 = mutableStateMutableStateOf$default9;
            } else {
                MutableState mutableStateMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document2.getBeneficiaryId(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default10);
                objRememberedValue6 = mutableStateMutableStateOf$default10;
            }
            mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id119 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189511746, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(id119);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged5) {
                purpose = document2.getPurpose();
                if (purpose == null) {
                    purpose = "";
                }
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                purpose = document2.getPurpose();
                if (purpose == null) {
                    purpose = "";
                }
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id24 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189508930, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(id24);
            Object objRememberedValue314 = composerStartRestartGroup.rememberedValue();
            if (zChanged6) {
                details = document2.getDetails();
                if (details == null) {
                    details = "";
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            } else {
                details = document2.getDetails();
                if (details == null) {
                    details = "";
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            }
            mutableState8 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id25 = document2.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189506135, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(id25);
            Object objRememberedValue315 = composerStartRestartGroup.rememberedValue();
            if (zChanged7) {
                amount = document.getAmount();
                if (amount != null) {
                    string = amount.toString();
                } else {
                    string = null;
                }
                if (string == null) {
                    string = "";
                }
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
            } else {
                amount = document.getAmount();
                if (amount != null) {
                    string = amount.toString();
                } else {
                    string = null;
                }
                if (string == null) {
                    string = "";
                }
                objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default2);
            }
            mutableState9 = (MutableState) objMutableStateOf$default2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id26 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189502846, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(id26);
            Object objRememberedValue316 = composerStartRestartGroup.rememberedValue();
            if (zChanged8) {
                amountWords = document.getAmountWords();
                if (amountWords == null) {
                    amountWords = "";
                }
                objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
            } else {
                amountWords = document.getAmountWords();
                if (amountWords == null) {
                    amountWords = "";
                }
                objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(amountWords, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default3);
            }
            mutableState10 = (MutableState) objMutableStateOf$default3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id27 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189499874, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(id27);
            Object objRememberedValue317 = composerStartRestartGroup.rememberedValue();
            if (zChanged9) {
                objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFinancialCategory(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
            } else {
                objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFinancialCategory(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default4);
            }
            mutableState11 = (MutableState) objMutableStateOf$default4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id1110 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189496969, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged10 = composerStartRestartGroup.changed(id1110);
            Object objRememberedValue318 = composerStartRestartGroup.rememberedValue();
            if (zChanged10) {
                objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getCostCenter(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
            } else {
                objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getCostCenter(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default5);
            }
            mutableState12 = (MutableState) objMutableStateOf$default5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id1111 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189494374, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged11 = composerStartRestartGroup.changed(id1111);
            Object objRememberedValue319 = composerStartRestartGroup.rememberedValue();
            if (zChanged11) {
                objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFundingSource(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default6);
            } else {
                objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getFundingSource(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default6);
            }
            mutableState13 = (MutableState) objMutableStateOf$default6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id1112 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189491748, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged12 = composerStartRestartGroup.changed(id1112);
            Object objRememberedValue3110 = composerStartRestartGroup.rememberedValue();
            if (zChanged12) {
                String notes4 = document.getNotes();
                objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes4 != null ? notes4 : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default7);
            } else {
                String notes5 = document.getNotes();
                objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes5 != null ? notes5 : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default7);
            }
            mutableState14 = (MutableState) objMutableStateOf$default7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id1113 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189489103, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged13 = composerStartRestartGroup.changed(id1113);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChanged13) {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getTags(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            } else {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(document.getTags(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            mutableState15 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            long id1114 = document.getId();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189486552, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged14 = composerStartRestartGroup.changed(id1114);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (zChanged14) {
                obj = null;
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document.getAttachmentsCount()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                obj = null;
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(document.getAttachmentsCount()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            mutableState16 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1658padding3ABfNKs3 = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), C1786Dp.m7249constructorimpl(12.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1658padding3ABfNKs3);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting()) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 771332839, "C41@2263L236,45@2627L10,45@2508L140,46@2699L21,46@2657L4930:UniversalDocumentEditorScreen.kt#ska5t9");
            Modifier modifierFillMaxWidth$default112 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween3 = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy11 = RowKt.rowMeasurePolicy(spaceBetween3, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default112);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl2 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting()) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance11 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -629629462, "C42@2409L10,42@2356L75,43@2444L45:UniversalDocumentEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("محرر المستندات المكتبي", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
            ButtonKt.TextButton(onBack, null, false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7706getLambda$2029005263$app(), composerStartRestartGroup, ((i4 >> 9) & 14) | 805306368, 510);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            TextKt.m3342Text4IGK_g(document.getType().name() + " — رقم " + document.getDocumentNumber() + " — الحالة: " + document.getStatus().name(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 0, 0, 65534);
            Modifier modifierVerticalScroll$default3 = ScrollKt.verticalScroll$default(ColumnScope.weight$default(columnScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_14 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_14, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default3);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl3 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyColumnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting()) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            } else {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 932283655, "C47@2853L10,47@2787L89,48@2889L404,52@3361L20,52@3306L169,53@3545L22,53@3488L158,54@3710L16,54@3659L168,55@3891L16,55@3840L156,56@4009L408,60@4482L17,60@4430L150,61@4593L404,65@5010L425,69@5497L14,69@5448L145,70@5623L761,82@6422L80,70@5606L896,83@6540L44,83@6622L89,83@6515L196,85@6910L22,85@6885L115,86@7038L186,86@7013L286,90@7337L175,90@7312L265:UniversalDocumentEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("تحرير جميع البيانات داخل صفحة العمل", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleMedium(), composerStartRestartGroup, 6, 0, 65534);
            Modifier modifierFillMaxWidth$default113 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_15 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy12 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_15, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default113);
            constructor4 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl4 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting()) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            } else {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance12 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1609021994, "C49@3037L14,49@2988L132,50@3190L18,50@3137L142:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$9 = UniversalDocumentEditorScreen$lambda$7(mutableState3);
            Modifier modifierWeight$default17 = RowScope.weight$default(rowScopeInstance12, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363739352, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged15 = composerStartRestartGroup.changed(mutableState3);
            objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (zChanged15) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m863x8fa703b5(mutableState3, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            } else {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m863x8fa703b5(mutableState3, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$9, (Function1<? super String, Unit>) objRememberedValue10, modifierWeight$default17, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7704getLambda$1768396028$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$12 = UniversalDocumentEditorScreen$lambda$10(mutableState4);
            Modifier modifierWeight$default18 = RowScope.weight$default(rowScopeInstance12, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 363744252, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged16 = composerStartRestartGroup.changed(mutableState4);
            objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (zChanged16) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m864xb970694e(mutableState4, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            } else {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m864xb970694e(mutableState4, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$12, (Function1<? super String, Unit>) objRememberedValue11, modifierWeight$default18, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1086747195$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$15 = UniversalDocumentEditorScreen$lambda$13(mutableState5);
            Modifier modifierFillMaxWidth$default114 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969749914, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged17 = composerStartRestartGroup.changed(mutableState5);
            objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (zChanged17) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m865x65c011d(mutableState5, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            } else {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m865x65c011d(mutableState5, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$15, (Function1<? super String, Unit>) objRememberedValue12, modifierFillMaxWidth$default114, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7703getLambda$1698929376$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$18 = UniversalDocumentEditorScreen$lambda$16(mutableState6);
            Modifier modifierFillMaxWidth$default115 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969755804, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged18 = composerStartRestartGroup.changed(mutableState6);
            objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (zChanged18) {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m866x302566a1(mutableState6, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
            } else {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m866x302566a1(mutableState6, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$18, (Function1<? super String, Unit>) objRememberedValue13, modifierFillMaxWidth$default115, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1831396503$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$111 = UniversalDocumentEditorScreen$lambda$19(mutableState7);
            Modifier modifierFillMaxWidth$default116 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969761078, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged19 = composerStartRestartGroup.changed(mutableState7);
            objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (zChanged19) {
                objRememberedValue14 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m867x59eecc25(mutableState7, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            } else {
                objRememberedValue14 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m867x59eecc25(mutableState7, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$111, (Function1<? super String, Unit>) objRememberedValue14, modifierFillMaxWidth$default116, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$412072472$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 2, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            String strUniversalDocumentEditorScreen$lambda$24 = UniversalDocumentEditorScreen$lambda$22(mutableState8);
            Modifier modifierFillMaxWidth$default117 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969766870, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged20 = composerStartRestartGroup.changed(mutableState8);
            objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            if (zChanged20) {
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m868xba7adb93(mutableState8, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            } else {
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m868xba7adb93(mutableState8, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$24, (Function1<? super String, Unit>) objRememberedValue15, modifierFillMaxWidth$default117, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7700getLambda$1007251559$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 4, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            Modifier modifierFillMaxWidth$default118 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_16 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy13 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_16, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default118);
            constructor5 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl5 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting()) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            } else {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance13 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1199580677, "C57@4158L15,57@4108L135,58@4315L20,58@4260L143:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$27 = UniversalDocumentEditorScreen$lambda$25(mutableState9);
            Modifier modifierWeight$default19 = RowScope.weight$default(rowScopeInstance13, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147073488, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged21 = composerStartRestartGroup.changed(mutableState9);
            objRememberedValue16 = composerStartRestartGroup.rememberedValue();
            if (zChanged21) {
                objRememberedValue16 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m869x2575cb0(mutableState9, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            } else {
                objRememberedValue16 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m869x2575cb0(mutableState9, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$27, (Function1<? super String, Unit>) objRememberedValue16, modifierWeight$default19, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7708getLambda$501546629$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$210 = UniversalDocumentEditorScreen$lambda$28(mutableState10);
            Modifier modifierWeight$default110 = RowScope.weight$default(rowScopeInstance13, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1147068459, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged22 = composerStartRestartGroup.changed(mutableState10);
            objRememberedValue17 = composerStartRestartGroup.rememberedValue();
            if (zChanged22) {
                objRememberedValue17 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m870x2c20c234(mutableState10, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
            } else {
                objRememberedValue17 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m870x2c20c234(mutableState10, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$210, (Function1<? super String, Unit>) objRememberedValue17, modifierWeight$default110, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7702getLambda$1193011470$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$33 = UniversalDocumentEditorScreen$lambda$31(mutableState11);
            Modifier modifierFillMaxWidth$default119 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969785783, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged23 = composerStartRestartGroup.changed(mutableState11);
            objRememberedValue18 = composerStartRestartGroup.rememberedValue();
            if (zChanged23) {
                objRememberedValue18 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m871xccbbbee1(mutableState11, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
            } else {
                objRememberedValue18 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m871xccbbbee1(mutableState11, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$33, (Function1<? super String, Unit>) objRememberedValue18, modifierFillMaxWidth$default119, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1868391706$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 0, 0, 8388536);
            Modifier modifierFillMaxWidth$default21 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_17 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy14 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_17, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default21);
            constructor6 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl6 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl6.getInserting()) {
                composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            } else {
                composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier110, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance14 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -328759202, "C62@4746L19,62@4692L140,63@4900L16,63@4849L134:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$36 = UniversalDocumentEditorScreen$lambda$34(mutableState12);
            Modifier modifierWeight$default111 = RowScope.weight$default(rowScopeInstance14, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682132981, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged24 = composerStartRestartGroup.changed(mutableState12);
            objRememberedValue19 = composerStartRestartGroup.rememberedValue();
            if (zChanged24) {
                objRememberedValue19 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m872xac15c3ef(mutableState12, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
            } else {
                objRememberedValue19 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m872xac15c3ef(mutableState12, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$36, (Function1<? super String, Unit>) objRememberedValue19, modifierWeight$default111, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7705getLambda$1920870660$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$39 = UniversalDocumentEditorScreen$lambda$37(mutableState13);
            Modifier modifierWeight$default112 = RowScope.weight$default(rowScopeInstance14, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 682137906, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged25 = composerStartRestartGroup.changed(mutableState13);
            objRememberedValue20 = composerStartRestartGroup.rememberedValue();
            if (zChanged25) {
                objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m873xd5df2988(mutableState13, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
            } else {
                objRememberedValue20 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m873xd5df2988(mutableState13, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$39, (Function1<? super String, Unit>) objRememberedValue20, modifierWeight$default112, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1682631795$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFillMaxWidth$default1110 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_18 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy15 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_18, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default1110);
            constructor7 = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM4301constructorimpl7 = Updater.m4301constructorimpl(composerStartRestartGroup);
            Updater.m4308setimpl(composerM4301constructorimpl7, measurePolicyRowMeasurePolicy15, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl7, currentCompositionLocalMap111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl7.getInserting()) {
                composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            } else {
                composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            }
            Updater.m4308setimpl(composerM4301constructorimpl7, modifierMaterializeModifier111, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance15 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 542063048, "C66@5164L42,66@5109L164,67@5338L13,67@5290L131:UniversalDocumentEditorScreen.kt#ska5t9");
            String strUniversalDocumentEditorScreen$lambda$48 = UniversalDocumentEditorScreen$lambda$46(mutableState16);
            Modifier modifierWeight$default113 = RowScope.weight$default(rowScopeInstance15, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783627923, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged26 = composerStartRestartGroup.changed(mutableState16);
            objRememberedValue21 = composerStartRestartGroup.rememberedValue();
            if (zChanged26) {
                objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m874xd38a0d53(mutableState16, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
            } else {
                objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m874xd38a0d53(mutableState16, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$48, (Function1<? super String, Unit>) objRememberedValue21, modifierWeight$default113, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$954772605$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            String strUniversalDocumentEditorScreen$lambda$45 = UniversalDocumentEditorScreen$lambda$43(mutableState15);
            Modifier modifierWeight$default114 = RowScope.weight$default(rowScopeInstance15, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1783622384, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged27 = composerStartRestartGroup.changed(mutableState15);
            objRememberedValue22 = composerStartRestartGroup.rememberedValue();
            if (zChanged27) {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m875xfd5372d7(mutableState15, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
            } else {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m875xfd5372d7(mutableState15, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$45, (Function1<? super String, Unit>) objRememberedValue22, modifierWeight$default114, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$263307764$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strUniversalDocumentEditorScreen$lambda$42 = UniversalDocumentEditorScreen$lambda$40(mutableState14);
            Modifier modifierFillMaxWidth$default1111 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969818260, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            zChanged28 = composerStartRestartGroup.changed(mutableState14);
            objRememberedValue23 = composerStartRestartGroup.rememberedValue();
            if (zChanged28) {
                objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m876x34f973b8(mutableState14, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
            } else {
                objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return UniversalDocumentEditorScreenKt.m876x34f973b8(mutableState14, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strUniversalDocumentEditorScreen$lambda$42, (Function1<? super String, Unit>) objRememberedValue23, modifierFillMaxWidth$default1111, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$449067675$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 3, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573248, 805306368, 0, 7864248);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1969823039, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChanged211 = composerStartRestartGroup.changed(mutableState3) | composerStartRestartGroup.changed(mutableState4) | composerStartRestartGroup.changed(mutableState5) | composerStartRestartGroup.changed(mutableState6) | composerStartRestartGroup.changed(mutableState7) | composerStartRestartGroup.changed(mutableState8) | composerStartRestartGroup.changed(mutableState14) | composerStartRestartGroup.changed(mutableState9) | composerStartRestartGroup.changed(mutableState10) | composerStartRestartGroup.changed(mutableState11) | composerStartRestartGroup.changed(mutableState12) | composerStartRestartGroup.changed(mutableState13) | composerStartRestartGroup.changed(mutableState15) | composerStartRestartGroup.changed(mutableState16);
            i5 = i4 & 112;
            if (i5 == 32) {
                z = true;
            } else {
                z = false;
            }
            boolean z10 = zChanged211 | z;
            if ((i4 & 57344) == 16384) {
                z2 = true;
            } else {
                z2 = false;
            }
            masrofViewModel = viewModel;
            zChangedInstance = z2 | z10 | composerStartRestartGroup.changedInstance(masrofViewModel);
            objRememberedValue24 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                final Function1 function14 = function3;
                z3 = false;
                Function0 function15 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m877x5ec2d951(document, function14, masrofViewModel, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState14, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, mutableState15, mutableState16);
                    }
                };
                function4 = function14;
                mutableState17 = mutableState7;
                composer2 = composerStartRestartGroup;
                document2 = document;
                masrofViewModel = masrofViewModel;
                mutableState18 = mutableState5;
                composer2.updateRememberedValue(function15);
                objRememberedValue24 = function15;
            } else {
                final Function1 function16 = function3;
                z3 = false;
                Function0 function17 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m877x5ec2d951(document, function16, masrofViewModel, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState14, mutableState9, mutableState10, mutableState11, mutableState12, mutableState13, mutableState15, mutableState16);
                    }
                };
                function4 = function16;
                mutableState17 = mutableState7;
                composer2 = composerStartRestartGroup;
                document2 = document;
                masrofViewModel = masrofViewModel;
                mutableState18 = mutableState5;
                composer2.updateRememberedValue(function17);
                objRememberedValue24 = function17;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer3 = composer2;
            mutableState19 = mutableState18;
            Function1<? super Document, Unit> function18 = function4;
            ButtonKt.Button((Function0) objRememberedValue24, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1848624266, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$83(document2, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer2, 54), composer3, 805306416, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969851666, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue25 = composer3.rememberedValue();
            if (objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                mutableState20 = mutableState2;
                objRememberedValue25 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m878x1d70f197(mutableState20);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue25);
            } else {
                mutableState20 = mutableState2;
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            mutableState21 = mutableState20;
            ButtonKt.OutlinedButton((Function0) objRememberedValue25, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(866804856, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$86(mutableState20, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer3, 54), composer3, 805306422, 508);
            if (UniversalDocumentEditorScreen$lambda$4(mutableState21)) {
                composer3.startReplaceGroup(1969858402);
                ComposerKt.sourceInformation(composer3, "84@6793L79,84@6748L124");
                CardKt.Card(SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(680.0f)), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1592510189, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$89(masrofViewModel, document2, mutableState21, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composer3, 54), composer3, 196614, 30);
            } else {
                composer3.startReplaceGroup(929369468);
            }
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart(composer3, 1969863484, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            objRememberedValue26 = composer3.rememberedValue();
            if (objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                mutableState22 = mutableState;
                objRememberedValue26 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m880xd18fcc0d(mutableState22);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue26);
            } else {
                mutableState22 = mutableState;
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            mutableState23 = mutableState22;
            ButtonKt.OutlinedButton((Function0) objRememberedValue26, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7707getLambda$2104201745$app(), composer3, 805306422, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969867744, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChangedInstance6 = composer3.changedInstance(context);
            if (i5 == 32) {
                z4 = true;
            } else {
                z4 = z3;
            }
            z5 = zChangedInstance6 | z4;
            objRememberedValue27 = composer3.rememberedValue();
            if (z5) {
                objRememberedValue27 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m881xfb593191(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue27);
            } else {
                objRememberedValue27 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m881xfb593191(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue27);
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ButtonKt.OutlinedButton((Function0) objRememberedValue27, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$771441520$app(), composer3, 805306416, 508);
            ComposerKt.sourceInformationMarkerStart(composer3, 1969877301, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChangedInstance7 = composer3.changedInstance(context);
            if (i5 == 32) {
                z6 = true;
            } else {
                z6 = z3;
            }
            z7 = zChangedInstance7 | z6;
            objRememberedValue28 = composer3.rememberedValue();
            if (z7) {
                objRememberedValue28 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m882x25229715(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue28);
            } else {
                objRememberedValue28 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m882x25229715(context, document2);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue28);
            }
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ButtonKt.OutlinedButton((Function0) objRememberedValue28, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7709getLambda$647882511$app(), composer3, 805306416, 508);
            composerStartRestartGroup = composer3;
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
            if (UniversalDocumentEditorScreen$lambda$1(mutableState23)) {
                composerStartRestartGroup.startReplaceGroup(-189310266);
                ComposerKt.sourceInformation(composerStartRestartGroup, "97@7655L23,100@8023L113,101@8162L67,99@7739L258,96@7615L620");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -189309583, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
                objRememberedValue29 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                    mutableState24 = mutableState23;
                    objRememberedValue29 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$99$lambda$98(mutableState24);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue29);
                } else {
                    mutableState24 = mutableState23;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function5 = onPreview;
                AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0((Function0) objRememberedValue29, ComposableLambdaKt.rememberComposableLambda(1439858029, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$102(function5, document2, mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(1653475115, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$105(mutableState24, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1867092201$app(), ComposableLambdaKt.rememberComposableLambda(-173582904, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$109(document2, mutableState19, mutableState17, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composerStartRestartGroup, 1772598, 0, 16276);
                composerStartRestartGroup = composerStartRestartGroup;
            } else {
                function5 = onPreview;
                composerStartRestartGroup.startReplaceGroup(-1581225240);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function18;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$110(viewModel, document2, function5, onBack, function6, i, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final boolean UniversalDocumentEditorScreen$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void UniversalDocumentEditorScreen$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean UniversalDocumentEditorScreen$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void UniversalDocumentEditorScreen$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String UniversalDocumentEditorScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$28(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$34(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$37(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$40(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$43(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UniversalDocumentEditorScreen$lambda$46(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$53$lambda$50$lambda$49 */
    static final Unit m863x8fa703b5(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$53$lambda$52$lambda$51 */
    static final Unit m864xb970694e(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$55$lambda$54 */
    static final Unit m865x65c011d(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$57$lambda$56 */
    static final Unit m866x302566a1(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$59$lambda$58 */
    static final Unit m867x59eecc25(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$61$lambda$60 */
    static final Unit m868xba7adb93(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$66$lambda$63$lambda$62 */
    static final Unit m869x2575cb0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$66$lambda$65$lambda$64 */
    static final Unit m870x2c20c234(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$68$lambda$67 */
    static final Unit m871xccbbbee1(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$73$lambda$70$lambda$69 */
    static final Unit m872xac15c3ef(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$73$lambda$72$lambda$71 */
    static final Unit m873xd5df2988(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$78$lambda$77$lambda$76 */
    static final Unit m875xfd5372d7(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$80$lambda$79 */
    static final Unit m876x34f973b8(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$82$lambda$81 */
    static final Unit m877x5ec2d951(Document document, Function1 function1, MasrofViewModel masrofViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, MutableState mutableState10, MutableState mutableState11, MutableState mutableState12, MutableState mutableState13, MutableState mutableState14) {
        String strUniversalDocumentEditorScreen$lambda$7 = UniversalDocumentEditorScreen$lambda$7(mutableState);
        String strUniversalDocumentEditorScreen$lambda$10 = UniversalDocumentEditorScreen$lambda$10(mutableState2);
        String strUniversalDocumentEditorScreen$lambda$13 = UniversalDocumentEditorScreen$lambda$13(mutableState3);
        String strUniversalDocumentEditorScreen$lambda$16 = UniversalDocumentEditorScreen$lambda$16(mutableState4);
        String strUniversalDocumentEditorScreen$lambda$19 = UniversalDocumentEditorScreen$lambda$19(mutableState5);
        String strUniversalDocumentEditorScreen$lambda$22 = UniversalDocumentEditorScreen$lambda$22(mutableState6);
        String strUniversalDocumentEditorScreen$lambda$40 = UniversalDocumentEditorScreen$lambda$40(mutableState7);
        Double doubleOrNull = StringsKt.toDoubleOrNull(UniversalDocumentEditorScreen$lambda$25(mutableState8));
        String strUniversalDocumentEditorScreen$lambda$28 = UniversalDocumentEditorScreen$lambda$28(mutableState9);
        String strUniversalDocumentEditorScreen$lambda$31 = UniversalDocumentEditorScreen$lambda$31(mutableState10);
        String strUniversalDocumentEditorScreen$lambda$34 = UniversalDocumentEditorScreen$lambda$34(mutableState11);
        String strUniversalDocumentEditorScreen$lambda$37 = UniversalDocumentEditorScreen$lambda$37(mutableState12);
        String strUniversalDocumentEditorScreen$lambda$43 = UniversalDocumentEditorScreen$lambda$43(mutableState13);
        Integer intOrNull = StringsKt.toIntOrNull(UniversalDocumentEditorScreen$lambda$46(mutableState14));
        Document documentCopy$default = Document.copy$default(document, 0L, null, null, strUniversalDocumentEditorScreen$lambda$7, strUniversalDocumentEditorScreen$lambda$10, doubleOrNull, strUniversalDocumentEditorScreen$lambda$28, strUniversalDocumentEditorScreen$lambda$13, strUniversalDocumentEditorScreen$lambda$19, strUniversalDocumentEditorScreen$lambda$22, strUniversalDocumentEditorScreen$lambda$40, null, intOrNull != null ? intOrNull.intValue() : 0, 0L, false, null, System.currentTimeMillis(), strUniversalDocumentEditorScreen$lambda$43, strUniversalDocumentEditorScreen$lambda$31, strUniversalDocumentEditorScreen$lambda$34, strUniversalDocumentEditorScreen$lambda$37, strUniversalDocumentEditorScreen$lambda$16, null, null, null, null, null, null, null, 532736007, null);
        if (document.getId() != 0 || function1 == null) {
            masrofViewModel.updateDocument(documentCopy$default);
        } else {
            function1.invoke(documentCopy$default);
        }
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$83(Document document, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C82@6424L76:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1848624266, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous>.<anonymous>.<anonymous> (UniversalDocumentEditorScreen.kt:82)");
            }
            TextKt.m3342Text4IGK_g(document.getId() == 0 ? "إنشاء المستند وحفظه" : "حفظ جميع التعديلات", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$85$lambda$84 */
    static final Unit m878x1d70f197(MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$5(mutableState, !UniversalDocumentEditorScreen$lambda$4(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$86(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C83@6624L85:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(866804856, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous>.<anonymous>.<anonymous> (UniversalDocumentEditorScreen.kt:83)");
            }
            TextKt.m3342Text4IGK_g(UniversalDocumentEditorScreen$lambda$4(mutableState) ? "إغلاق محرر المستندات" : "فتح محرر المستندات المدمج", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$89(MasrofViewModel masrofViewModel, Document document, final MutableState mutableState, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C84@6840L30,84@6795L75:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1592510189, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous>.<anonymous>.<anonymous> (UniversalDocumentEditorScreen.kt:84)");
            }
            DocumentType type = document.getType();
            ComposerKt.sourceInformationMarkerStart(composer, -1393932719, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.m879x2d087b37(mutableState);
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

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$89$lambda$88$lambda$87 */
    static final Unit m879x2d087b37(MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$5(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$91$lambda$90 */
    static final Unit m880xd18fcc0d(MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$2(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$93$lambda$92 */
    static final Unit m881xfb593191(Context context, Document document) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportPdf(context, CollectionsKt.listOf(document)), "مشاركة المستند PDF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$95$lambda$94 */
    static final Unit m882x25229715(Context context, Document document) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportDocx(context, document), "تصدير ملف DOCX");
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$99$lambda$98(MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$2(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$109(Document document, MutableState mutableState, MutableState mutableState2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C99@7772L21,99@7741L254:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-173582904, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous> (UniversalDocumentEditorScreen.kt:99)");
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
            ComposerKt.sourceInformationMarkerStart(composer, 2009545998, "C99@7798L47,99@7847L55,99@7904L48,99@7954L39:UniversalDocumentEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("رقم المستند: " + document.getDocumentNumber(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            String strUniversalDocumentEditorScreen$lambda$13 = UniversalDocumentEditorScreen$lambda$13(mutableState);
            if (StringsKt.isBlank(strUniversalDocumentEditorScreen$lambda$13)) {
                strUniversalDocumentEditorScreen$lambda$13 = "غير محدد";
            }
            TextKt.m3342Text4IGK_g("المستفيد: " + ((Object) strUniversalDocumentEditorScreen$lambda$13), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            String strUniversalDocumentEditorScreen$lambda$19 = UniversalDocumentEditorScreen$lambda$19(mutableState2);
            if (StringsKt.isBlank(strUniversalDocumentEditorScreen$lambda$19)) {
                strUniversalDocumentEditorScreen$lambda$19 = "غير محدد";
            }
            TextKt.m3342Text4IGK_g("الغرض: " + ((Object) strUniversalDocumentEditorScreen$lambda$19), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            TextKt.m3342Text4IGK_g("الحالة: " + document.getStatus().name(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
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

    static final Unit UniversalDocumentEditorScreen$lambda$102(final Function1 function1, final Document document, final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C100@8042L58,100@8025L109:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1439858029, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous> (UniversalDocumentEditorScreen.kt:100)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 724097223, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function1) | composer.changed(document);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$102$lambda$101$lambda$100(function1, document, mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.m7701getLambda$1189682307$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$102$lambda$101$lambda$100(Function1 function1, Document document, MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$2(mutableState, false);
        function1.invoke(String.valueOf(document.getId()));
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$105(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C101@8185L23,101@8164L63:UniversalDocumentEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1653475115, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreen.<anonymous> (UniversalDocumentEditorScreen.kt:101)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -148313246, "CC(remember):UniversalDocumentEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UniversalDocumentEditorScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen$lambda$105$lambda$104$lambda$103(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$UniversalDocumentEditorScreenKt.INSTANCE.getLambda$1991980302$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UniversalDocumentEditorScreen$lambda$105$lambda$104$lambda$103(MutableState mutableState) {
        UniversalDocumentEditorScreen$lambda$2(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UniversalDocumentEditorScreen$lambda$97$lambda$96$lambda$78$lambda$75$lambda$74 */
    static final Unit m874xd38a0d53(MutableState mutableState, String it) throws IOException {
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
