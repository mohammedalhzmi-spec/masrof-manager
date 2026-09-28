package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.net.Uri;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.autofill.HintConstants;
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
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SwitchKt;
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
import com.google.firebase.firestore.model.Values;
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity;
import com.mohammedalhzmi.masrofmanager.data.UserEntity;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
import com.mohammedalhzmi.masrofmanager.util.AuditExporter;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UserManagementScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f²\u0006\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u008a\u0084\u0002²\u0006\u0010\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eX\u008a\u0084\u0002²\u0006\f\u0010\u0012\u001a\u0004\u0018\u00010\u000fX\u008a\u008e\u0002²\u0006\n\u0010\u0013\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\bX\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u008e\u0002"}, m914d2 = {"UserManagementScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "onBack", "Lkotlin/Function0;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "date", "", "kotlin.jvm.PlatformType", Values.VECTOR_MAP_VECTORS_KEY, "", "app", "users", "", "Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "logs", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "editing", HintConstants.AUTOFILL_HINT_USERNAME, "fullName", "password", "role", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "active", ""}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class UserManagementScreenKt {
    static final Unit UserManagementScreen$lambda$72(MasrofViewModel masrofViewModel, Function0 function0, int i, Composer composer, int i2) {
        UserManagementScreen(masrofViewModel, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void UserManagementScreen(final MasrofViewModel viewModel, final Function0<Unit> onBack, Composer composer, final int i) {
        int i2;
        Object obj;
        final MutableState mutableState;
        final MutableState mutableState2;
        final MutableState mutableState3;
        final MutableState mutableState4;
        final MutableState mutableState5;
        MutableState mutableState6;
        final MutableState mutableState7;
        MutableState mutableState8;
        MutableState mutableState9;
        MutableState mutableState10;
        MutableState mutableState11;
        Object obj2;
        final MasrofViewModel masrofViewModel;
        String str;
        MutableState mutableState12;
        MutableState mutableState13;
        String str2;
        int i3;
        int i4;
        final MutableState mutableState14;
        MutableState mutableState15;
        char c;
        final MutableState mutableState16;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1245599177);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(UserManagementScreen)P(1)21@910L16,22@963L16,23@1011L7,24@1127L118,24@1041L204,25@1365L114,25@1269L210,26@1595L112,26@1502L205,27@1727L46,28@1794L31,29@1846L31,30@1898L31,31@1946L41,32@2006L33,34@2321L3690:UserManagementScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onBack) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            masrofViewModel = viewModel;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1245599177, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.UserManagementScreen (UserManagementScreen.kt:20)");
            }
            final State stateCollectAsState = SnapshotStateKt.collectAsState(viewModel.getAllUsers(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(viewModel.getAuditLogs(), null, composerStartRestartGroup, 0, 1);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ActivityResultContracts.CreateDocument createDocument = new ActivityResultContracts.CreateDocument("text/csv");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275077485, "CC(remember):UserManagementScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changedInstance(viewModel);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$4$lambda$3(context, viewModel, stateCollectAsState2, (Uri) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(createDocument, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            ActivityResultContracts.CreateDocument createDocument2 = new ActivityResultContracts.CreateDocument("application/msword");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275085097, "CC(remember):UserManagementScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changedInstance(viewModel);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$7$lambda$6(context, viewModel, stateCollectAsState2, (Uri) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(createDocument2, (Function1) objRememberedValue2, composerStartRestartGroup, 0);
            ActivityResultContracts.CreateDocument createDocument3 = new ActivityResultContracts.CreateDocument("application/pdf");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275092455, "CC(remember):UserManagementScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changedInstance(viewModel);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$10$lambda$9(context, viewModel, stateCollectAsState2, (Uri) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult3 = ActivityResultRegistryKt.rememberLauncherForActivityResult(createDocument3, (Function1) objRememberedValue3, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275096613, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState17 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275098742, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            int i5 = i2;
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            MutableState mutableState18 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275100406, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            MutableState mutableState19 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275102070, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            MutableState mutableState20 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275103616, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppRole.USER, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue8 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState21 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1275105528, "CC(remember):UserManagementScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                obj = null;
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                obj = null;
            }
            MutableState mutableState22 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, obj), C1786Dp.m7249constructorimpl(16.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            MutableState mutableState23 = mutableState21;
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 200389665, "C35@2388L214,36@2698L10,36@2611L108,37@2756L17,37@2728L162,38@2927L17,38@2899L135,39@3071L17,39@3098L77,39@3043L188,40@3240L237,41@3486L145,42@3760L222,45@4020L66,42@3640L446,47@4266L10,47@4227L103,48@4382L768,48@4339L811,57@5202L10,57@5159L65,58@5233L485,63@5782L223,63@5727L278:UserManagementScreen.kt#ska5t9");
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1683136242, "C35@2527L10,35@2480L73,35@2555L45:UserManagementScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("إدارة المستخدمين", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineMedium(), composerStartRestartGroup, 6, 0, 65534);
            ButtonKt.TextButton(onBack, null, false, null, null, null, null, null, null, ComposableSingletons$UserManagementScreenKt.INSTANCE.m7713getLambda$283968640$app(), composerStartRestartGroup, ((i5 >> 3) & 14) | 805306368, 510);
            Composer composer2 = composerStartRestartGroup;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            TextKt.m3342Text4IGK_g("أنشئ حسابات مستقلة، حدّد الدور، وأوقف الحساب عند الحاجة.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall(), composer2, 6, 0, 65534);
            String strUserManagementScreen$lambda$15 = UserManagementScreen$lambda$15(mutableState18);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            boolean z = UserManagementScreen$lambda$12(mutableState17) == null;
            String str3 = "CC(remember):UserManagementScreen.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer2, -686264302, str3);
            Object objRememberedValue10 = composer2.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState18;
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$33$lambda$32(mutableState, (String) obj3);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue10);
            } else {
                mutableState = mutableState18;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OutlinedTextFieldKt.OutlinedTextField(strUserManagementScreen$lambda$15, (Function1<? super String, Unit>) objRememberedValue10, modifierFillMaxWidth$default2, z, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UserManagementScreenKt.INSTANCE.m7714getLambda$479832665$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 12582912, 0, 8257456);
            String strUserManagementScreen$lambda$18 = UserManagementScreen$lambda$18(mutableState19);
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer2, -686258830, str3);
            Object objRememberedValue11 = composer2.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                mutableState2 = mutableState19;
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$35$lambda$34(mutableState2, (String) obj3);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue11);
            } else {
                mutableState2 = mutableState19;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OutlinedTextFieldKt.OutlinedTextField(strUserManagementScreen$lambda$18, (Function1<? super String, Unit>) objRememberedValue11, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$UserManagementScreenKt.INSTANCE.m7711getLambda$1819701872$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 12582912, 0, 8257464);
            String strUserManagementScreen$lambda$21 = UserManagementScreen$lambda$21(mutableState20);
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer2, -686254222, str3);
            Object objRememberedValue12 = composer2.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                mutableState3 = mutableState20;
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$37$lambda$36(mutableState3, (String) obj3);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue12);
            } else {
                mutableState3 = mutableState20;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            final MutableState mutableState24 = mutableState17;
            char c2 = '6';
            OutlinedTextFieldKt.OutlinedTextField(strUserManagementScreen$lambda$21, (Function1<? super String, Unit>) objRememberedValue12, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1492272047, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$38(mutableState24, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composer2, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 12582912, 0, 8257464);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            String str4 = "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, str4);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getTop(), composer2, 6);
            String str5 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str5);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, companion);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            String str6 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str6);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor3);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            String str7 = "C101@5126L9:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composer2, -407840262, str7);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, 74871020, "C:UserManagementScreen.kt#ska5t9");
            composer2.startReplaceGroup(972247046);
            ComposerKt.sourceInformation(composer2, "");
            AppRole[] appRoleArrValues = AppRole.values();
            int length = appRoleArrValues.length;
            int i6 = 0;
            while (i6 < length) {
                final AppRole appRole = appRoleArrValues[i6];
                if (appRole == UserManagementScreen$lambda$24(mutableState23)) {
                    composer2.startReplaceGroup(-190176292);
                    ComposerKt.sourceInformation(composer2, "40@3368L15,40@3385L20,40@3351L54");
                    ComposerKt.sourceInformationMarkerStart(composer2, -190175787, str3);
                    boolean zChanged = composer2.changed(appRole.ordinal());
                    Object objRememberedValue13 = composer2.rememberedValue();
                    if (zChanged || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                        mutableState16 = mutableState23;
                        objRememberedValue13 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return UserManagementScreenKt.m883x4b153f16(appRole, mutableState16);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue13);
                    } else {
                        mutableState16 = mutableState23;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    i3 = length;
                    i4 = i6;
                    str = str3;
                    mutableState12 = mutableState;
                    str2 = str4;
                    Composer composer3 = composer2;
                    mutableState13 = mutableState24;
                    mutableState14 = mutableState16;
                    mutableState15 = mutableState2;
                    ButtonKt.Button((Function0) objRememberedValue13, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1668716155, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$41(appRole, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composer2, 54), composer3, 805306368, 510);
                    composer2 = composer3;
                    composer2.endReplaceGroup();
                    c = '6';
                } else {
                    str = str3;
                    mutableState12 = mutableState;
                    mutableState13 = mutableState24;
                    str2 = str4;
                    i3 = length;
                    i4 = i6;
                    mutableState14 = mutableState23;
                    mutableState15 = mutableState2;
                    composer2.startReplaceGroup(-190174364);
                    ComposerKt.sourceInformation(composer2, "40@3436L15,40@3453L20,40@3411L62");
                    ComposerKt.sourceInformationMarkerStart(composer2, -190173611, str);
                    boolean zChanged2 = composer2.changed(appRole.ordinal());
                    Object objRememberedValue14 = composer2.rememberedValue();
                    if (zChanged2 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue14 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return UserManagementScreenKt.m884x9c35771(appRole, mutableState14);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue14);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Composer composer4 = composer2;
                    c = '6';
                    ButtonKt.OutlinedButton((Function0) objRememberedValue14, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(996989136, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$44(appRole, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composer2, 54), composer4, 805306368, 510);
                    composer2 = composer4;
                    composer2.endReplaceGroup();
                }
                i6 = i4 + 1;
                str3 = str;
                length = i3;
                appRoleArrValues = appRoleArrValues;
                mutableState24 = mutableState13;
                mutableState = mutableState12;
                mutableState2 = mutableState15;
                mutableState3 = mutableState3;
                c2 = c;
                str7 = str7;
                str6 = str6;
                str4 = str2;
                str5 = str5;
                mutableState23 = mutableState14;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str3;
            final MutableState mutableState25 = mutableState;
            final MutableState mutableState26 = mutableState3;
            final MutableState mutableState27 = mutableState24;
            String str11 = str4;
            char c3 = c2;
            String str12 = str5;
            final MutableState mutableState28 = mutableState23;
            final MutableState mutableState29 = mutableState2;
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, str11);
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceBetween2, Alignment.INSTANCE.getTop(), composer2, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str12);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default5);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str8);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor4);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl4 = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -407840262, str9);
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, -1193802965, "C41@3578L18,41@3613L15,41@3598L31:UserManagementScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("الحساب نشط", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 6, 0, 131070);
            boolean zUserManagementScreen$lambda$27 = UserManagementScreen$lambda$27(mutableState22);
            ComposerKt.sourceInformationMarkerStart(composer2, 1208417300, str10);
            Object objRememberedValue15 = composer2.rememberedValue();
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                mutableState4 = mutableState22;
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$49$lambda$48$lambda$47(mutableState4, ((Boolean) obj3).booleanValue());
                    }
                };
                composer2.updateRememberedValue(objRememberedValue15);
            } else {
                mutableState4 = mutableState22;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Composer composer5 = composer2;
            final MutableState mutableState30 = mutableState4;
            SwitchKt.Switch(zUserManagementScreen$lambda$27, (Function1) objRememberedValue15, null, null, false, null, null, composer5, 48, 124);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            composer5.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            boolean z2 = (StringsKt.isBlank(UserManagementScreen$lambda$15(mutableState25)) || StringsKt.isBlank(UserManagementScreen$lambda$18(mutableState29)) || (UserManagementScreen$lambda$12(mutableState27) == null && UserManagementScreen$lambda$21(mutableState26).length() < 6)) ? false : true;
            Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer5, -686231969, str10);
            boolean zChangedInstance4 = composer5.changedInstance(viewModel);
            Object objRememberedValue16 = composer5.rememberedValue();
            if (zChangedInstance4 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$52$lambda$51(viewModel, mutableState27, mutableState25, mutableState26, mutableState29, mutableState28, mutableState30);
                    }
                };
                mutableState5 = mutableState27;
                mutableState6 = mutableState25;
                mutableState7 = mutableState29;
                mutableState8 = mutableState28;
                composer5.updateRememberedValue(function0);
                objRememberedValue16 = function0;
            } else {
                mutableState5 = mutableState27;
                mutableState6 = mutableState25;
                mutableState7 = mutableState29;
                mutableState8 = mutableState28;
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            final MutableState mutableState31 = mutableState5;
            ButtonKt.Button((Function0) objRememberedValue16, modifierFillMaxWidth$default6, z2, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(564954897, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$53(mutableState5, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, composer5, c3), composer5, 805306416, 504);
            if (UserManagementScreen$lambda$12(mutableState31) != null) {
                composer5.startReplaceGroup(-686220697);
                ComposerKt.sourceInformation(composer5, "46@4141L14,46@4116L102");
                ComposerKt.sourceInformationMarkerStart(composer5, -686219985, str10);
                Object objRememberedValue17 = composer5.rememberedValue();
                if (objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                    final MutableState mutableState32 = mutableState6;
                    final MutableState mutableState33 = mutableState8;
                    objRememberedValue17 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda19
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$55$lambda$54(mutableState31, mutableState32, mutableState7, mutableState26, mutableState33, mutableState30);
                        }
                    };
                    mutableState11 = mutableState31;
                    mutableState9 = mutableState32;
                    mutableState10 = mutableState33;
                    composer5.updateRememberedValue(objRememberedValue17);
                } else {
                    mutableState9 = mutableState6;
                    mutableState10 = mutableState8;
                    mutableState11 = mutableState31;
                }
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ButtonKt.OutlinedButton((Function0) objRememberedValue17, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$UserManagementScreenKt.INSTANCE.m7710getLambda$1205119340$app(), composer5, 805306422, 508);
            } else {
                mutableState9 = mutableState6;
                mutableState10 = mutableState8;
                mutableState7 = mutableState7;
                mutableState11 = mutableState31;
                composer5.startReplaceGroup(197907649);
            }
            composer5.endReplaceGroup();
            TextKt.m3342Text4IGK_g("الحسابات", PaddingKt.m1662paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, C1786Dp.m7249constructorimpl(10.0f), 0.0f, 0.0f, 13, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getTitleLarge(), composer5, 54, 0, 65532);
            Modifier modifierWeight$default = ColumnScope.weight$default(columnScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer5, -686211519, str10);
            boolean zChanged3 = composer5.changed(r44) | composer5.changedInstance(viewModel);
            Object objRememberedValue18 = composer5.rememberedValue();
            if (zChanged3 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                final MutableState mutableState34 = mutableState10;
                final MutableState mutableState35 = mutableState11;
                final MutableState mutableState36 = mutableState9;
                obj2 = null;
                final MutableState mutableState37 = mutableState7;
                Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$59$lambda$58(stateCollectAsState, viewModel, mutableState35, mutableState36, mutableState37, mutableState26, mutableState34, mutableState30, (LazyListScope) obj3);
                    }
                };
                composer5.updateRememberedValue(function1);
                objRememberedValue18 = function1;
            } else {
                obj2 = null;
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            LazyDslKt.LazyColumn(modifierWeight$default, null, null, false, null, null, null, false, (Function1) objRememberedValue18, composer5, 0, 254);
            TextKt.m3342Text4IGK_g("سجل العمليات", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer5, MaterialTheme.$stable).getTitleLarge(), composer5, 6, 0, 65534);
            Modifier modifierFillMaxWidth$default7 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, obj2);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composer5, 693286680, str11);
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composer5, 6);
            ComposerKt.sourceInformationMarkerStart(composer5, -1323940314, str12);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer5.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer5, modifierFillMaxWidth$default7);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer5, -692256719, str8);
            if (!(composer5.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer5.startReusableNode();
            if (composer5.getInserting()) {
                composer5.createNode(constructor5);
            } else {
                composer5.useNode();
            }
            Composer composerM4301constructorimpl5 = Updater.m4301constructorimpl(composer5);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer5, -407840262, str9);
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer5, 1832516014, "C59@5364L39,59@5339L115,60@5492L40,60@5467L115,61@5620L39,61@5595L113:UserManagementScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer5, 1444587211, str10);
            boolean zChangedInstance5 = composer5.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
            Object objRememberedValue19 = composer5.rememberedValue();
            if (zChangedInstance5 || objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue19 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$66$lambda$61$lambda$60(managedActivityResultLauncherRememberLauncherForActivityResult);
                    }
                };
                composer5.updateRememberedValue(objRememberedValue19);
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            masrofViewModel = viewModel;
            ButtonKt.OutlinedButton((Function0) objRememberedValue19, RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$UserManagementScreenKt.INSTANCE.getLambda$21227282$app(), composer5, 805306368, 508);
            ComposerKt.sourceInformationMarkerStart(composer5, 1444591308, str10);
            boolean zChangedInstance6 = composer5.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult2);
            Object objRememberedValue20 = composer5.rememberedValue();
            if (zChangedInstance6 || objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue20 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$66$lambda$63$lambda$62(managedActivityResultLauncherRememberLauncherForActivityResult2);
                    }
                };
                composer5.updateRememberedValue(objRememberedValue20);
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ButtonKt.OutlinedButton((Function0) objRememberedValue20, RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$UserManagementScreenKt.INSTANCE.m7715getLambda$641925381$app(), composer5, 805306368, 508);
            ComposerKt.sourceInformationMarkerStart(composer5, 1444595403, str10);
            boolean zChangedInstance7 = composer5.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult3);
            Object objRememberedValue21 = composer5.rememberedValue();
            if (zChangedInstance7 || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue21 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$66$lambda$65$lambda$64(managedActivityResultLauncherRememberLauncherForActivityResult3);
                    }
                };
                composer5.updateRememberedValue(objRememberedValue21);
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ButtonKt.OutlinedButton((Function0) objRememberedValue21, RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$UserManagementScreenKt.INSTANCE.m7712getLambda$196388070$app(), composer5, 805306368, 508);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            composer5.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            Modifier modifierM1691heightInVpY3zN4$default = SizeKt.m1691heightInVpY3zN4$default(Modifier.INSTANCE, 0.0f, C1786Dp.m7249constructorimpl(180.0f), 1, obj2);
            ComposerKt.sourceInformationMarkerStart(composer5, -686167264, str10);
            boolean zChanged4 = composer5.changed(stateCollectAsState2);
            Object objRememberedValue22 = composer5.rememberedValue();
            if (zChanged4 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$70$lambda$69(stateCollectAsState2, (LazyListScope) obj3);
                    }
                };
                composer5.updateRememberedValue(objRememberedValue22);
            }
            ComposerKt.sourceInformationMarkerEnd(composer5);
            LazyDslKt.LazyColumn(modifierM1691heightInVpY3zN4$default, null, null, false, null, null, null, false, (Function1) objRememberedValue22, composer5, 6, 254);
            composerStartRestartGroup = composer5;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UserManagementScreenKt.UserManagementScreen$lambda$72(masrofViewModel, onBack, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    static final Unit UserManagementScreen$lambda$4$lambda$3(Context context, MasrofViewModel masrofViewModel, State state, Uri uri) throws FileNotFoundException {
        if (uri != null) {
            AuditExporter.INSTANCE.exportCsv(context, uri, UserManagementScreen$lambda$1(state));
            masrofViewModel.recordAudit("EXPORT_AUDIT", "CSV/Excel");
        }
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$7$lambda$6(Context context, MasrofViewModel masrofViewModel, State state, Uri uri) throws FileNotFoundException {
        if (uri != null) {
            AuditExporter.INSTANCE.exportWord(context, uri, UserManagementScreen$lambda$1(state));
            masrofViewModel.recordAudit("EXPORT_AUDIT", "Word");
        }
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$10$lambda$9(Context context, MasrofViewModel masrofViewModel, State state, Uri uri) throws FileNotFoundException {
        if (uri != null) {
            AuditExporter.INSTANCE.exportPdf(context, uri, UserManagementScreen$lambda$1(state));
            masrofViewModel.recordAudit("EXPORT_AUDIT", "PDF");
        }
        return Unit.INSTANCE;
    }

    private static final UserEntity UserManagementScreen$lambda$12(MutableState<UserEntity> mutableState) {
        return mutableState.getValue();
    }

    private static final String UserManagementScreen$lambda$15(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UserManagementScreen$lambda$18(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String UserManagementScreen$lambda$21(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final AppRole UserManagementScreen$lambda$24(MutableState<AppRole> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean UserManagementScreen$lambda$27(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void UserManagementScreen$lambda$28(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:25:0x004c  */
    public static final void UserManagementScreen$load(MutableState<UserEntity> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<AppRole> mutableState5, MutableState<Boolean> mutableState6, UserEntity userEntity) {
        Object objM7781constructorimpl;
        AppRole appRole;
        mutableState.setValue(userEntity);
        String username = userEntity != null ? userEntity.getUsername() : null;
        if (username == null) {
            username = "";
        }
        mutableState2.setValue(username);
        String fullName = userEntity != null ? userEntity.getFullName() : null;
        if (fullName == null) {
            fullName = "";
        }
        mutableState3.setValue(fullName);
        mutableState4.setValue("");
        if (userEntity != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(AppRole.valueOf(userEntity.getRole()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            AppRole appRole2 = AppRole.USER;
            if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                objM7781constructorimpl = appRole2;
            }
            appRole = (AppRole) objM7781constructorimpl;
            if (appRole == null) {
                appRole = AppRole.USER;
            }
        } else {
            appRole = AppRole.USER;
        }
        mutableState5.setValue(appRole);
        UserManagementScreen$lambda$28(mutableState6, userEntity != null ? userEntity.getActive() : true);
    }

    static final Unit UserManagementScreen$lambda$71$lambda$33$lambda$32(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$35$lambda$34(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$37$lambda$36(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$38(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C39@3100L73:UserManagementScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1492272047, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UserManagementScreen.<anonymous>.<anonymous> (UserManagementScreen.kt:39)");
            }
            TextKt.m3342Text4IGK_g(UserManagementScreen$lambda$12(mutableState) == null ? "كلمة المرور" : "كلمة مرور جديدة (اختياري)", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$40$lambda$39 */
    static final Unit m883x4b153f16(AppRole appRole, MutableState mutableState) {
        mutableState.setValue(appRole);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$41(AppRole appRole, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C40@3387L16:UserManagementScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1668716155, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UserManagementScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (UserManagementScreen.kt:40)");
            }
            TextKt.m3342Text4IGK_g(appRole.getTitle(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$43$lambda$42 */
    static final Unit m884x9c35771(AppRole appRole, MutableState mutableState) {
        mutableState.setValue(appRole);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$46$lambda$45$lambda$44(AppRole appRole, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C40@3455L16:UserManagementScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(996989136, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UserManagementScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (UserManagementScreen.kt:40)");
            }
            TextKt.m3342Text4IGK_g(appRole.getTitle(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$49$lambda$48$lambda$47(MutableState mutableState, boolean z) {
        UserManagementScreen$lambda$28(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$52$lambda$51(MasrofViewModel masrofViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6) {
        if (UserManagementScreen$lambda$12(mutableState) == null) {
            masrofViewModel.createUser(UserManagementScreen$lambda$15(mutableState2), UserManagementScreen$lambda$21(mutableState3), UserManagementScreen$lambda$18(mutableState4), UserManagementScreen$lambda$24(mutableState5).name());
        } else {
            UserEntity userEntityUserManagementScreen$lambda$12 = UserManagementScreen$lambda$12(mutableState);
            Intrinsics.checkNotNull(userEntityUserManagementScreen$lambda$12);
            String strUserManagementScreen$lambda$21 = UserManagementScreen$lambda$21(mutableState3);
            if (StringsKt.isBlank(strUserManagementScreen$lambda$21)) {
                strUserManagementScreen$lambda$21 = null;
            }
            masrofViewModel.updateUser(userEntityUserManagementScreen$lambda$12, strUserManagementScreen$lambda$21, UserManagementScreen$lambda$18(mutableState4), UserManagementScreen$lambda$24(mutableState5).name(), UserManagementScreen$lambda$27(mutableState6));
        }
        UserManagementScreen$load(mutableState, mutableState2, mutableState4, mutableState3, mutableState5, mutableState6, null);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$53(MutableState mutableState, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C45@4022L62:UserManagementScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(564954897, i, -1, "com.mohammedalhzmi.masrofmanager.ui.UserManagementScreen.<anonymous>.<anonymous> (UserManagementScreen.kt:45)");
            }
            TextKt.m3342Text4IGK_g(UserManagementScreen$lambda$12(mutableState) == null ? "إنشاء الحساب" : "حفظ التعديلات", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$55$lambda$54(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6) {
        UserManagementScreen$load(mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, null);
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$59$lambda$58(State state, final MasrofViewModel masrofViewModel, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List<UserEntity> listUserManagementScreen$lambda$0 = UserManagementScreen$lambda$0(state);
        final Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$59$lambda$58$lambda$56((UserEntity) obj);
            }
        };
        final C4090xf011512a c4090xf011512a = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$59$lambda$58$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(UserEntity userEntity) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((UserEntity) obj);
            }
        };
        LazyColumn.items(listUserManagementScreen$lambda$0.size(), new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$59$lambda$58$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function1.invoke(listUserManagementScreen$lambda$0.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$59$lambda$58$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return c4090xf011512a.invoke(listUserManagementScreen$lambda$0.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$59$lambda$58$$inlined$items$default$4
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
                final UserEntity userEntity = (UserEntity) listUserManagementScreen$lambda$0.get(i);
                composer.startReplaceGroup(-1099521548);
                ComposerKt.sourceInformation(composer, "C*50@4518L608,50@4452L674:UserManagementScreen.kt#ska5t9");
                Modifier modifierM1660paddingVpY3zN4$default = PaddingKt.m1660paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, C1786Dp.m7249constructorimpl(3.0f), 1, null);
                final MasrofViewModel masrofViewModel2 = masrofViewModel;
                final MutableState mutableState7 = mutableState;
                final MutableState mutableState8 = mutableState2;
                final MutableState mutableState9 = mutableState3;
                final MutableState mutableState10 = mutableState4;
                final MutableState mutableState11 = mutableState5;
                final MutableState mutableState12 = mutableState6;
                CardKt.Card(modifierM1660paddingVpY3zN4$default, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(159544511, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$1$11$1$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer2, Integer num) {
                        invoke(columnScope, composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
                        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v4 java.lang.Object
                        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                        	at jadx.core.dex.visitors.ModVisitor.anonymousCallArgMod(ModVisitor.java:535)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at jadx.core.dex.visitors.ModVisitor.processAnonymousConstructor(ModVisitor.java:528)
                        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:111)
                        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
                        */
                    public final void invoke(androidx.compose.foundation.layout.ColumnScope r36, androidx.compose.runtime.Composer r37, int r38) {
                        /*
                            Method dump skipped, instruction units count: 937
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.mohammedalhzmi.masrofmanager.p010ui.UserManagementScreenKt$UserManagementScreen$1$11$1$2$1.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
                    }
                }, composer, 54), composer, 196614, 30);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    static final Object UserManagementScreen$lambda$71$lambda$59$lambda$58$lambda$56(UserEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    static final Unit UserManagementScreen$lambda$71$lambda$66$lambda$61$lambda$60(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("audit-log.csv");
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$66$lambda$63$lambda$62(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("audit-log.doc");
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$66$lambda$65$lambda$64(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("audit-log.pdf");
        return Unit.INSTANCE;
    }

    static final Unit UserManagementScreen$lambda$71$lambda$70$lambda$69(State state, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        final List listTake = CollectionsKt.take(UserManagementScreen$lambda$1(state), 20);
        final Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserManagementScreenKt.UserManagementScreen$lambda$71$lambda$70$lambda$69$lambda$67((AuditLogEntity) obj);
            }
        };
        final C4094x3c5260b5 c4094x3c5260b5 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$70$lambda$69$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(AuditLogEntity auditLogEntity) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((AuditLogEntity) obj);
            }
        };
        LazyColumn.items(listTake.size(), new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$70$lambda$69$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function1.invoke(listTake.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$70$lambda$69$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return c4094x3c5260b5.invoke(listTake.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.UserManagementScreenKt$UserManagementScreen$lambda$71$lambda$70$lambda$69$$inlined$items$default$4
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
                AuditLogEntity auditLogEntity = (AuditLogEntity) listTake.get(i);
                composer.startReplaceGroup(-308048223);
                ComposerKt.sourceInformation(composer, "C*63@5934L10,63@5831L170:UserManagementScreen.kt#ska5t9");
                TextKt.m3342Text4IGK_g(UserManagementScreenKt.date(auditLogEntity.getTimestamp()) + " — " + auditLogEntity.getUsername() + ": " + auditLogEntity.getAction() + " — " + auditLogEntity.getDetails(), PaddingKt.m1660paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, C1786Dp.m7249constructorimpl(2.0f), 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 48, 0, 65532);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    static final Object UserManagementScreen$lambda$71$lambda$70$lambda$69$lambda$67(AuditLogEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String date(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(j));
    }

    private static final List<UserEntity> UserManagementScreen$lambda$0(State<? extends List<UserEntity>> state) {
        return state.getValue();
    }

    private static final List<AuditLogEntity> UserManagementScreen$lambda$1(State<? extends List<AuditLogEntity>> state) {
        return state.getValue();
    }
}
