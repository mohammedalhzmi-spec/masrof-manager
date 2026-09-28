package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.net.Uri;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BorderStroke;
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
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.CheckboxKt;
import androidx.compose.material3.ChipColors;
import androidx.compose.material3.ChipElevation;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.graphics.Color;
import androidx.compose.p000ui.graphics.ColorKt;
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
import androidx.core.app.NotificationCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.google.android.gms.actions.SearchIntents;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager;
import com.mohammedalhzmi.masrofmanager.util.AppPermission;
import com.mohammedalhzmi.masrofmanager.util.AppRole;
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter;
import com.mohammedalhzmi.masrofmanager.util.RolePreferences;
import io.ktor.http.LinkHeader;
import io.ktor.sse.ServerSentEventKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.GroupingKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DashboardScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\f\u001a\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001a\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0012\u001a\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0016²\u0006\u0010\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u008a\u0084\u0002²\u0006\u0010\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u008a\u0084\u0002²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020 X\u008a\u008e\u0002²\u0006\f\u0010!\u001a\u0004\u0018\u00010\tX\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\tX\u008a\u008e\u0002"}, m914d2 = {"DashboardScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "onAddDocument", "Lkotlin/Function0;", "onCreateBook", "onPrint", "Lkotlin/Function1;", "", "onEdit", "onSettings", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "statusTitle", NotificationCompat.CATEGORY_STATUS, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "statusColor", "Landroidx/compose/ui/graphics/Color;", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;)J", "documentTitle", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "app", "documents", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "archivedDocuments", "selectedIds", "", "", SearchIntents.EXTRA_QUERY, "showArchive", "", "selectedTag", "customTag"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class DashboardScreenKt {

    /* JADX INFO: compiled from: DashboardScreen.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DocumentStatus.values().length];
            try {
                iArr[DocumentStatus.DRAFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentStatus.SUBMITTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentStatus.APPROVED_FINANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentStatus.APPROVED_BRANCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DocumentStatus.APPROVED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DocumentStatus.PAID.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DocumentStatus.RECEIVED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[DocumentStatus.CANCELLED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocumentType.values().length];
            try {
                iArr2[DocumentType.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[DocumentType.ORDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit DashboardScreen$lambda$109(MasrofViewModel masrofViewModel, Function0 function0, Function0 function1, Function1 function2, Function1 function3, Function0 function4, int i, Composer composer, int i2) {
        DashboardScreen(masrofViewModel, function0, function1, function2, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

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
    public static final void DashboardScreen(final MasrofViewModel masrofViewModel, final Function0<Unit> onAddDocument, final Function0<Unit> onCreateBook, Function1<? super String, Unit> onPrint, final Function1<? super String, Unit> onEdit, final Function0<Unit> onSettings, Composer composer, final int i) {
        final MutableState mutableState;
        final MutableState mutableState2;
        final MutableState mutableState3;
        final MutableState mutableState4;
        String str;
        Object obj;
        final Context context;
        final Function1<? super String, Unit> function1;
        Iterator it;
        MutableState mutableState5;
        final MasrofViewModel viewModel = masrofViewModel;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onAddDocument, "onAddDocument");
        Intrinsics.checkNotNullParameter(onCreateBook, "onCreateBook");
        Intrinsics.checkNotNullParameter(onPrint, "onPrint");
        Intrinsics.checkNotNullParameter(onEdit, "onEdit");
        Intrinsics.checkNotNullParameter(onSettings, "onSettings");
        Composer composerStartRestartGroup = composer.startRestartGroup(504888545);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DashboardScreen)P(5!2,3)25@1287L16,26@1361L16,27@1401L42,28@1461L31,29@1516L34,30@1574L42,31@1648L7,38@2189L114,38@2090L213,39@2403L117,39@2329L191,40@2642L121,40@2549L214,41@2866L124,41@2792L198,42@3051L21,42@2995L9228:DashboardScreen.kt#ska5t9");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onAddDocument) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onCreateBook) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onPrint) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onEdit) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onSettings) ? 131072 : 65536;
        }
        if ((74899 & i2) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            function1 = onPrint;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(504888545, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen (DashboardScreen.kt:24)");
            }
            final State stateCollectAsState = SnapshotStateKt.collectAsState(viewModel.getAllDocuments(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(viewModel.getArchivedDocuments(), null, composerStartRestartGroup, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133401461, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SetsKt.emptySet(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState6 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133399552, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState7 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133397789, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue3 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState8 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133395925, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState9 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context2 = (Context) objConsume;
            final AppRole appRoleCurrentRole = RolePreferences.INSTANCE.currentRole(context2);
            final boolean zCan = RolePreferences.INSTANCE.can(context2, AppPermission.SETTINGS);
            boolean zCan2 = RolePreferences.INSTANCE.can(context2, AppPermission.BACKUP);
            final boolean zCan3 = RolePreferences.INSTANCE.can(context2, AppPermission.EDIT);
            final boolean zCan4 = RolePreferences.INSTANCE.can(context2, AppPermission.DELETE);
            final boolean zCan5 = RolePreferences.INSTANCE.can(context2, AppPermission.APPROVE);
            ActivityResultContracts.CreateDocument createDocument = new ActivityResultContracts.CreateDocument("application/x-sqlite3");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133376173, "CC(remember):DashboardScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context2);
            int i3 = i2;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return DashboardScreenKt.DashboardScreen$lambda$16$lambda$15(viewModel, context2, (Uri) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(createDocument, (Function1) objRememberedValue5, composerStartRestartGroup, 0);
            ActivityResultContracts.OpenDocument openDocument = new ActivityResultContracts.OpenDocument();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133369322, "CC(remember):DashboardScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context2);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return DashboardScreenKt.DashboardScreen$lambda$19$lambda$18(viewModel, context2, (Uri) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(openDocument, (Function1) objRememberedValue6, composerStartRestartGroup, 0);
            ActivityResultContracts.CreateDocument createDocument2 = new ActivityResultContracts.CreateDocument("application/zip");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133361670, "CC(remember):DashboardScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context2);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return DashboardScreenKt.DashboardScreen$lambda$22$lambda$21(viewModel, context2, (Uri) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult3 = ActivityResultRegistryKt.rememberLauncherForActivityResult(createDocument2, (Function1) objRememberedValue7, composerStartRestartGroup, 0);
            ActivityResultContracts.OpenDocument openDocument2 = new ActivityResultContracts.OpenDocument();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -133354499, "CC(remember):DashboardScreen.kt#9igjgp");
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context2);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return DashboardScreenKt.DashboardScreen$lambda$25$lambda$24(viewModel, context2, (Uri) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult4 = ActivityResultRegistryKt.rememberLauncherForActivityResult(openDocument2, (Function1) objRememberedValue8, composerStartRestartGroup, 0);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 189952187, "C43@3163L75,43@3309L950,43@3100L1159,49@4268L41,50@4318L96,51@4423L111,52@4543L40,53@4641L14,53@4592L187,54@4788L435,61@5588L10,61@5542L68,62@5619L478,66@6123L31,67@6216L145,67@6163L300,68@6472L191,69@6672L40,81@7862L41,88@8439L3079,114@12176L41:DashboardScreen.kt#ska5t9");
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1941RoundedCornerShape0680j_4(C1786Dp.m7249constructorimpl(14.0f)), CardDefaults.INSTANCE.m2477cardColorsro_MJ88(ColorKt.Color(4279384925L), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1702810105, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return DashboardScreenKt.DashboardScreen$lambda$108$lambda$28(zCan, onSettings, context2, appRoleCurrentRole, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 24);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            int i4 = 1;
            int i5 = i3;
            ButtonKt.Button(onAddDocument, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$785670203$app(), composerStartRestartGroup, ((i3 >> 3) & 14) | 805306416, 508);
            ButtonKt.OutlinedButton(onCreateBook, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$1148063225$app(), composerStartRestartGroup, ((i5 >> 6) & 14) | 805306416, 508);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(8.0f)), composerStartRestartGroup, 6);
            String strDashboardScreen$lambda$6 = DashboardScreen$lambda$6(mutableState7);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933494951, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState7;
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$30$lambda$29(mutableState, (String) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            } else {
                mutableState = mutableState7;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strDashboardScreen$lambda$6, (Function1<? super String, Unit>) objRememberedValue9, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$2018968657$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573296, 12582912, 0, 8257464);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -282148035, "C55@4940L49,55@4999L49,55@4894L155,56@5107L48,56@5165L47,56@5062L151:DashboardScreen.kt#ska5t9");
            boolean z = !DashboardScreen$lambda$9(mutableState8);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -424742344, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                mutableState2 = mutableState8;
                mutableState3 = mutableState6;
                objRememberedValue10 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$37$lambda$32$lambda$31(mutableState2, mutableState3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            } else {
                mutableState2 = mutableState8;
                mutableState3 = mutableState6;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ChipKt.FilterChip(z, (Function0) objRememberedValue10, ComposableLambdaKt.rememberComposableLambda(-1026018124, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DashboardScreenKt.DashboardScreen$lambda$108$lambda$37$lambda$33(stateCollectAsState, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), null, false, null, null, null, null, null, null, null, composerStartRestartGroup, 432, 0, 4088);
            boolean zDashboardScreen$lambda$9 = DashboardScreen$lambda$9(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -424737001, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$37$lambda$35$lambda$34(mutableState2, mutableState3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ChipKt.FilterChip(zDashboardScreen$lambda$9, (Function0) objRememberedValue11, ComposableLambdaKt.rememberComposableLambda(-1039140515, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return DashboardScreenKt.DashboardScreen$lambda$108$lambda$37$lambda$36(stateCollectAsState2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), null, false, null, null, null, null, null, null, null, composerStartRestartGroup, 432, 0, 4088);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final List<Document> listDashboardScreen$lambda$1 = DashboardScreen$lambda$9(mutableState2) ? DashboardScreen$lambda$1(stateCollectAsState2) : DashboardScreen$lambda$0(stateCollectAsState);
            ArrayList arrayList = new ArrayList();
            Iterator it2 = listDashboardScreen$lambda$1.iterator();
            while (it2.hasNext()) {
                String tags = ((Document) it2.next()).getTags();
                String[] strArr = new String[i4];
                strArr[0] = ",";
                List listSplit$default = StringsKt.split$default((CharSequence) tags, strArr, false, 0, 6, (Object) null);
                int i6 = i4;
                Iterator it3 = it2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
                Iterator it4 = listSplit$default.iterator();
                while (it4.hasNext()) {
                    arrayList2.add(StringsKt.trim((CharSequence) it4.next()).toString());
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : arrayList2) {
                    if (!StringsKt.isBlank((String) obj2)) {
                        arrayList3.add(obj2);
                    }
                }
                CollectionsKt.addAll(arrayList, arrayList3);
                it2 = it3;
                i4 = i6;
            }
            int i7 = i4;
            final ArrayList arrayList4 = arrayList;
            Map mapEachCount = GroupingKt.eachCount(new Grouping<String, String>() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$DashboardScreen$lambda$108$$inlined$groupingBy$1
                @Override // kotlin.collections.Grouping
                public Iterator<String> sourceIterator() {
                    return arrayList4.iterator();
                }

                @Override // kotlin.collections.Grouping
                public String keyOf(String element) {
                    return element;
                }
            });
            String[] strArr2 = new String[5];
            strArr2[0] = "كهرباء";
            strArr2[i7] = "صيانة";
            strArr2[2] = "رواتب";
            strArr2[3] = "وقود ومحروقات";
            strArr2[4] = "قطع غيار";
            List<String> listListOf = CollectionsKt.listOf((Object[]) strArr2);
            TextKt.m3342Text4IGK_g("الوسوم والتصنيف", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleSmall(), composerStartRestartGroup, 6, 0, 65534);
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i7, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default3);
            MutableState mutableState10 = mutableState;
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 85133097, "C63@5778L22,63@5810L40,63@5725L126:DashboardScreen.kt#ska5t9");
            boolean z2 = DashboardScreen$lambda$12(mutableState9) == null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1249673574, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                mutableState4 = mutableState9;
                objRememberedValue12 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$49$lambda$42$lambda$41(mutableState4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            } else {
                mutableState4 = mutableState9;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ChipKt.FilterChip(z2, (Function0) objRememberedValue12, ComposableLambdaKt.rememberComposableLambda(1125442077, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return DashboardScreenKt.DashboardScreen$lambda$108$lambda$49$lambda$43(listDashboardScreen$lambda$1, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, composerStartRestartGroup, 54), null, false, null, null, null, null, null, null, null, composerStartRestartGroup, 432, 0, 4088);
            composerStartRestartGroup.startReplaceGroup(1249678139);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*64@5994L55,64@6059L25,64@5942L143");
            for (Pair pair : CollectionsKt.sortedWith(MapsKt.toList(mapEachCount), new Comparator() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$DashboardScreen$lambda$108$lambda$49$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) ((Pair) t2).getSecond(), (Integer) ((Pair) t).getSecond());
                }
            })) {
                final String str2 = (String) pair.component1();
                final int iIntValue = ((Number) pair.component2()).intValue();
                boolean zAreEqual = Intrinsics.areEqual(DashboardScreen$lambda$12(mutableState4), str2);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -141287890, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(str2);
                Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue13 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m810xb18ceb70(str2, mutableState4);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ChipKt.FilterChip(zAreEqual, (Function0) objRememberedValue13, ComposableLambdaKt.rememberComposableLambda(-1725427990, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$49$lambda$48$lambda$47(str2, iIntValue, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composerStartRestartGroup, 54), null, false, null, null, null, null, null, null, null, composerStartRestartGroup, 384, 0, 4088);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933447510, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                str = "";
                obj = null;
                objRememberedValue14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            } else {
                str = "";
                obj = null;
            }
            final MutableState mutableState11 = (MutableState) objRememberedValue14;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strDashboardScreen$lambda$108$lambda$51 = DashboardScreen$lambda$108$lambda$51(mutableState11);
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, obj);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933444420, "CC(remember):DashboardScreen.kt#9igjgp");
            Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue15 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$54$lambda$53(mutableState4, mutableState11, (String) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            OutlinedTextFieldKt.OutlinedTextField(strDashboardScreen$lambda$108$lambda$51, (Function1<? super String, Unit>) objRememberedValue15, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$DashboardScreenKt.INSTANCE.m7672getLambda$1809957254$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composerStartRestartGroup, 1573296, 12582912, 0, 8257464);
            Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(5.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default5);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            String str3 = str;
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
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1997438443, "C:DashboardScreen.kt#ska5t9");
            composerStartRestartGroup.startReplaceGroup(212661476);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*68@6614L21,68@6645L13,68@6593L66");
            for (final String str4 : listListOf) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -552715116, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChanged2 = composerStartRestartGroup.changed(str4);
                Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue16 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m811xc7f48b6c(str4, mutableState4);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ChipKt.AssistChip((Function0<Unit>) objRememberedValue16, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1868105694, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$59$lambda$58$lambda$57(str4, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, composerStartRestartGroup, 54), (Modifier) null, false, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Shape) null, (ChipColors) null, (ChipElevation) null, (BorderStroke) null, (MutableInteractionSource) null, composerStartRestartGroup, 48, 0, 2044);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(8.0f)), composerStartRestartGroup, 6);
            if (zCan2) {
                composerStartRestartGroup.startReplaceGroup(193311098);
                ComposerKt.sourceInformation(composerStartRestartGroup, "71@6750L404,75@7167L444,79@7649L108,79@7624L219");
                Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_7 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_7, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default6);
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
                Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                    composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                }
                Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1566301473, "C72@6885L45,72@6860L123,73@7025L59,73@7000L140:DashboardScreen.kt#ska5t9");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2128735271, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
                Object objRememberedValue17 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance5 || objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue17 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$64$lambda$61$lambda$60(managedActivityResultLauncherRememberLauncherForActivityResult);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue17, RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7674getLambda$466015462$app(), composerStartRestartGroup, 805306368, 508);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2128730777, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult2);
                Object objRememberedValue18 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance6 || objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue18 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$64$lambda$63$lambda$62(managedActivityResultLauncherRememberLauncherForActivityResult2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue18, RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7670getLambda$1576399805$app(), composerStartRestartGroup, 805306368, 508);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Modifier modifierFillMaxWidth$default7 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_8 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_8, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default7);
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
                Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                    composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                }
                Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2119085486, "C76@7302L54,76@7277L137,77@7456L84,77@7431L166:DashboardScreen.kt#ska5t9");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 899642123, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult3);
                Object objRememberedValue19 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance7 || objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue19 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$69$lambda$66$lambda$65(managedActivityResultLauncherRememberLauncherForActivityResult3);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue19, RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7671getLambda$1684863741$app(), composerStartRestartGroup, 805306368, 508);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 899647081, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult4);
                Object objRememberedValue20 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance8 || objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue20 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$69$lambda$68$lambda$67(managedActivityResultLauncherRememberLauncherForActivityResult4);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue20, RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$682639340$app(), composerStartRestartGroup, 805306368, 508);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933398601, "CC(remember):DashboardScreen.kt#9igjgp");
                context = context2;
                boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(masrofViewModel);
                Object objRememberedValue21 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance9 || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue21 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$71$lambda$70(context, masrofViewModel);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue21, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7669getLambda$1042454402$app(), composerStartRestartGroup, 805306416, 508);
            } else {
                context = context2;
                composerStartRestartGroup.startReplaceGroup(186593367);
            }
            composerStartRestartGroup.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(12.0f)), composerStartRestartGroup, 6);
            List<Document> listDashboardScreen$lambda$2 = DashboardScreen$lambda$9(mutableState2) ? DashboardScreen$lambda$1(stateCollectAsState2) : DashboardScreen$lambda$0(stateCollectAsState);
            String lowerCase = StringsKt.trim((CharSequence) DashboardScreen$lambda$6(mutableState10)).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            ArrayList arrayList5 = new ArrayList();
            Iterator it5 = listDashboardScreen$lambda$2.iterator();
            while (it5.hasNext()) {
                Object next = it5.next();
                Document document = (Document) next;
                if (DashboardScreen$lambda$12(mutableState4) != null) {
                    it = it5;
                    List listSplit$default2 = StringsKt.split$default((CharSequence) document.getTags(), new String[]{","}, false, 0, 6, (Object) null);
                    ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default2, 10));
                    Iterator it6 = listSplit$default2.iterator();
                    while (it6.hasNext()) {
                        arrayList6.add(StringsKt.trim((CharSequence) it6.next()).toString());
                    }
                    if (!CollectionsKt.contains(arrayList6, DashboardScreen$lambda$12(mutableState4))) {
                        mutableState5 = mutableState4;
                    }
                    it5 = it;
                    mutableState4 = mutableState5;
                } else {
                    it = it5;
                }
                String str5 = lowerCase;
                if (StringsKt.isBlank(str5)) {
                    mutableState5 = mutableState4;
                } else {
                    String[] strArr3 = new String[6];
                    strArr3[0] = document.getDocumentNumber();
                    strArr3[1] = document.getDateHijri();
                    strArr3[2] = document.getDateGregorian();
                    String beneficiaryName = document.getBeneficiaryName();
                    if (beneficiaryName == null) {
                        beneficiaryName = str3;
                    }
                    strArr3[3] = beneficiaryName;
                    strArr3[4] = documentTitle(document.getType());
                    strArr3[5] = document.getTags();
                    List listListOf2 = CollectionsKt.listOf((Object[]) strArr3);
                    if (!(listListOf2 instanceof Collection) || !listListOf2.isEmpty()) {
                        Iterator it7 = listListOf2.iterator();
                        while (true) {
                            if (it7.hasNext()) {
                                String lowerCase2 = ((String) it7.next()).toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                mutableState5 = mutableState4;
                                if (!StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) str5, false, 2, (Object) null)) {
                                    mutableState4 = mutableState5;
                                }
                            }
                        }
                    }
                    mutableState5 = mutableState4;
                    it5 = it;
                    mutableState4 = mutableState5;
                }
                arrayList5.add(next);
                it5 = it;
                mutableState4 = mutableState5;
            }
            ArrayList arrayList7 = arrayList5;
            Modifier modifierFillMaxWidth$default8 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            boolean z3 = false;
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default8);
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
            Updater.m4308setimpl(composerM4301constructorimpl7, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                composerM4301constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                composerM4301constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
            }
            Updater.m4308setimpl(composerM4301constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 462658666, "C:DashboardScreen.kt#ska5t9");
            composerStartRestartGroup.startReplaceGroup(14925000);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*90@8612L2882,90@8546L2948");
            Iterator it8 = arrayList7.iterator();
            while (it8.hasNext()) {
                final Document document2 = (Document) it8.next();
                boolean z4 = z3;
                final MutableState mutableState12 = mutableState3;
                final MutableState mutableState13 = mutableState2;
                CardKt.Card(PaddingKt.m1660paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, C1786Dp.m7249constructorimpl(4.0f), 1, null), null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1314966669, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97(document2, appRoleCurrentRole, masrofViewModel, zCan5, zCan3, onEdit, context, zCan4, mutableState12, mutableState13, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 30);
                it8 = it8;
                i5 = i5;
                arrayList7 = arrayList7;
                mutableState2 = mutableState13;
                mutableState3 = mutableState12;
                z3 = z4;
            }
            boolean z5 = z3;
            final MutableState mutableState14 = mutableState3;
            final MutableState mutableState15 = mutableState2;
            final ArrayList arrayList8 = arrayList7;
            viewModel = masrofViewModel;
            int i8 = i5;
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (DashboardScreen$lambda$3(mutableState14).isEmpty()) {
                function1 = onPrint;
                composerStartRestartGroup.startReplaceGroup(186593367);
            } else {
                composerStartRestartGroup.startReplaceGroup(198077813);
                ComposerKt.sourceInformation(composerStartRestartGroup, "111@11588L119,111@11745L54,111@11571L228");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933272542, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance10 = composerStartRestartGroup.changedInstance(viewModel) | ((i8 & 7168) == 2048 ? true : z5);
                Object objRememberedValue22 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance10 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                    function1 = onPrint;
                    objRememberedValue22 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda17
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$101$lambda$100(viewModel, function1, mutableState14);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
                } else {
                    function1 = onPrint;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.Button((Function0) objRememberedValue22, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1931523415, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return DashboardScreenKt.DashboardScreen$lambda$108$lambda$102(mutableState14, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306416, 508);
                if (DashboardScreen$lambda$3(mutableState14).size() == 1 && zCan4) {
                    composerStartRestartGroup.startReplaceGroup(-1933263908);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "112@11873L167,112@12078L79,112@11852L305");
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1933263374, "CC(remember):DashboardScreen.kt#9igjgp");
                    boolean zChangedInstance11 = composerStartRestartGroup.changedInstance(arrayList8) | composerStartRestartGroup.changedInstance(viewModel);
                    Object objRememberedValue23 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance11 || objRememberedValue23 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue23 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda19
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DashboardScreenKt.DashboardScreen$lambda$108$lambda$106$lambda$105(arrayList8, mutableState14, viewModel, mutableState15);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ButtonKt.TextButton((Function0) objRememberedValue23, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-159155647, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return DashboardScreenKt.DashboardScreen$lambda$108$lambda$107(mutableState15, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306416, 508);
                } else {
                    composerStartRestartGroup.startReplaceGroup(186593367);
                }
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endReplaceGroup();
            SpacerKt.Spacer(SizeKt.m1689height3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(24.0f)), composerStartRestartGroup, 6);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return DashboardScreenKt.DashboardScreen$lambda$109(viewModel, onAddDocument, onCreateBook, function1, onEdit, onSettings, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final Set<Long> DashboardScreen$lambda$3(MutableState<Set<Long>> mutableState) {
        return mutableState.getValue();
    }

    private static final String DashboardScreen$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final void DashboardScreen$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DashboardScreen$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final String DashboardScreen$lambda$12(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit DashboardScreen$lambda$16$lambda$15(MasrofViewModel masrofViewModel, Context context, Uri uri) {
        if (uri != null) {
            masrofViewModel.exportDatabase(context, uri);
            masrofViewModel.recordAudit("EXPORT_DATABASE", "نسخة DB");
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$19$lambda$18(MasrofViewModel masrofViewModel, Context context, Uri uri) {
        if (uri != null) {
            masrofViewModel.importDatabase(context, uri);
            masrofViewModel.recordAudit("IMPORT_DATABASE", "استعادة DB");
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$22$lambda$21(MasrofViewModel masrofViewModel, Context context, Uri uri) {
        if (uri != null) {
            masrofViewModel.exportFullBackup(context, uri);
            masrofViewModel.recordAudit("EXPORT_BACKUP", "نسخة ZIP كاملة");
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$25$lambda$24(MasrofViewModel masrofViewModel, Context context, Uri uri) {
        if (uri != null) {
            masrofViewModel.importFullBackup(context, uri);
            masrofViewModel.recordAudit("IMPORT_BACKUP", "استعادة ZIP كاملة");
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$28(boolean z, Function0 function0, Context context, AppRole appRole, ColumnScope Card, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C44@3323L926:DashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1702810105, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous> (DashboardScreen.kt:44)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(16.0f));
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composer, 54);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1610246729, "C45@3494L559:DashboardScreen.kt#ska5t9");
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -2021914496, "C45@3637L10,45@3535L187,45@3857L10,45@3724L154,45@4029L10,45@3880L171:DashboardScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("نظام المالية والإدارة", (Modifier) null, Color.INSTANCE.m4845getWhite0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getHeadlineSmall(), composer, 196998, 0, 65498);
            TextKt.m3342Text4IGK_g("صندوق النظافة والتحسين م/إب — فرع مديرية الحزم", (Modifier) null, ColorKt.Color(4292667887L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 390, 0, 65530);
            TextKt.m3342Text4IGK_g("المستخدم: " + RolePreferences.INSTANCE.userName(context) + " — " + appRole.getTitle(), (Modifier) null, ColorKt.Color(4291356371L), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelSmall(), composer, 384, 0, 65530);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (z) {
                composer.startReplaceGroup(-779322167);
                ComposerKt.sourceInformation(composer, "46@4087L148");
                IconButtonKt.IconButton(function0, null, false, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7675getLambda$655037411$app(), composer, ProfileVerifier.CompilationStatus.f255xf2722a21, 30);
            } else {
                composer.startReplaceGroup(1606756717);
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

    static final Unit DashboardScreen$lambda$108$lambda$30$lambda$29(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$37$lambda$32$lambda$31(MutableState mutableState, MutableState mutableState2) {
        DashboardScreen$lambda$10(mutableState, false);
        mutableState2.setValue(SetsKt.emptySet());
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$37$lambda$33(State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C55@5001L45:DashboardScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1026018124, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:55)");
            }
            TextKt.m3342Text4IGK_g("المستندات الحالية (" + DashboardScreen$lambda$0(state).size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$37$lambda$35$lambda$34(MutableState mutableState, MutableState mutableState2) {
        DashboardScreen$lambda$10(mutableState, true);
        mutableState2.setValue(SetsKt.emptySet());
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$37$lambda$36(State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C56@5167L43:DashboardScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1039140515, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:56)");
            }
            TextKt.m3342Text4IGK_g("الأرشيف (" + DashboardScreen$lambda$1(state).size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$49$lambda$42$lambda$41(MutableState mutableState) {
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$49$lambda$43(List list, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C63@5812L36:DashboardScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1125442077, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:63)");
            }
            TextKt.m3342Text4IGK_g("الكل (" + list.size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$49$lambda$48$lambda$46$lambda$45 */
    static final Unit m810xb18ceb70(String str, MutableState mutableState) {
        if (Intrinsics.areEqual(DashboardScreen$lambda$12(mutableState), str)) {
            str = null;
        }
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$49$lambda$48$lambda$47(String str, int i, Composer composer, int i2) {
        ComposerKt.sourceInformation(composer, "C64@6061L21:DashboardScreen.kt#ska5t9");
        if ((i2 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1725427990, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:64)");
            }
            TextKt.m3342Text4IGK_g(str + " (" + i + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final String DashboardScreen$lambda$108$lambda$51(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit DashboardScreen$lambda$108$lambda$54$lambda$53(MutableState mutableState, MutableState mutableState2, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (StringsKt.endsWith$default(value, "\n", false, 2, (Object) null)) {
            String string = StringsKt.trim((CharSequence) value).toString();
            if (!StringsKt.isBlank(string)) {
                mutableState.setValue(string);
            }
            mutableState2.setValue("");
        } else {
            mutableState2.setValue(value);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$59$lambda$58$lambda$56$lambda$55 */
    static final Unit m811xc7f48b6c(String str, MutableState mutableState) {
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$59$lambda$58$lambda$57(String str, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C68@6647L9:DashboardScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1868105694, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:68)");
            }
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$64$lambda$61$lambda$60(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("masrof-backup.db");
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$64$lambda$63$lambda$62(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch(new String[]{"application/x-sqlite3"});
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$69$lambda$66$lambda$65(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("masrof-full-backup.zip");
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$69$lambda$68$lambda$67(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch(new String[]{"application/zip", "application/octet-stream"});
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$71$lambda$70(Context context, MasrofViewModel masrofViewModel) {
        AppBackupManager.INSTANCE.shareBackup(context);
        masrofViewModel.recordAudit("SHARE_BACKUP", "مشاركة النسخة الاحتياطية");
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97(final Document document, AppRole appRole, final MasrofViewModel masrofViewModel, boolean z, boolean z2, final Function1 function1, final Context context, boolean z3, final MutableState mutableState, MutableState mutableState2, ColumnScope Card, Composer composer, int i) {
        int i2;
        int i3;
        String str;
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation(composer, "C91@8634L2842:DashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1314966669, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DashboardScreen.kt:91)");
            }
            Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(10.0f));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, 48);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -212755953, "C92@8802L88,92@8763L128,93@8916L618,101@11090L217,101@11069L258:DashboardScreen.kt#ska5t9");
            boolean zContains = DashboardScreen$lambda$3(mutableState).contains(Long.valueOf(document.getId()));
            ComposerKt.sourceInformationMarkerStart(composer, -699601111, "CC(remember):DashboardScreen.kt#9igjgp");
            boolean zChanged = composer.changed(document);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DashboardScreenKt.m812x1836cf8d(document, mutableState, ((Boolean) obj).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue, null, false, null, null, composer, 0, 60);
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierWeight$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl2 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -785807587, "C93@9038L10,93@8957L104,93@9128L10,93@9063L121,93@9186L35:DashboardScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g(documentTitle(document.getType()) + " — " + document.getDocumentNumber(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getTitleMedium(), composer, 0, 0, 65534);
            TextKt.m3342Text4IGK_g("الحالة: " + statusTitle(document.getStatus()), (Modifier) null, statusColor(document.getStatus()), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelMedium(), composer, 0, 0, 65530);
            String beneficiaryName = document.getBeneficiaryName();
            if (beneficiaryName == null) {
                beneficiaryName = "";
            }
            TextKt.m3342Text4IGK_g(beneficiaryName, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            Composer composer2 = composer;
            if (document.getAmount() != null) {
                composer2.startReplaceGroup(2052870081);
                ComposerKt.sourceInformation(composer2, "93@9247L26");
                i2 = -794711749;
                TextKt.m3342Text4IGK_g(document.getAmount() + " ريال", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
                composer2 = composer;
            } else {
                i2 = -794711749;
                composer2.startReplaceGroup(-794711749);
            }
            composer2.endReplaceGroup();
            if (StringsKt.isBlank(document.getFinancialCategory())) {
                composer2.startReplaceGroup(i2);
            } else {
                composer2.startReplaceGroup(2052872349);
                ComposerKt.sourceInformation(composer2, "93@9411L10,93@9315L118");
                TextKt.m3342Text4IGK_g("البند: " + document.getFinancialCategory() + " — مركز التكلفة: " + document.getCostCenter(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getLabelSmall(), composer, 0, 0, 65534);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
            if (StringsKt.isBlank(document.getTags())) {
                composer2.startReplaceGroup(i2);
            } else {
                composer2.startReplaceGroup(2052877005);
                ComposerKt.sourceInformation(composer2, "93@9510L10,93@9462L70");
                TextKt.m3342Text4IGK_g("وسوم: " + document.getTags(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getLabelSmall(), composer, 0, 0, 65534);
                composer2 = composer;
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (appRole == AppRole.FINANCE_MANAGER && !DashboardScreen$lambda$9(mutableState2) && document.getStatus() == DocumentStatus.SUBMITTED) {
                composer2.startReplaceGroup(-699572603);
                ComposerKt.sourceInformation(composer2, "94@9713L70,94@9692L116");
                ComposerKt.sourceInformationMarkerStart(composer2, -699571977, "CC(remember):DashboardScreen.kt#9igjgp");
                boolean zChangedInstance = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue2 = composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m813xd6e4e7d3(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                i3 = -221533263;
                str = "CC(remember):DashboardScreen.kt#9igjgp";
                ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$1881310793$app(), composer2, 805306368, 510);
            } else {
                i3 = -221533263;
                str = "CC(remember):DashboardScreen.kt#9igjgp";
                composer2.startReplaceGroup(-221533263);
            }
            composer2.endReplaceGroup();
            if (appRole == AppRole.ADMIN && !DashboardScreen$lambda$9(mutableState2) && document.getStatus() == DocumentStatus.APPROVED_FINANCE) {
                composer2.startReplaceGroup(-699563926);
                ComposerKt.sourceInformation(composer2, "95@9984L69,95@9963L121");
                String str4 = str;
                ComposerKt.sourceInformationMarkerStart(composer2, -699563306, str4);
                boolean zChangedInstance2 = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue3 = composer2.rememberedValue();
                if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m814x3770f741(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                str2 = str4;
                ButtonKt.TextButton((Function0) objRememberedValue3, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$1548688960$app(), composer2, 805306368, 510);
            } else {
                str2 = str;
                composer2.startReplaceGroup(i3);
            }
            composer2.endReplaceGroup();
            if (appRole == AppRole.ADMIN && !DashboardScreen$lambda$9(mutableState2) && document.getStatus() == DocumentStatus.APPROVED_BRANCH) {
                composer2.startReplaceGroup(-699555138);
                ComposerKt.sourceInformation(composer2, "96@10259L62,96@10238L109");
                String str5 = str2;
                ComposerKt.sourceInformationMarkerStart(composer2, -699554513, str5);
                boolean zChangedInstance3 = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda31
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m815x613a5cc5(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                str3 = str5;
                ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$1218241473$app(), composer2, 805306368, 510);
            } else {
                str3 = str2;
                composer2.startReplaceGroup(i3);
            }
            composer2.endReplaceGroup();
            if (z && !DashboardScreen$lambda$9(mutableState2) && document.getStatus() == DocumentStatus.APPROVED) {
                composer2.startReplaceGroup(-699548522);
                ComposerKt.sourceInformation(composer2, "97@10466L58,97@10445L101");
                ComposerKt.sourceInformationMarkerStart(composer2, -699547893, str3);
                boolean zChangedInstance4 = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue5 = composer2.rememberedValue();
                if (zChangedInstance4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda32
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m816x8b03c249(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.TextButton((Function0) objRememberedValue5, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$887793986$app(), composer2, 805306368, 510);
            } else {
                composer2.startReplaceGroup(i3);
            }
            composer2.endReplaceGroup();
            if (z && !DashboardScreen$lambda$9(mutableState2) && (document.getStatus() == DocumentStatus.SUBMITTED || document.getStatus() == DocumentStatus.APPROVED)) {
                composer2.startReplaceGroup(-699540744);
                ComposerKt.sourceInformation(composer2, "98@10709L63,98@10688L103");
                ComposerKt.sourceInformationMarkerStart(composer2, -699540112, str3);
                boolean zChangedInstance5 = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue6 = composer2.rememberedValue();
                if (zChangedInstance5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda33
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m817xb4cd27cd(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.TextButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$557346499$app(), composer2, 805306368, 510);
            } else {
                composer2.startReplaceGroup(i3);
            }
            composer2.endReplaceGroup();
            if (DashboardScreen$lambda$9(mutableState2)) {
                composer2.startReplaceGroup(-699536131);
                ComposerKt.sourceInformation(composer2, "99@10854L34,99@10833L76");
                ComposerKt.sourceInformationMarkerStart(composer2, -699535501, str3);
                boolean zChangedInstance6 = composer2.changedInstance(masrofViewModel) | composer2.changed(document);
                Object objRememberedValue7 = composer2.rememberedValue();
                if (zChangedInstance6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m818xde968d51(masrofViewModel, document);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue7);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.TextButton((Function0) objRememberedValue7, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.getLambda$226899012$app(), composer2, 805306368, 510);
                composer2.endReplaceGroup();
            } else {
                if (z2) {
                    composer2.startReplaceGroup(-699532307);
                    ComposerKt.sourceInformation(composer2, "100@10973L52,100@10952L92");
                    ComposerKt.sourceInformationMarkerStart(composer2, -699531675, str3);
                    boolean zChanged2 = composer2.changed(function1) | composer2.changed(document);
                    Object objRememberedValue8 = composer2.rememberedValue();
                    if (zChanged2 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda35
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return DashboardScreenKt.m819x3f229cbf(function1, document);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue8);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.TextButton((Function0) objRememberedValue8, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7673getLambda$373014995$app(), composer2, 805306368, 510);
                } else {
                    composer2.startReplaceGroup(i3);
                }
                composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(composer2, -699527766, str3);
            boolean zChangedInstance7 = composer2.changedInstance(context) | composer2.changed(document);
            Object objRememberedValue9 = composer2.rememberedValue();
            if (zChangedInstance7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashboardScreenKt.m820x68ec0243(context, document);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.TextButton((Function0) objRememberedValue9, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7676getLambda$665268370$app(), composer, 805306368, 510);
            if (!z3 || DashboardScreen$lambda$9(mutableState2)) {
                composer.startReplaceGroup(-221533263);
            } else {
                composer.startReplaceGroup(-699518536);
                ComposerKt.sourceInformation(composer, "105@11404L33,105@11383L71");
                ComposerKt.sourceInformationMarkerStart(composer, -699517902, str3);
                boolean zChangedInstance8 = composer.changedInstance(masrofViewModel) | composer.changed(document);
                Object objRememberedValue10 = composer.rememberedValue();
                if (zChangedInstance8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue10 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.DashboardScreenKt$$ExternalSyntheticLambda37
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashboardScreenKt.m821x92b567c7(masrofViewModel, document);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue10);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.TextButton((Function0) objRememberedValue10, null, false, null, null, null, null, null, null, ComposableSingletons$DashboardScreenKt.INSTANCE.m7668getLambda$103548475$app(), composer, 805306368, 510);
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

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$76$lambda$75 */
    static final Unit m812x1836cf8d(Document document, MutableState mutableState, boolean z) {
        mutableState.setValue(z ? SetsKt.plus(DashboardScreen$lambda$3(mutableState), Long.valueOf(document.getId())) : SetsKt.minus(DashboardScreen$lambda$3(mutableState), Long.valueOf(document.getId())));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$79$lambda$78 */
    static final Unit m813xd6e4e7d3(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.transitionDocument(document, DocumentStatus.APPROVED_FINANCE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$81$lambda$80 */
    static final Unit m814x3770f741(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.transitionDocument(document, DocumentStatus.APPROVED_BRANCH);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$83$lambda$82 */
    static final Unit m815x613a5cc5(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.transitionDocument(document, DocumentStatus.APPROVED);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$85$lambda$84 */
    static final Unit m816x8b03c249(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.transitionDocument(document, DocumentStatus.PAID);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$87$lambda$86 */
    static final Unit m817xb4cd27cd(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.transitionDocument(document, DocumentStatus.CANCELLED);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$89$lambda$88 */
    static final Unit m818xde968d51(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.restoreDocument(document);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$91$lambda$90 */
    static final Unit m819x3f229cbf(Function1 function1, Document document) {
        String lowerCase = document.getType().name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        function1.invoke(lowerCase + ServerSentEventKt.COLON + document.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$93$lambda$92 */
    static final Unit m820x68ec0243(Context context, Document document) {
        OfficialDocumentExporter.INSTANCE.share(context, OfficialDocumentExporter.INSTANCE.exportPdf(context, CollectionsKt.listOf(document)), "مشاركة المستند PDF");
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DashboardScreen$lambda$108$lambda$99$lambda$98$lambda$97$lambda$96$lambda$95$lambda$94 */
    static final Unit m821x92b567c7(MasrofViewModel masrofViewModel, Document document) {
        masrofViewModel.deleteDocument(document);
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$101$lambda$100(MasrofViewModel masrofViewModel, Function1 function1, MutableState mutableState) {
        masrofViewModel.recordAudit("PRINT_EXPORT", "عدد المستندات: " + DashboardScreen$lambda$3(mutableState).size());
        function1.invoke(CollectionsKt.joinToString$default(DashboardScreen$lambda$3(mutableState), ",", null, null, 0, null, null, 62, null));
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$102(MutableState mutableState, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C111@11747L50:DashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1931523415, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous> (DashboardScreen.kt:111)");
            }
            TextKt.m3342Text4IGK_g("تصدير / طباعة المحدد (" + DashboardScreen$lambda$3(mutableState).size() + ")", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$106$lambda$105(List list, MutableState mutableState, MasrofViewModel masrofViewModel, MutableState mutableState2) {
        Object next;
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!DashboardScreen$lambda$3(mutableState).contains(Long.valueOf(((Document) next).getId())));
        Document document = (Document) next;
        if (document != null) {
            if (DashboardScreen$lambda$9(mutableState2)) {
                masrofViewModel.restoreDocument(document);
            } else {
                masrofViewModel.archiveDocument(document);
            }
            mutableState.setValue(SetsKt.emptySet());
        }
        return Unit.INSTANCE;
    }

    static final Unit DashboardScreen$lambda$108$lambda$107(MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C112@12080L75:DashboardScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-159155647, i, -1, "com.mohammedalhzmi.masrofmanager.ui.DashboardScreen.<anonymous>.<anonymous> (DashboardScreen.kt:112)");
            }
            TextKt.m3342Text4IGK_g(DashboardScreen$lambda$9(mutableState) ? "استعادة المستند المحدد" : "أرشفة المستند المحدد", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final String statusTitle(DocumentStatus documentStatus) {
        switch (WhenMappings.$EnumSwitchMapping$0[documentStatus.ordinal()]) {
            case 1:
                return "مسودة";
            case 2:
                return "قيد المراجعة";
            case 3:
                return "اعتماد المدير المالي";
            case 4:
                return "اعتماد مدير الفرع";
            case 5:
                return "معتمد";
            case 6:
                return "تم الصرف";
            case 7:
                return "تم الاستلام";
            case 8:
                return "ملغى";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final long statusColor(DocumentStatus documentStatus) {
        int i = WhenMappings.$EnumSwitchMapping$0[documentStatus.ordinal()];
        if (i == 5) {
            return ColorKt.Color(4279793998L);
        }
        if (i == 6 || i == 7) {
            return ColorKt.Color(4279524768L);
        }
        if (i == 8) {
            return ColorKt.Color(4289995544L);
        }
        return ColorKt.Color(4287259933L);
    }

    private static final String documentTitle(DocumentType documentType) {
        int i = WhenMappings.$EnumSwitchMapping$1[documentType.ordinal()];
        if (i == 1) {
            return "ورقة تقديم طلب";
        }
        if (i == 2) {
            return "أمر صرف";
        }
        if (i == 3) {
            return "ورقة استلام";
        }
        return documentType.name();
    }

    private static final List<Document> DashboardScreen$lambda$0(State<? extends List<Document>> state) {
        return state.getValue();
    }

    private static final List<Document> DashboardScreen$lambda$1(State<? extends List<Document>> state) {
        return state.getValue();
    }
}
