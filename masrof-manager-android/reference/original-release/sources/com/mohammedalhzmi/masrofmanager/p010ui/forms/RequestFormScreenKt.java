package com.mohammedalhzmi.masrofmanager.p010ui.forms;

import android.content.Context;
import androidx.compose.p000ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.text.util.LocalePreferences;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.data.ModelsKt;
import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering;
import com.mohammedalhzmi.masrofmanager.util.FormMemory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RequestFormScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\rX\u008a\u008e\u0002"}, m914d2 = {"RequestFormScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "onNavigateBack", "Lkotlin/Function0;", "existing", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "documentType", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lkotlin/jvm/functions/Function0;Lcom/mohammedalhzmi/masrofmanager/data/Document;Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;Landroidx/compose/runtime/Composer;II)V", "app", "requester", "", "directedTo", "details", "hijri", LocalePreferences.CalendarType.GREGORIAN, "tags", "category", "costCenter", "fundingSource"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class RequestFormScreenKt {
    static final Unit RequestFormScreen$lambda$53(MasrofViewModel masrofViewModel, Function0 function0, Document document, DocumentType documentType, int i, int i2, Composer composer, int i3) {
        RequestFormScreen(masrofViewModel, function0, document, documentType, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:103:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:105:0x01c9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:106:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:107:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:113:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:114:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:117:0x01fe  */
    /* JADX WARN: Code duplicated, block: B:119:0x0206 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x0208  */
    /* JADX WARN: Code duplicated, block: B:121:0x020d  */
    /* JADX WARN: Code duplicated, block: B:123:0x0210  */
    /* JADX WARN: Code duplicated, block: B:127:0x0220  */
    /* JADX WARN: Code duplicated, block: B:128:0x0229  */
    /* JADX WARN: Code duplicated, block: B:131:0x023a  */
    /* JADX WARN: Code duplicated, block: B:133:0x0242 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:134:0x0244  */
    /* JADX WARN: Code duplicated, block: B:135:0x0249  */
    /* JADX WARN: Code duplicated, block: B:137:0x024c  */
    /* JADX WARN: Code duplicated, block: B:141:0x025e  */
    /* JADX WARN: Code duplicated, block: B:142:0x0267  */
    /* JADX WARN: Code duplicated, block: B:145:0x0278  */
    /* JADX WARN: Code duplicated, block: B:147:0x0280 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:148:0x0282  */
    /* JADX WARN: Code duplicated, block: B:149:0x0287  */
    /* JADX WARN: Code duplicated, block: B:151:0x028a  */
    /* JADX WARN: Code duplicated, block: B:155:0x029d  */
    /* JADX WARN: Code duplicated, block: B:156:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:159:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:163:0x02c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:164:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:165:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:167:0x02cd  */
    /* JADX WARN: Code duplicated, block: B:171:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:172:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:175:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:177:0x0304 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:178:0x0306  */
    /* JADX WARN: Code duplicated, block: B:179:0x030b  */
    /* JADX WARN: Code duplicated, block: B:181:0x030e  */
    /* JADX WARN: Code duplicated, block: B:185:0x0339  */
    /* JADX WARN: Code duplicated, block: B:186:0x034d  */
    /* JADX WARN: Code duplicated, block: B:189:0x038e  */
    /* JADX WARN: Code duplicated, block: B:193:0x0398  */
    /* JADX WARN: Code duplicated, block: B:195:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0060  */
    /* JADX WARN: Code duplicated, block: B:31:0x0063  */
    /* JADX WARN: Code duplicated, block: B:33:0x0067 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:34:0x0069  */
    /* JADX WARN: Code duplicated, block: B:35:0x006b  */
    /* JADX WARN: Code duplicated, block: B:38:0x0079  */
    /* JADX WARN: Code duplicated, block: B:39:0x007c  */
    /* JADX WARN: Code duplicated, block: B:47:0x0095  */
    /* JADX WARN: Code duplicated, block: B:49:0x0098  */
    /* JADX WARN: Code duplicated, block: B:51:0x009b  */
    /* JADX WARN: Code duplicated, block: B:52:0x009f  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:59:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:62:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ef A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:67:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:73:0x0117  */
    /* JADX WARN: Code duplicated, block: B:74:0x0120  */
    /* JADX WARN: Code duplicated, block: B:77:0x0131  */
    /* JADX WARN: Code duplicated, block: B:79:0x0139 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x013b  */
    /* JADX WARN: Code duplicated, block: B:82:0x0141  */
    /* JADX WARN: Code duplicated, block: B:86:0x015b  */
    /* JADX WARN: Code duplicated, block: B:87:0x0164  */
    /* JADX WARN: Code duplicated, block: B:90:0x0175  */
    /* JADX WARN: Code duplicated, block: B:92:0x017d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x017f  */
    /* JADX WARN: Code duplicated, block: B:95:0x0185  */
    /* JADX WARN: Code duplicated, block: B:99:0x01a5  */
    public static final void RequestFormScreen(final MasrofViewModel viewModel, final Function0<Unit> onNavigateBack, Document document, DocumentType documentType, Composer composer, final int i, final int i2) {
        int i3;
        Document document2;
        int i4;
        int iOrdinal;
        int i5;
        final DocumentType documentType2;
        final Context context;
        Long lValueOf;
        boolean zChanged;
        Object objRememberedValue;
        String beneficiaryName;
        Long lValueOf2;
        boolean zChanged2;
        Object objRememberedValue2;
        String purpose;
        Long lValueOf3;
        boolean zChanged3;
        Object objRememberedValue3;
        String details;
        Long lValueOf4;
        boolean zChanged4;
        Object objRememberedValue4;
        String dateHijri;
        Long lValueOf5;
        boolean zChanged5;
        Object objRememberedValue5;
        String dateGregorian;
        Long lValueOf6;
        boolean zChanged6;
        Object objRememberedValue6;
        String tags;
        Long lValueOf7;
        boolean zChanged7;
        Object objRememberedValue7;
        String financialCategory;
        Long lValueOf8;
        boolean zChanged8;
        Object objRememberedValue8;
        String costCenter;
        Long lValueOf9;
        boolean zChanged9;
        Object objRememberedValue9;
        String fundingSource;
        String strDisplayName;
        StringBuilder sb;
        String string;
        final Document document3;
        Composer composer2;
        final DocumentType documentType3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onNavigateBack, "onNavigateBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(1972069468);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(RequestFormScreen)P(3,2,1)14@744L7,15@773L120,16@916L161,17@1097L110,18@1225L72,19@1319L76,20@1412L67,21@1500L80,22@1603L73,23@1702L76,26@2002L2359,26@1926L2435:RequestFormScreen.kt#h8pn2q");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onNavigateBack) ? 32 : 16;
        }
        int i6 = i2 & 4;
        if (i6 == 0) {
            if ((i & 384) == 0) {
                document2 = document;
                i3 |= composerStartRestartGroup.changed(document2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (documentType == null) {
                    iOrdinal = -1;
                } else {
                    iOrdinal = documentType.ordinal();
                }
                if (composerStartRestartGroup.changed(iOrdinal)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i3 & 1171) != 1170 && composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.skipToGroupEnd();
                documentType3 = documentType;
                composer2 = composerStartRestartGroup;
                document3 = document2;
            } else {
                if (i6 != 0) {
                    document2 = null;
                }
                if (i4 != 0) {
                    documentType2 = DocumentType.REQUEST;
                } else {
                    documentType2 = documentType;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1972069468, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreen (RequestFormScreen.kt:13)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                context = (Context) objConsume;
                if (document2 != null) {
                    lValueOf = Long.valueOf(document2.getId());
                } else {
                    lValueOf = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078188724, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(lValueOf);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (beneficiaryName = document2.getBeneficiaryName()) == null) {
                        beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                    }
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MutableState mutableState = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf2 = Long.valueOf(document2.getId());
                } else {
                    lValueOf2 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078193341, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(lValueOf2);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (purpose = document2.getPurpose()) == null) {
                        purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                    }
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final MutableState mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf3 = Long.valueOf(document2.getId());
                } else {
                    lValueOf3 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078199082, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(lValueOf3);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (details = document2.getDetails()) == null) {
                        details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                    }
                    objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MutableState mutableState3 = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf4 = Long.valueOf(document2.getId());
                } else {
                    lValueOf4 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078203140, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged4 = composerStartRestartGroup.changed(lValueOf4);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        dateHijri = document2.getDateHijri();
                    } else {
                        dateHijri = null;
                    }
                    if (dateHijri == null) {
                        dateHijri = "";
                    }
                    objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf5 = Long.valueOf(document2.getId());
                } else {
                    lValueOf5 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078206152, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged5 = composerStartRestartGroup.changed(lValueOf5);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        dateGregorian = document2.getDateGregorian();
                    } else {
                        dateGregorian = null;
                    }
                    if (dateGregorian == null) {
                        dateGregorian = "";
                    }
                    objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                final MutableState mutableState5 = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf6 = Long.valueOf(document2.getId());
                } else {
                    lValueOf6 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078209119, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged6 = composerStartRestartGroup.changed(lValueOf6);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        tags = document2.getTags();
                    } else {
                        tags = null;
                    }
                    if (tags == null) {
                        tags = "";
                    }
                    MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                    objRememberedValue6 = mutableStateMutableStateOf$default;
                }
                final MutableState mutableState6 = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf7 = Long.valueOf(document2.getId());
                } else {
                    lValueOf7 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078211948, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged7 = composerStartRestartGroup.changed(lValueOf7);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (!zChanged7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        financialCategory = document2.getFinancialCategory();
                    } else {
                        financialCategory = null;
                    }
                    if (financialCategory == null) {
                        financialCategory = "";
                    }
                    MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                    objRememberedValue7 = mutableStateMutableStateOf$default2;
                }
                final MutableState mutableState7 = (MutableState) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf8 = Long.valueOf(document2.getId());
                } else {
                    lValueOf8 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078215237, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged8 = composerStartRestartGroup.changed(lValueOf8);
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (!zChanged8 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        costCenter = document2.getCostCenter();
                    } else {
                        costCenter = null;
                    }
                    if (costCenter == null) {
                        costCenter = "";
                    }
                    MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                    objRememberedValue8 = mutableStateMutableStateOf$default3;
                }
                final MutableState mutableState8 = (MutableState) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf9 = Long.valueOf(document2.getId());
                } else {
                    lValueOf9 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078218408, "CC(remember):RequestFormScreen.kt#9igjgp");
                zChanged9 = composerStartRestartGroup.changed(lValueOf9);
                objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (!zChanged9 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        fundingSource = document2.getFundingSource();
                    } else {
                        fundingSource = null;
                    }
                    if (fundingSource == null) {
                        fundingSource = "";
                    }
                    objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                }
                final MutableState mutableState9 = (MutableState) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final String strPadStart = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, documentType2)), 4, '0');
                strDisplayName = ModelsKt.displayName(documentType2);
                if (document2 == null) {
                    sb = new StringBuilder();
                    string = sb.append(strDisplayName).append(" جديدة").toString();
                } else {
                    sb = new StringBuilder("تعديل ");
                    string = sb.append(strDisplayName).toString();
                }
                document3 = document2;
                Function2 function2 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52(mutableState4, mutableState5, document3, strPadStart, mutableState2, mutableState, mutableState3, mutableState7, mutableState8, mutableState9, mutableState6, documentType2, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                    }
                };
                composer2 = composerStartRestartGroup;
                OfficialFormComponentsKt.OfficialFormShell(string, ComposableLambdaKt.rememberComposableLambda(207731303, true, function2, composer2, 54), composer2, 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                documentType3 = documentType2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$53(viewModel, onNavigateBack, document3, documentType3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        document2 = document;
        i4 = i2 & 8;
        if (i4 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (documentType == null) {
                iOrdinal = -1;
            } else {
                iOrdinal = documentType.ordinal();
            }
            if (composerStartRestartGroup.changed(iOrdinal)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i3 |= i5;
        }
        if ((i3 & 1171) != 1170) {
            if (i6 != 0) {
                document2 = null;
            }
            if (i4 != 0) {
                documentType2 = DocumentType.REQUEST;
            } else {
                documentType2 = documentType;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1972069468, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreen (RequestFormScreen.kt:13)");
            }
            ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localContext2);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            context = (Context) objConsume2;
            if (document2 != null) {
                lValueOf = Long.valueOf(document2.getId());
            } else {
                lValueOf = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078188724, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(lValueOf);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState10 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf2 = Long.valueOf(document2.getId());
            } else {
                lValueOf2 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078193341, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(lValueOf2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                if (document2 != null) {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                } else {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                if (document2 != null) {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                } else {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState11 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf3 = Long.valueOf(document2.getId());
            } else {
                lValueOf3 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078199082, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(lValueOf3);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged3) {
                if (document2 != null) {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                } else {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (document2 != null) {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                } else {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState12 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf4 = Long.valueOf(document2.getId());
            } else {
                lValueOf4 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078203140, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(lValueOf4);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!zChanged4) {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState13 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf5 = Long.valueOf(document2.getId());
            } else {
                lValueOf5 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078206152, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(lValueOf5);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChanged5) {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState14 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf6 = Long.valueOf(document2.getId());
            } else {
                lValueOf6 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078209119, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(lValueOf6);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (!zChanged6) {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue6 = mutableStateMutableStateOf$default4;
            } else {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue6 = mutableStateMutableStateOf$default5;
            }
            final MutableState mutableState15 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf7 = Long.valueOf(document2.getId());
            } else {
                lValueOf7 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078211948, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(lValueOf7);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (!zChanged7) {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue7 = mutableStateMutableStateOf$default6;
            } else {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue7 = mutableStateMutableStateOf$default7;
            }
            final MutableState mutableState16 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf8 = Long.valueOf(document2.getId());
            } else {
                lValueOf8 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078215237, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(lValueOf8);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (!zChanged8) {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                objRememberedValue8 = mutableStateMutableStateOf$default8;
            } else {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                objRememberedValue8 = mutableStateMutableStateOf$default9;
            }
            final MutableState mutableState17 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf9 = Long.valueOf(document2.getId());
            } else {
                lValueOf9 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078218408, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(lValueOf9);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (!zChanged9) {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            final MutableState mutableState18 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final String strPadStart2 = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, documentType2)), 4, '0');
            strDisplayName = ModelsKt.displayName(documentType2);
            if (document2 == null) {
                sb = new StringBuilder();
                string = sb.append(strDisplayName).append(" جديدة").toString();
            } else {
                sb = new StringBuilder("تعديل ");
                string = sb.append(strDisplayName).toString();
            }
            document3 = document2;
            Function2 function3 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RequestFormScreenKt.RequestFormScreen$lambda$52(mutableState13, mutableState14, document3, strPadStart2, mutableState11, mutableState10, mutableState12, mutableState16, mutableState17, mutableState18, mutableState15, documentType2, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            composer2 = composerStartRestartGroup;
            OfficialFormComponentsKt.OfficialFormShell(string, ComposableLambdaKt.rememberComposableLambda(207731303, true, function3, composer2, 54), composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            documentType3 = documentType2;
        } else {
            if (i6 != 0) {
                document2 = null;
            }
            if (i4 != 0) {
                documentType2 = DocumentType.REQUEST;
            } else {
                documentType2 = documentType;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1972069468, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreen (RequestFormScreen.kt:13)");
            }
            ProvidableCompositionLocal<Context> localContext3 = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume3 = composerStartRestartGroup.consume(localContext3);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            context = (Context) objConsume3;
            if (document2 != null) {
                lValueOf = Long.valueOf(document2.getId());
            } else {
                lValueOf = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078188724, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(lValueOf);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "requester", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState19 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf2 = Long.valueOf(document2.getId());
            } else {
                lValueOf2 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078193341, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(lValueOf2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                if (document2 != null) {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                } else {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                if (document2 != null) {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                } else {
                    purpose = FormMemory.INSTANCE.read(context, "request", "directed", "مدير فرع صندوق النظافة والتحسين - مديرية الحزم");
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState110 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf3 = Long.valueOf(document2.getId());
            } else {
                lValueOf3 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078199082, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(lValueOf3);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged3) {
                if (document2 != null) {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                } else {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (document2 != null) {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                } else {
                    details = FormMemory.read$default(FormMemory.INSTANCE, context, "request", "details", null, 8, null);
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(details, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState111 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf4 = Long.valueOf(document2.getId());
            } else {
                lValueOf4 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078203140, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(lValueOf4);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!zChanged4) {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState112 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf5 = Long.valueOf(document2.getId());
            } else {
                lValueOf5 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078206152, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(lValueOf5);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChanged5) {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState113 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf6 = Long.valueOf(document2.getId());
            } else {
                lValueOf6 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078209119, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(lValueOf6);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (!zChanged6) {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default10);
                objRememberedValue6 = mutableStateMutableStateOf$default10;
            } else {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default11);
                objRememberedValue6 = mutableStateMutableStateOf$default11;
            }
            final MutableState mutableState114 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf7 = Long.valueOf(document2.getId());
            } else {
                lValueOf7 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078211948, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(lValueOf7);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (!zChanged7) {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default12);
                objRememberedValue7 = mutableStateMutableStateOf$default12;
            } else {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default13);
                objRememberedValue7 = mutableStateMutableStateOf$default13;
            }
            final MutableState mutableState115 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf8 = Long.valueOf(document2.getId());
            } else {
                lValueOf8 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078215237, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(lValueOf8);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (!zChanged8) {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default14);
                objRememberedValue8 = mutableStateMutableStateOf$default14;
            } else {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default15 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default15);
                objRememberedValue8 = mutableStateMutableStateOf$default15;
            }
            final MutableState mutableState116 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf9 = Long.valueOf(document2.getId());
            } else {
                lValueOf9 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1078218408, "CC(remember):RequestFormScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(lValueOf9);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (!zChanged9) {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            final MutableState mutableState117 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final String strPadStart3 = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, documentType2)), 4, '0');
            strDisplayName = ModelsKt.displayName(documentType2);
            if (document2 == null) {
                sb = new StringBuilder();
                string = sb.append(strDisplayName).append(" جديدة").toString();
            } else {
                sb = new StringBuilder("تعديل ");
                string = sb.append(strDisplayName).toString();
            }
            document3 = document2;
            Function2 function4 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RequestFormScreenKt.RequestFormScreen$lambda$52(mutableState112, mutableState113, document3, strPadStart3, mutableState110, mutableState19, mutableState111, mutableState115, mutableState116, mutableState117, mutableState114, documentType2, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            composer2 = composerStartRestartGroup;
            OfficialFormComponentsKt.OfficialFormShell(string, ComposableLambdaKt.rememberComposableLambda(207731303, true, function4, composer2, 54), composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            documentType3 = documentType2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RequestFormScreenKt.RequestFormScreen$lambda$53(viewModel, onNavigateBack, document3, documentType3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String RequestFormScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String RequestFormScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit RequestFormScreen$lambda$52(final MutableState mutableState, final MutableState mutableState2, final Document document, final String str, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, final MutableState mutableState9, final DocumentType documentType, final MasrofViewModel masrofViewModel, final Context context, final Function0 function0, Composer composer, int i) {
        Composer composer2;
        ComposerKt.sourceInformation(composer, "C27@2044L14,27@2060L18,27@2012L67,28@2194L3,28@2088L110,29@2249L19,29@2207L62,30@2321L18,30@2278L62,31@2388L16,31@2349L59,32@2454L17,32@2417L55,33@2523L19,33@2481L62,34@2597L22,34@2552L68,35@2696L3,35@2629L71,36@2728L13,36@2709L33,37@2841L1514,37@2751L1604:RequestFormScreen.kt#h8pn2q");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(207731303, i, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreen.<anonymous> (RequestFormScreen.kt:27)");
            }
            String strRequestFormScreen$lambda$10 = RequestFormScreen$lambda$10(mutableState);
            String strRequestFormScreen$lambda$13 = RequestFormScreen$lambda$13(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, 2061287925, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$28$lambda$27(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 2061288441, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged2 = composer.changed(mutableState2);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$30$lambda$29(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialDates(strRequestFormScreen$lambda$10, strRequestFormScreen$lambda$13, function1, (Function1) objRememberedValue2, composer, 0);
            String documentNumber = document == null ? str : document.getDocumentNumber();
            ComposerKt.sourceInformationMarkerStart(composer, 2061292714, "CC(remember):RequestFormScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$32$lambda$31((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(documentNumber, "رقم المستند (تلقائي)", (Function1) objRememberedValue3, 0, composer, 432, 8);
            String strRequestFormScreen$lambda$4 = RequestFormScreen$lambda$4(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, 2061294490, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged3 = composer.changed(mutableState3);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$34$lambda$33(mutableState3, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$4, "المخاطب إليه", (Function1) objRememberedValue4, 0, composer, 48, 8);
            String strRequestFormScreen$lambda$1 = RequestFormScreen$lambda$1(mutableState4);
            ComposerKt.sourceInformationMarkerStart(composer, 2061296793, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged4 = composer.changed(mutableState4);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$36$lambda$35(mutableState4, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$1, "اسم مقدم الطلب", (Function1) objRememberedValue5, 0, composer, 48, 8);
            String strRequestFormScreen$lambda$7 = RequestFormScreen$lambda$7(mutableState5);
            ComposerKt.sourceInformationMarkerStart(composer, 2061298935, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged5 = composer.changed(mutableState5);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$38$lambda$37(mutableState5, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$7, "تفاصيل الطلب", (Function1) objRememberedValue6, 6, composer, 3120, 0);
            String strRequestFormScreen$lambda$19 = RequestFormScreen$lambda$19(mutableState6);
            ComposerKt.sourceInformationMarkerStart(composer, 2061301048, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged6 = composer.changed(mutableState6);
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$40$lambda$39(mutableState6, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$19, "بند الطلب", (Function1) objRememberedValue7, 0, composer, 48, 8);
            String strRequestFormScreen$lambda$22 = RequestFormScreen$lambda$22(mutableState7);
            ComposerKt.sourceInformationMarkerStart(composer, 2061303258, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged7 = composer.changed(mutableState7);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChanged7 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$42$lambda$41(mutableState7, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$22, "مركز التكلفة", (Function1) objRememberedValue8, 0, composer, 48, 8);
            String strRequestFormScreen$lambda$25 = RequestFormScreen$lambda$25(mutableState8);
            ComposerKt.sourceInformationMarkerStart(composer, 2061305629, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged8 = composer.changed(mutableState8);
            Object objRememberedValue9 = composer.rememberedValue();
            if (zChanged8 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$44$lambda$43(mutableState8, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strRequestFormScreen$lambda$25, "مصدر التمويل", (Function1) objRememberedValue9, 0, composer, 48, 8);
            String notes = document != null ? document.getNotes() : null;
            if (notes == null) {
                notes = "";
            }
            String str2 = notes;
            ComposerKt.sourceInformationMarkerStart(composer, 2061308778, "CC(remember):RequestFormScreen.kt#9igjgp");
            Object objRememberedValue10 = composer.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$46$lambda$45((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(str2, "المرفقات / رقم النموذج", (Function1) objRememberedValue10, 0, composer, 432, 8);
            String strRequestFormScreen$lambda$16 = RequestFormScreen$lambda$16(mutableState9);
            ComposerKt.sourceInformationMarkerStart(composer, 2061309812, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged9 = composer.changed(mutableState9);
            Object objRememberedValue11 = composer.rememberedValue();
            if (zChanged9 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$48$lambda$47(mutableState9, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialTags(strRequestFormScreen$lambda$16, (Function1) objRememberedValue11, composer, 0);
            String str3 = document == null ? "حفظ ورقة التقديم الرسمية" : "حفظ التعديلات";
            ComposerKt.sourceInformationMarkerStart(composer, 2061314929, "CC(remember):RequestFormScreen.kt#9igjgp");
            boolean zChanged10 = composer.changed(document) | composer.changed(str) | composer.changed(mutableState) | composer.changed(mutableState2) | composer.changed(mutableState4) | composer.changed(mutableState3) | composer.changed(mutableState5) | composer.changed(mutableState6) | composer.changed(mutableState7) | composer.changed(mutableState8) | composer.changed(mutableState9) | composer.changed(documentType.ordinal()) | composer.changedInstance(masrofViewModel) | composer.changedInstance(context) | composer.changed(function0);
            Object objRememberedValue12 = composer.rememberedValue();
            if (zChanged10 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.RequestFormScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RequestFormScreenKt.RequestFormScreen$lambda$52$lambda$51$lambda$50(document, str, documentType, masrofViewModel, context, function0, mutableState, mutableState2, mutableState4, mutableState3, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9);
                    }
                };
                composer2 = composer;
                composer2.updateRememberedValue(objRememberedValue12);
            } else {
                composer2 = composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OfficialFormComponentsKt.SaveOfficialButton(str3, (Function0) objRememberedValue12, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$28$lambda$27(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$30$lambda$29(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$32$lambda$31(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$34$lambda$33(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$36$lambda$35(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$38$lambda$37(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$40$lambda$39(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$42$lambda$41(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$44$lambda$43(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$46$lambda$45(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$48$lambda$47(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit RequestFormScreen$lambda$52$lambda$51$lambda$50(Document document, String str, DocumentType documentType, MasrofViewModel masrofViewModel, Context context, Function0 function0, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9) {
        String cloudId;
        String rejectionReason;
        String approvedBy;
        String reviewedBy;
        String submittedBy;
        String beneficiaryId;
        String documentNumber;
        long id = document != null ? document.getId() : 0L;
        String str2 = (document == null || (documentNumber = document.getDocumentNumber()) == null) ? str : documentNumber;
        String strRequestFormScreen$lambda$10 = RequestFormScreen$lambda$10(mutableState);
        String strRequestFormScreen$lambda$13 = RequestFormScreen$lambda$13(mutableState2);
        String strRequestFormScreen$lambda$1 = RequestFormScreen$lambda$1(mutableState3);
        String strRequestFormScreen$lambda$4 = RequestFormScreen$lambda$4(mutableState4);
        String strRequestFormScreen$lambda$7 = RequestFormScreen$lambda$7(mutableState5);
        String notes = document != null ? document.getNotes() : null;
        DocumentStatus documentStatus = DocumentStatus.SUBMITTED;
        int attachmentsCount = document != null ? document.getAttachmentsCount() : 0;
        long createdAt = document != null ? document.getCreatedAt() : System.currentTimeMillis();
        boolean zIsArchived = document != null ? document.isArchived() : false;
        Long archivedAt = document != null ? document.getArchivedAt() : null;
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strRequestFormScreen$lambda$19 = RequestFormScreen$lambda$19(mutableState6);
        String strRequestFormScreen$lambda$22 = RequestFormScreen$lambda$22(mutableState7);
        String strRequestFormScreen$lambda$25 = RequestFormScreen$lambda$25(mutableState8);
        String str3 = (document == null || (beneficiaryId = document.getBeneficiaryId()) == null) ? "" : beneficiaryId;
        String str4 = (document == null || (submittedBy = document.getSubmittedBy()) == null) ? "" : submittedBy;
        String str5 = (document == null || (reviewedBy = document.getReviewedBy()) == null) ? "" : reviewedBy;
        String str6 = (document == null || (approvedBy = document.getApprovedBy()) == null) ? "" : approvedBy;
        Long approvedAt = document != null ? document.getApprovedAt() : null;
        Long paidAt = document != null ? document.getPaidAt() : null;
        String str7 = (document == null || (rejectionReason = document.getRejectionReason()) == null) ? "" : rejectionReason;
        List listSplit$default = StringsKt.split$default((CharSequence) RequestFormScreen$lambda$16(mutableState9), new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList2.add(obj);
            }
        }
        Document document2 = new Document(id, documentType, str2, strRequestFormScreen$lambda$10, strRequestFormScreen$lambda$13, null, null, strRequestFormScreen$lambda$1, strRequestFormScreen$lambda$4, strRequestFormScreen$lambda$7, notes, documentStatus, attachmentsCount, createdAt, zIsArchived, archivedAt, jCurrentTimeMillis, CollectionsKt.joinToString$default(CollectionsKt.distinct(arrayList2), ",", null, null, 0, null, null, 62, null), strRequestFormScreen$lambda$19, strRequestFormScreen$lambda$22, strRequestFormScreen$lambda$25, str3, str4, str5, str6, approvedAt, paidAt, str7, (document == null || (cloudId = document.getCloudId()) == null) ? "" : cloudId);
        if (document == null) {
            masrofViewModel.addDocument(document2);
        } else {
            masrofViewModel.updateDocument(document2);
        }
        if (document == null) {
            DocumentNumbering.INSTANCE.consume(context, documentType);
        }
        FormMemory.INSTANCE.remember(context, "request", "requester", RequestFormScreen$lambda$1(mutableState3));
        FormMemory.INSTANCE.remember(context, "request", "directed", RequestFormScreen$lambda$4(mutableState4));
        FormMemory.INSTANCE.remember(context, "request", "details", RequestFormScreen$lambda$7(mutableState5));
        function0.invoke();
        return Unit.INSTANCE;
    }
}
