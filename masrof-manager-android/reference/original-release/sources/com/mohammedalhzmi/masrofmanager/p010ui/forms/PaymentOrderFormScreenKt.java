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
import com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel;
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering;
import com.mohammedalhzmi.masrofmanager.util.FormMemory;
import com.mohammedalhzmi.masrofmanager.util.NumberToWordsConverter;
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

/* JADX INFO: compiled from: PaymentOrderFormScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0010\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u000bX\u008a\u008e\u0002"}, m914d2 = {"PaymentOrderFormScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "onNavigateBack", "Lkotlin/Function0;", "existing", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lkotlin/jvm/functions/Function0;Lcom/mohammedalhzmi/masrofmanager/data/Document;Landroidx/compose/runtime/Composer;II)V", "app", "beneficiary", "", "amountText", "purpose", "hijri", LocalePreferences.CalendarType.GREGORIAN, "attachments", "tags", "category", "costCenter", "fundingSource", "beneficiaryId"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class PaymentOrderFormScreenKt {
    static final Unit PaymentOrderFormScreen$lambda$66(MasrofViewModel masrofViewModel, Function0 function0, Document document, int i, int i2, Composer composer, int i3) {
        PaymentOrderFormScreen(masrofViewModel, function0, document, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:103:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:105:0x01e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:106:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:107:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:109:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:113:0x01f9  */
    /* JADX WARN: Code duplicated, block: B:114:0x0202  */
    /* JADX WARN: Code duplicated, block: B:117:0x0213  */
    /* JADX WARN: Code duplicated, block: B:119:0x021b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x021d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0222  */
    /* JADX WARN: Code duplicated, block: B:123:0x0225  */
    /* JADX WARN: Code duplicated, block: B:127:0x0236  */
    /* JADX WARN: Code duplicated, block: B:128:0x023f  */
    /* JADX WARN: Code duplicated, block: B:131:0x0250  */
    /* JADX WARN: Code duplicated, block: B:133:0x0258 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:134:0x025a  */
    /* JADX WARN: Code duplicated, block: B:135:0x025f  */
    /* JADX WARN: Code duplicated, block: B:137:0x0262  */
    /* JADX WARN: Code duplicated, block: B:141:0x0274  */
    /* JADX WARN: Code duplicated, block: B:142:0x027d  */
    /* JADX WARN: Code duplicated, block: B:145:0x028e  */
    /* JADX WARN: Code duplicated, block: B:149:0x029a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:150:0x029c  */
    /* JADX WARN: Code duplicated, block: B:151:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:153:0x02a4  */
    /* JADX WARN: Code duplicated, block: B:157:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:158:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:161:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:163:0x02da A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:164:0x02dc  */
    /* JADX WARN: Code duplicated, block: B:165:0x02e1  */
    /* JADX WARN: Code duplicated, block: B:167:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:171:0x02f6  */
    /* JADX WARN: Code duplicated, block: B:172:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:175:0x0310  */
    /* JADX WARN: Code duplicated, block: B:179:0x031c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:180:0x031e  */
    /* JADX WARN: Code duplicated, block: B:181:0x0323  */
    /* JADX WARN: Code duplicated, block: B:183:0x0326  */
    /* JADX WARN: Code duplicated, block: B:187:0x033a  */
    /* JADX WARN: Code duplicated, block: B:188:0x0343  */
    /* JADX WARN: Code duplicated, block: B:191:0x0354  */
    /* JADX WARN: Code duplicated, block: B:193:0x035c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:194:0x035e  */
    /* JADX WARN: Code duplicated, block: B:195:0x0363  */
    /* JADX WARN: Code duplicated, block: B:198:0x0367  */
    /* JADX WARN: Code duplicated, block: B:202:0x0393  */
    /* JADX WARN: Code duplicated, block: B:203:0x0396  */
    /* JADX WARN: Code duplicated, block: B:206:0x03d2  */
    /* JADX WARN: Code duplicated, block: B:209:0x03db  */
    /* JADX WARN: Code duplicated, block: B:211:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x006f  */
    /* JADX WARN: Code duplicated, block: B:36:0x0072  */
    /* JADX WARN: Code duplicated, block: B:39:0x0079  */
    /* JADX WARN: Code duplicated, block: B:42:0x009a  */
    /* JADX WARN: Code duplicated, block: B:43:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:48:0x00be A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:49:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:51:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:55:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:56:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:59:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:61:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x0108  */
    /* JADX WARN: Code duplicated, block: B:66:0x0114  */
    /* JADX WARN: Code duplicated, block: B:70:0x0132  */
    /* JADX WARN: Code duplicated, block: B:71:0x013b  */
    /* JADX WARN: Code duplicated, block: B:74:0x014c  */
    /* JADX WARN: Code duplicated, block: B:78:0x0159 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x015b  */
    /* JADX WARN: Code duplicated, block: B:81:0x0161  */
    /* JADX WARN: Code duplicated, block: B:85:0x0181  */
    /* JADX WARN: Code duplicated, block: B:86:0x018a  */
    /* JADX WARN: Code duplicated, block: B:89:0x019d  */
    /* JADX WARN: Code duplicated, block: B:91:0x01a5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:92:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:93:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:95:0x01af  */
    /* JADX WARN: Code duplicated, block: B:99:0x01be  */
    public static final void PaymentOrderFormScreen(final MasrofViewModel viewModel, final Function0<Unit> onNavigateBack, Document document, Composer composer, final int i, final int i2) {
        int i3;
        Document document2;
        final Context context;
        Long lValueOf;
        boolean zChanged;
        Object objRememberedValue;
        String beneficiaryName;
        Long lValueOf2;
        boolean zChanged2;
        Object objRememberedValue2;
        String string;
        Double amount;
        Long lValueOf3;
        boolean zChanged3;
        String purpose;
        Object objMutableStateOf$default;
        Long lValueOf4;
        boolean zChanged4;
        Object objRememberedValue3;
        String dateHijri;
        Long lValueOf5;
        boolean zChanged5;
        Object objRememberedValue4;
        String dateGregorian;
        Long lValueOf6;
        boolean zChanged6;
        Object objRememberedValue5;
        String notes;
        Long lValueOf7;
        boolean zChanged7;
        Object objRememberedValue6;
        String tags;
        Long lValueOf8;
        boolean zChanged8;
        Object objRememberedValue7;
        String financialCategory;
        Long lValueOf9;
        boolean zChanged9;
        Object objRememberedValue8;
        String costCenter;
        Long lValueOf10;
        boolean zChanged10;
        Object objRememberedValue9;
        String fundingSource;
        Long lValueOf11;
        boolean zChanged11;
        Object objRememberedValue10;
        String beneficiaryId;
        String str;
        final Document document3;
        Composer composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onNavigateBack, "onNavigateBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1644220939);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PaymentOrderFormScreen)P(2,1)14@709L7,15@740L120,16@883L118,17@1021L108,18@1147L72,19@1241L76,20@1341L68,21@1426L67,22@1514L80,23@1617L73,24@1716L76,25@1818L76,28@2127L2594,28@2050L2671:PaymentOrderFormScreen.kt#h8pn2q");
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onNavigateBack) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 == 0) {
            if ((i & 384) == 0) {
                document2 = document;
                i3 |= composerStartRestartGroup.changed(document2) ? 256 : 128;
            }
            if ((i3 & 147) != 146 && composerStartRestartGroup.getSkipping()) {
                composerStartRestartGroup.skipToGroupEnd();
                composer2 = composerStartRestartGroup;
                document3 = document2;
            } else {
                if (i4 != 0) {
                    document2 = null;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1644220939, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreen (PaymentOrderFormScreen.kt:13)");
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451648653, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged = composerStartRestartGroup.changed(lValueOf);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (beneficiaryName = document2.getBeneficiaryName()) == null) {
                        beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451653227, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged2 = composerStartRestartGroup.changed(lValueOf2);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (!zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (amount = document2.getAmount()) == null || (string = amount.toString()) == null) {
                        string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                    }
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final MutableState mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf3 = Long.valueOf(document2.getId());
                } else {
                    lValueOf3 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451657633, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged3 = composerStartRestartGroup.changed(lValueOf3);
                Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (!zChanged3 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null || (purpose = document2.getPurpose()) == null) {
                        purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                    }
                    objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                } else {
                    objMutableStateOf$default = objRememberedValue11;
                }
                final MutableState mutableState3 = (MutableState) objMutableStateOf$default;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf4 = Long.valueOf(document2.getId());
                } else {
                    lValueOf4 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451661629, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged4 = composerStartRestartGroup.changed(lValueOf4);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (!zChanged4 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        dateHijri = document2.getDateHijri();
                    } else {
                        dateHijri = null;
                    }
                    if (dateHijri == null) {
                        dateHijri = "";
                    }
                    objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf5 = Long.valueOf(document2.getId());
                } else {
                    lValueOf5 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451664641, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged5 = composerStartRestartGroup.changed(lValueOf5);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (!zChanged5 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        dateGregorian = document2.getDateGregorian();
                    } else {
                        dateGregorian = null;
                    }
                    if (dateGregorian == null) {
                        dateGregorian = "";
                    }
                    objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                final MutableState mutableState5 = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf6 = Long.valueOf(document2.getId());
                } else {
                    lValueOf6 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451667833, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged6 = composerStartRestartGroup.changed(lValueOf6);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (!zChanged6 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        notes = document2.getNotes();
                    } else {
                        notes = null;
                    }
                    if (notes == null) {
                        notes = "";
                    }
                    MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                    objRememberedValue5 = mutableStateMutableStateOf$default;
                }
                final MutableState mutableState6 = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf7 = Long.valueOf(document2.getId());
                } else {
                    lValueOf7 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451670552, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged7 = composerStartRestartGroup.changed(lValueOf7);
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (!zChanged7 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        tags = document2.getTags();
                    } else {
                        tags = null;
                    }
                    if (tags == null) {
                        tags = "";
                    }
                    MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                    objRememberedValue6 = mutableStateMutableStateOf$default2;
                }
                final MutableState mutableState7 = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf8 = Long.valueOf(document2.getId());
                } else {
                    lValueOf8 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451673381, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged8 = composerStartRestartGroup.changed(lValueOf8);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (!zChanged8 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        financialCategory = document2.getFinancialCategory();
                    } else {
                        financialCategory = null;
                    }
                    if (financialCategory == null) {
                        financialCategory = "";
                    }
                    MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                    objRememberedValue7 = mutableStateMutableStateOf$default3;
                }
                final MutableState mutableState8 = (MutableState) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf9 = Long.valueOf(document2.getId());
                } else {
                    lValueOf9 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451676670, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged9 = composerStartRestartGroup.changed(lValueOf9);
                objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (!zChanged9 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        costCenter = document2.getCostCenter();
                    } else {
                        costCenter = null;
                    }
                    if (costCenter == null) {
                        costCenter = "";
                    }
                    MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                    objRememberedValue8 = mutableStateMutableStateOf$default4;
                }
                final MutableState mutableState9 = (MutableState) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf10 = Long.valueOf(document2.getId());
                } else {
                    lValueOf10 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451679841, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged10 = composerStartRestartGroup.changed(lValueOf10);
                objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (!zChanged10 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        fundingSource = document2.getFundingSource();
                    } else {
                        fundingSource = null;
                    }
                    if (fundingSource == null) {
                        fundingSource = "";
                    }
                    MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                    objRememberedValue9 = mutableStateMutableStateOf$default5;
                }
                final MutableState mutableState10 = (MutableState) objRememberedValue9;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (document2 != null) {
                    lValueOf11 = Long.valueOf(document2.getId());
                } else {
                    lValueOf11 = null;
                }
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451683105, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
                zChanged11 = composerStartRestartGroup.changed(lValueOf11);
                objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (!zChanged11 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    if (document2 != null) {
                        beneficiaryId = document2.getBeneficiaryId();
                    } else {
                        beneficiaryId = null;
                    }
                    objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryId != null ? beneficiaryId : "", null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                }
                final MutableState mutableState11 = (MutableState) objRememberedValue10;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final String strPadStart = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.ORDER)), 4, '0');
                final Double doubleOrNull = StringsKt.toDoubleOrNull(PaymentOrderFormScreen$lambda$4(mutableState2));
                if (document2 == null) {
                    str = "أمر صرف جديد";
                } else {
                    str = "تعديل أمر صرف";
                }
                document3 = document2;
                Function2 function2 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65(mutableState4, mutableState5, document3, strPadStart, mutableState, mutableState2, doubleOrNull, mutableState3, mutableState8, mutableState9, mutableState10, mutableState11, mutableState6, mutableState7, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                    }
                };
                composer2 = composerStartRestartGroup;
                OfficialFormComponentsKt.OfficialFormShell(str, ComposableLambdaKt.rememberComposableLambda(942705610, true, function2, composer2, 54), composer2, 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$66(viewModel, onNavigateBack, document3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 384;
        document2 = document;
        if ((i3 & 147) != 146) {
            if (i4 != 0) {
                document2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1644220939, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreen (PaymentOrderFormScreen.kt:13)");
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451648653, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(lValueOf);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState12 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf2 = Long.valueOf(document2.getId());
            } else {
                lValueOf2 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451653227, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(lValueOf2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                if (document2 != null) {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                } else {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                if (document2 != null) {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                } else {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState13 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf3 = Long.valueOf(document2.getId());
            } else {
                lValueOf3 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451657633, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(lValueOf3);
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                if (document2 != null) {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                } else {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            } else {
                if (document2 != null) {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                } else {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            }
            final MutableState mutableState14 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf4 = Long.valueOf(document2.getId());
            } else {
                lValueOf4 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451661629, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(lValueOf4);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged4) {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState15 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf5 = Long.valueOf(document2.getId());
            } else {
                lValueOf5 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451664641, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(lValueOf5);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!zChanged5) {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState16 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf6 = Long.valueOf(document2.getId());
            } else {
                lValueOf6 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451667833, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(lValueOf6);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChanged6) {
                if (document2 != null) {
                    notes = document2.getNotes();
                } else {
                    notes = null;
                }
                if (notes == null) {
                    notes = "";
                }
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue5 = mutableStateMutableStateOf$default6;
            } else {
                if (document2 != null) {
                    notes = document2.getNotes();
                } else {
                    notes = null;
                }
                if (notes == null) {
                    notes = "";
                }
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue5 = mutableStateMutableStateOf$default7;
            }
            final MutableState mutableState17 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf7 = Long.valueOf(document2.getId());
            } else {
                lValueOf7 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451670552, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(lValueOf7);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (!zChanged7) {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                objRememberedValue6 = mutableStateMutableStateOf$default8;
            } else {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                objRememberedValue6 = mutableStateMutableStateOf$default9;
            }
            final MutableState mutableState18 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf8 = Long.valueOf(document2.getId());
            } else {
                lValueOf8 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451673381, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(lValueOf8);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (!zChanged8) {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default10);
                objRememberedValue7 = mutableStateMutableStateOf$default10;
            } else {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default11);
                objRememberedValue7 = mutableStateMutableStateOf$default11;
            }
            final MutableState mutableState19 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf9 = Long.valueOf(document2.getId());
            } else {
                lValueOf9 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451676670, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(lValueOf9);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (!zChanged9) {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default12);
                objRememberedValue8 = mutableStateMutableStateOf$default12;
            } else {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default13);
                objRememberedValue8 = mutableStateMutableStateOf$default13;
            }
            final MutableState mutableState20 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf10 = Long.valueOf(document2.getId());
            } else {
                lValueOf10 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451679841, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged10 = composerStartRestartGroup.changed(lValueOf10);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (!zChanged10) {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                MutableState mutableStateMutableStateOf$default14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default14);
                objRememberedValue9 = mutableStateMutableStateOf$default14;
            } else {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                MutableState mutableStateMutableStateOf$default15 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default15);
                objRememberedValue9 = mutableStateMutableStateOf$default15;
            }
            final MutableState mutableState110 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf11 = Long.valueOf(document2.getId());
            } else {
                lValueOf11 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451683105, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged11 = composerStartRestartGroup.changed(lValueOf11);
            objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (!zChanged11) {
                if (document2 != null) {
                    beneficiaryId = document2.getBeneficiaryId();
                } else {
                    beneficiaryId = null;
                }
                objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryId != null ? beneficiaryId : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            } else {
                if (document2 != null) {
                    beneficiaryId = document2.getBeneficiaryId();
                } else {
                    beneficiaryId = null;
                }
                objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryId != null ? beneficiaryId : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            final MutableState mutableState111 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final String strPadStart2 = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.ORDER)), 4, '0');
            final Double doubleOrNull2 = StringsKt.toDoubleOrNull(PaymentOrderFormScreen$lambda$4(mutableState13));
            if (document2 == null) {
                str = "أمر صرف جديد";
            } else {
                str = "تعديل أمر صرف";
            }
            document3 = document2;
            Function2 function3 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65(mutableState15, mutableState16, document3, strPadStart2, mutableState12, mutableState13, doubleOrNull2, mutableState14, mutableState19, mutableState20, mutableState110, mutableState111, mutableState17, mutableState18, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            composer2 = composerStartRestartGroup;
            OfficialFormComponentsKt.OfficialFormShell(str, ComposableLambdaKt.rememberComposableLambda(942705610, true, function3, composer2, 54), composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i4 != 0) {
                document2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1644220939, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreen (PaymentOrderFormScreen.kt:13)");
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451648653, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged = composerStartRestartGroup.changed(lValueOf);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (!zChanged) {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                if (document2 != null) {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                } else {
                    beneficiaryName = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "beneficiary", null, 8, null);
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryName, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState112 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf2 = Long.valueOf(document2.getId());
            } else {
                lValueOf2 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451653227, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged2 = composerStartRestartGroup.changed(lValueOf2);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (!zChanged2) {
                if (document2 != null) {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                } else {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                if (document2 != null) {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                } else {
                    string = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "amount", null, 8, null);
                }
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(string, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState113 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf3 = Long.valueOf(document2.getId());
            } else {
                lValueOf3 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451657633, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged3 = composerStartRestartGroup.changed(lValueOf3);
            Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (zChanged3) {
                if (document2 != null) {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                } else {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            } else {
                if (document2 != null) {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                } else {
                    purpose = FormMemory.read$default(FormMemory.INSTANCE, context, "order", "purpose", null, 8, null);
                }
                objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(purpose, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
            }
            final MutableState mutableState114 = (MutableState) objMutableStateOf$default;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf4 = Long.valueOf(document2.getId());
            } else {
                lValueOf4 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451661629, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged4 = composerStartRestartGroup.changed(lValueOf4);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (!zChanged4) {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                if (document2 != null) {
                    dateHijri = document2.getDateHijri();
                } else {
                    dateHijri = null;
                }
                if (dateHijri == null) {
                    dateHijri = "";
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateHijri, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState115 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf5 = Long.valueOf(document2.getId());
            } else {
                lValueOf5 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451664641, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged5 = composerStartRestartGroup.changed(lValueOf5);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (!zChanged5) {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                if (document2 != null) {
                    dateGregorian = document2.getDateGregorian();
                } else {
                    dateGregorian = null;
                }
                if (dateGregorian == null) {
                    dateGregorian = "";
                }
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(dateGregorian, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState116 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf6 = Long.valueOf(document2.getId());
            } else {
                lValueOf6 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451667833, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged6 = composerStartRestartGroup.changed(lValueOf6);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (!zChanged6) {
                if (document2 != null) {
                    notes = document2.getNotes();
                } else {
                    notes = null;
                }
                if (notes == null) {
                    notes = "";
                }
                MutableState mutableStateMutableStateOf$default16 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default16);
                objRememberedValue5 = mutableStateMutableStateOf$default16;
            } else {
                if (document2 != null) {
                    notes = document2.getNotes();
                } else {
                    notes = null;
                }
                if (notes == null) {
                    notes = "";
                }
                MutableState mutableStateMutableStateOf$default17 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(notes, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default17);
                objRememberedValue5 = mutableStateMutableStateOf$default17;
            }
            final MutableState mutableState117 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf7 = Long.valueOf(document2.getId());
            } else {
                lValueOf7 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451670552, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged7 = composerStartRestartGroup.changed(lValueOf7);
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (!zChanged7) {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default18 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default18);
                objRememberedValue6 = mutableStateMutableStateOf$default18;
            } else {
                if (document2 != null) {
                    tags = document2.getTags();
                } else {
                    tags = null;
                }
                if (tags == null) {
                    tags = "";
                }
                MutableState mutableStateMutableStateOf$default19 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(tags, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default19);
                objRememberedValue6 = mutableStateMutableStateOf$default19;
            }
            final MutableState mutableState118 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf8 = Long.valueOf(document2.getId());
            } else {
                lValueOf8 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451673381, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged8 = composerStartRestartGroup.changed(lValueOf8);
            objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (!zChanged8) {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default110 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default110);
                objRememberedValue7 = mutableStateMutableStateOf$default110;
            } else {
                if (document2 != null) {
                    financialCategory = document2.getFinancialCategory();
                } else {
                    financialCategory = null;
                }
                if (financialCategory == null) {
                    financialCategory = "";
                }
                MutableState mutableStateMutableStateOf$default111 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(financialCategory, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default111);
                objRememberedValue7 = mutableStateMutableStateOf$default111;
            }
            final MutableState mutableState119 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf9 = Long.valueOf(document2.getId());
            } else {
                lValueOf9 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451676670, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged9 = composerStartRestartGroup.changed(lValueOf9);
            objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (!zChanged9) {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default112 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default112);
                objRememberedValue8 = mutableStateMutableStateOf$default112;
            } else {
                if (document2 != null) {
                    costCenter = document2.getCostCenter();
                } else {
                    costCenter = null;
                }
                if (costCenter == null) {
                    costCenter = "";
                }
                MutableState mutableStateMutableStateOf$default113 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(costCenter, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default113);
                objRememberedValue8 = mutableStateMutableStateOf$default113;
            }
            final MutableState mutableState21 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf10 = Long.valueOf(document2.getId());
            } else {
                lValueOf10 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451679841, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged10 = composerStartRestartGroup.changed(lValueOf10);
            objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (!zChanged10) {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                MutableState mutableStateMutableStateOf$default114 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default114);
                objRememberedValue9 = mutableStateMutableStateOf$default114;
            } else {
                if (document2 != null) {
                    fundingSource = document2.getFundingSource();
                } else {
                    fundingSource = null;
                }
                if (fundingSource == null) {
                    fundingSource = "";
                }
                MutableState mutableStateMutableStateOf$default115 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fundingSource, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default115);
                objRememberedValue9 = mutableStateMutableStateOf$default115;
            }
            final MutableState mutableState1110 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (document2 != null) {
                lValueOf11 = Long.valueOf(document2.getId());
            } else {
                lValueOf11 = null;
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 451683105, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            zChanged11 = composerStartRestartGroup.changed(lValueOf11);
            objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (!zChanged11) {
                if (document2 != null) {
                    beneficiaryId = document2.getBeneficiaryId();
                } else {
                    beneficiaryId = null;
                }
                objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryId != null ? beneficiaryId : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            } else {
                if (document2 != null) {
                    beneficiaryId = document2.getBeneficiaryId();
                } else {
                    beneficiaryId = null;
                }
                objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(beneficiaryId != null ? beneficiaryId : "", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            final MutableState mutableState1111 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final String strPadStart3 = StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.ORDER)), 4, '0');
            final Double doubleOrNull3 = StringsKt.toDoubleOrNull(PaymentOrderFormScreen$lambda$4(mutableState113));
            if (document2 == null) {
                str = "أمر صرف جديد";
            } else {
                str = "تعديل أمر صرف";
            }
            document3 = document2;
            Function2 function4 = new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65(mutableState115, mutableState116, document3, strPadStart3, mutableState112, mutableState113, doubleOrNull3, mutableState114, mutableState119, mutableState21, mutableState1110, mutableState1111, mutableState117, mutableState118, viewModel, context, onNavigateBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            composer2 = composerStartRestartGroup;
            OfficialFormComponentsKt.OfficialFormShell(str, ComposableLambdaKt.rememberComposableLambda(942705610, true, function4, composer2, 54), composer2, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$66(viewModel, onNavigateBack, document3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String PaymentOrderFormScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$28(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PaymentOrderFormScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0159  */
    static final Unit PaymentOrderFormScreen$lambda$65(final MutableState mutableState, final MutableState mutableState2, final Document document, final String str, final MutableState mutableState3, final MutableState mutableState4, final Double d, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, final MutableState mutableState9, final MutableState mutableState10, final MutableState mutableState11, final MasrofViewModel masrofViewModel, final Context context, final Function0 function0, Composer composer, int i) {
        String strConvert;
        Composer composer2;
        ComposerKt.sourceInformation(composer, "C29@2169L14,29@2185L18,29@2137L67,30@2321L3,30@2213L112,31@2380L20,31@2334L67,32@2467L19,32@2410L77,33@2608L3,33@2496L116,34@2658L16,34@2621L57,35@2726L17,35@2687L57,36@2795L19,36@2753L62,37@2869L22,37@2824L68,38@2958L22,38@2901L80,39@3029L20,39@2990L60,40@3078L13,40@3059L33,41@3187L1528,41@3101L1614:PaymentOrderFormScreen.kt#h8pn2q");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(942705610, i, -1, "com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreen.<anonymous> (PaymentOrderFormScreen.kt:29)");
            }
            String strPaymentOrderFormScreen$lambda$10 = PaymentOrderFormScreen$lambda$10(mutableState);
            String strPaymentOrderFormScreen$lambda$13 = PaymentOrderFormScreen$lambda$13(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, -298006888, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$34$lambda$33(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, -298006372, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged2 = composer.changed(mutableState2);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$36$lambda$35(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialDates(strPaymentOrderFormScreen$lambda$10, strPaymentOrderFormScreen$lambda$13, function1, (Function1) objRememberedValue2, composer, 0);
            String documentNumber = document == null ? str : document.getDocumentNumber();
            ComposerKt.sourceInformationMarkerStart(composer, -298002035, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$38$lambda$37((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(documentNumber, "رقم أمر الصرف (تلقائي)", (Function1) objRememberedValue3, 0, composer, 432, 8);
            String strPaymentOrderFormScreen$lambda$1 = PaymentOrderFormScreen$lambda$1(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, -298000130, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged3 = composer.changed(mutableState3);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$40$lambda$39(mutableState3, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$1, "الأخ / المستفيد", (Function1) objRememberedValue4, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$4 = PaymentOrderFormScreen$lambda$4(mutableState4);
            ComposerKt.sourceInformationMarkerStart(composer, -297997347, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged4 = composer.changed(mutableState4);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$42$lambda$41(mutableState4, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$4, "المبلغ بالأرقام (ريال يمني)", (Function1) objRememberedValue5, 0, composer, 48, 8);
            if (d != null) {
                strConvert = NumberToWordsConverter.INSTANCE.convert(d.doubleValue());
                if (strConvert == null) {
                    strConvert = "سيظهر المبلغ كتابةً هنا";
                }
            } else {
                strConvert = "سيظهر المبلغ كتابةً هنا";
            }
            String str2 = strConvert;
            ComposerKt.sourceInformationMarkerStart(composer, -297992851, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            Object objRememberedValue6 = composer.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$45$lambda$44((String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(str2, "المبلغ كتابةً", (Function1) objRememberedValue6, 0, composer, 432, 8);
            String strPaymentOrderFormScreen$lambda$7 = PaymentOrderFormScreen$lambda$7(mutableState5);
            ComposerKt.sourceInformationMarkerStart(composer, -297991238, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged5 = composer.changed(mutableState5);
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$47$lambda$46(mutableState5, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$7, "وذلك مقابل", (Function1) objRememberedValue7, 3, composer, 3120, 0);
            String strPaymentOrderFormScreen$lambda$22 = PaymentOrderFormScreen$lambda$22(mutableState6);
            ComposerKt.sourceInformationMarkerStart(composer, -297989061, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged6 = composer.changed(mutableState6);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChanged6 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$49$lambda$48(mutableState6, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$22, "بند المصروف", (Function1) objRememberedValue8, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$25 = PaymentOrderFormScreen$lambda$25(mutableState7);
            ComposerKt.sourceInformationMarkerStart(composer, -297986851, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged7 = composer.changed(mutableState7);
            Object objRememberedValue9 = composer.rememberedValue();
            if (zChanged7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$51$lambda$50(mutableState7, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$25, "مركز التكلفة", (Function1) objRememberedValue9, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$28 = PaymentOrderFormScreen$lambda$28(mutableState8);
            ComposerKt.sourceInformationMarkerStart(composer, -297984480, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged8 = composer.changed(mutableState8);
            Object objRememberedValue10 = composer.rememberedValue();
            if (zChanged8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$53$lambda$52(mutableState8, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$28, "مصدر التمويل", (Function1) objRememberedValue10, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$31 = PaymentOrderFormScreen$lambda$31(mutableState9);
            ComposerKt.sourceInformationMarkerStart(composer, -297981632, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged9 = composer.changed(mutableState9);
            Object objRememberedValue11 = composer.rememberedValue();
            if (zChanged9 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$55$lambda$54(mutableState9, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$31, "رقم هوية / حساب المستفيد", (Function1) objRememberedValue11, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$16 = PaymentOrderFormScreen$lambda$16(mutableState10);
            ComposerKt.sourceInformationMarkerStart(composer, -297979362, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged10 = composer.changed(mutableState10);
            Object objRememberedValue12 = composer.rememberedValue();
            if (zChanged10 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$57$lambda$56(mutableState10, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialField(strPaymentOrderFormScreen$lambda$16, "المرفقات", (Function1) objRememberedValue12, 0, composer, 48, 8);
            String strPaymentOrderFormScreen$lambda$19 = PaymentOrderFormScreen$lambda$19(mutableState11);
            ComposerKt.sourceInformationMarkerStart(composer, -297977801, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged11 = composer.changed(mutableState11);
            Object objRememberedValue13 = composer.rememberedValue();
            if (zChanged11 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$59$lambda$58(mutableState11, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue13);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OfficialFormComponentsKt.OfficialTags(strPaymentOrderFormScreen$lambda$19, (Function1) objRememberedValue13, composer, 0);
            String str3 = document == null ? "حفظ أمر الصرف الرسمي" : "حفظ التعديلات";
            ComposerKt.sourceInformationMarkerStart(composer, -297972798, "CC(remember):PaymentOrderFormScreen.kt#9igjgp");
            boolean zChanged12 = composer.changed(document) | composer.changed(str) | composer.changed(mutableState) | composer.changed(mutableState2) | composer.changed(d) | composer.changed(mutableState3) | composer.changed(mutableState5) | composer.changed(mutableState10) | composer.changed(mutableState6) | composer.changed(mutableState7) | composer.changed(mutableState8) | composer.changed(mutableState9) | composer.changed(mutableState11) | composer.changedInstance(masrofViewModel) | composer.changedInstance(context) | composer.changed(mutableState4) | composer.changed(function0);
            Object objRememberedValue14 = composer.rememberedValue();
            if (zChanged12 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue14 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.forms.PaymentOrderFormScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentOrderFormScreenKt.PaymentOrderFormScreen$lambda$65$lambda$64$lambda$63(document, str, d, masrofViewModel, context, function0, mutableState, mutableState2, mutableState3, mutableState5, mutableState10, mutableState6, mutableState7, mutableState8, mutableState9, mutableState11, mutableState4);
                    }
                };
                composer2 = composer;
                composer2.updateRememberedValue(objRememberedValue14);
            } else {
                composer2 = composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OfficialFormComponentsKt.SaveOfficialButton(str3, (Function0) objRememberedValue14, composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$34$lambda$33(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$36$lambda$35(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$38$lambda$37(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$40$lambda$39(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$42$lambda$41(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$45$lambda$44(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$47$lambda$46(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$49$lambda$48(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$51$lambda$50(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$53$lambda$52(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$55$lambda$54(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$57$lambda$56(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$59$lambda$58(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentOrderFormScreen$lambda$65$lambda$64$lambda$63(Document document, String str, Double d, MasrofViewModel masrofViewModel, Context context, Function0 function0, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, MutableState mutableState10, MutableState mutableState11) {
        String rejectionReason;
        String approvedBy;
        String reviewedBy;
        String submittedBy;
        String documentNumber;
        long id = document != null ? document.getId() : 0L;
        DocumentType documentType = DocumentType.ORDER;
        String str2 = (document == null || (documentNumber = document.getDocumentNumber()) == null) ? str : documentNumber;
        String strPaymentOrderFormScreen$lambda$10 = PaymentOrderFormScreen$lambda$10(mutableState);
        String strPaymentOrderFormScreen$lambda$13 = PaymentOrderFormScreen$lambda$13(mutableState2);
        String strConvert = d != null ? NumberToWordsConverter.INSTANCE.convert(d.doubleValue()) : null;
        String strPaymentOrderFormScreen$lambda$1 = PaymentOrderFormScreen$lambda$1(mutableState3);
        String strPaymentOrderFormScreen$lambda$7 = PaymentOrderFormScreen$lambda$7(mutableState4);
        String strPaymentOrderFormScreen$lambda$16 = PaymentOrderFormScreen$lambda$16(mutableState5);
        if (StringsKt.isBlank(strPaymentOrderFormScreen$lambda$16)) {
            strPaymentOrderFormScreen$lambda$16 = null;
        }
        String str3 = strPaymentOrderFormScreen$lambda$16;
        DocumentStatus documentStatus = DocumentStatus.SUBMITTED;
        int attachmentsCount = document != null ? document.getAttachmentsCount() : 0;
        long createdAt = document != null ? document.getCreatedAt() : System.currentTimeMillis();
        boolean zIsArchived = document != null ? document.isArchived() : false;
        Long archivedAt = document != null ? document.getArchivedAt() : null;
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strPaymentOrderFormScreen$lambda$22 = PaymentOrderFormScreen$lambda$22(mutableState6);
        String strPaymentOrderFormScreen$lambda$25 = PaymentOrderFormScreen$lambda$25(mutableState7);
        String strPaymentOrderFormScreen$lambda$28 = PaymentOrderFormScreen$lambda$28(mutableState8);
        String strPaymentOrderFormScreen$lambda$31 = PaymentOrderFormScreen$lambda$31(mutableState9);
        String str4 = (document == null || (submittedBy = document.getSubmittedBy()) == null) ? "" : submittedBy;
        String str5 = (document == null || (reviewedBy = document.getReviewedBy()) == null) ? "" : reviewedBy;
        String str6 = (document == null || (approvedBy = document.getApprovedBy()) == null) ? "" : approvedBy;
        Long approvedAt = document != null ? document.getApprovedAt() : null;
        Long paidAt = document != null ? document.getPaidAt() : null;
        String str7 = (document == null || (rejectionReason = document.getRejectionReason()) == null) ? "" : rejectionReason;
        List listSplit$default = StringsKt.split$default((CharSequence) PaymentOrderFormScreen$lambda$19(mutableState10), new String[]{","}, false, 0, 6, (Object) null);
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
        Document document2 = new Document(id, documentType, str2, strPaymentOrderFormScreen$lambda$10, strPaymentOrderFormScreen$lambda$13, d, strConvert, strPaymentOrderFormScreen$lambda$1, strPaymentOrderFormScreen$lambda$7, null, str3, documentStatus, attachmentsCount, createdAt, zIsArchived, archivedAt, jCurrentTimeMillis, CollectionsKt.joinToString$default(CollectionsKt.distinct(arrayList2), ",", null, null, 0, null, null, 62, null), strPaymentOrderFormScreen$lambda$22, strPaymentOrderFormScreen$lambda$25, strPaymentOrderFormScreen$lambda$28, strPaymentOrderFormScreen$lambda$31, str4, str5, str6, approvedAt, paidAt, str7, null, 268435456, null);
        if (document == null) {
            masrofViewModel.addDocument(document2);
        } else {
            masrofViewModel.updateDocument(document2);
        }
        if (document == null) {
            DocumentNumbering.INSTANCE.consume(context, DocumentType.ORDER);
        }
        FormMemory.INSTANCE.remember(context, "order", "beneficiary", PaymentOrderFormScreen$lambda$1(mutableState3));
        FormMemory.INSTANCE.remember(context, "order", "amount", PaymentOrderFormScreen$lambda$4(mutableState11));
        FormMemory.INSTANCE.remember(context, "order", "purpose", PaymentOrderFormScreen$lambda$7(mutableState4));
        function0.invoke();
        return Unit.INSTANCE;
    }
}
