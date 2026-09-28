package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.net.Uri;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DividerKt;
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
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.messaging.Constants;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.util.AppLockPreferences;
import com.mohammedalhzmi.masrofmanager.util.AppPreferences;
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering;
import com.mohammedalhzmi.masrofmanager.util.LockType;
import com.mohammedalhzmi.masrofmanager.util.RolePreferences;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SettingsScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a7\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a1\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\fH\u0003¢\u0006\u0002\u0010\r\u001a+\u0010\u000e\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003¢\u0006\u0002\u0010\u0012\u001a1\u0010\u0013\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\fH\u0003¢\u0006\u0002\u0010\r\u001a9\u0010\u0014\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\fH\u0003¢\u0006\u0002\u0010\u0018¨\u0006\u0019²\u0006\n\u0010\u001a\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\tX\u008a\u008e\u0002²\u0006\f\u0010\u001f\u001a\u0004\u0018\u00010\tX\u008a\u008e\u0002²\u0006\f\u0010 \u001a\u0004\u0018\u00010\tX\u008a\u008e\u0002²\u0006\f\u0010!\u001a\u0004\u0018\u00010\tX\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010%\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010&\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010'\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010)\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020\u0016X\u008a\u008e\u0002²\u0006\n\u0010+\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010,\u001a\u00020\tX\u008a\u008e\u0002²\u0006\n\u0010-\u001a\u00020\tX\u008a\u008e\u0002"}, m914d2 = {"SettingsScreen", "", "onBack", "Lkotlin/Function0;", "onManageUsers", "onUpdates", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SettingField", Constants.ScionAnalytics.PARAM_LABEL, "", Values.VECTOR_MAP_VECTORS_KEY, "onChange", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "LogoSetting", "selected", "", "onPick", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "PageSizeSetting", "LockTypeButton", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/util/LockType;", "onSelect", "(Ljava/lang/String;Lcom/mohammedalhzmi/masrofmanager/util/LockType;Lcom/mohammedalhzmi/masrofmanager/util/LockType;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "ministry", "administration", "branch", "manager", "finance", "orderLogo", "requestLogo", "receiptLogo", "orderStart", "requestStart", "receiptStart", "orderPage", "requestPage", "receiptPage", "lockEnabled", "biometricEnabled", "lockType", "newSecret", "timeout", "userName"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class SettingsScreenKt {
    static final Unit LockTypeButton$lambda$143(String str, LockType lockType, LockType lockType2, Function1 function1, int i, Composer composer, int i2) {
        LockTypeButton(str, lockType, lockType2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit LogoSetting$lambda$126(String str, boolean z, Function0 function0, int i, Composer composer, int i2) {
        LogoSetting(str, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PageSizeSetting$lambda$136(String str, String str2, Function1 function1, int i, Composer composer, int i2) {
        PageSizeSetting(str, str2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SettingField$lambda$124(String str, String str2, Function1 function1, int i, Composer composer, int i2) {
        SettingField(str, str2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$122(Function0 function0, Function0 function1, Function0 function2, int i, Composer composer, int i2) {
        SettingsScreen(function0, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(final Function0<Unit> function0, final Function0<Unit> onManageUsers, final Function0<Unit> onUpdates, Composer composer, final int i) {
        int i2;
        final MutableState mutableState;
        final MutableState mutableState2;
        final MutableState mutableState3;
        final MutableState mutableState4;
        final MutableState mutableState5;
        final MutableState mutableState6;
        final MutableState mutableState7;
        final MutableState mutableState8;
        final MutableState mutableState9;
        final MutableState mutableState10;
        final MutableState mutableState11;
        final MutableState mutableState12;
        final MutableState mutableState13;
        final MutableState mutableState14;
        String str;
        final MutableState mutableState15;
        final MutableState mutableState16;
        final MutableState mutableState17;
        Composer composer2;
        final Function0<Unit> onBack = function0;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onManageUsers, "onManageUsers");
        Intrinsics.checkNotNullParameter(onUpdates, "onUpdates");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1057760095);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SettingsScreen)21@1011L7,22@1039L61,23@1127L67,24@1213L59,25@1292L60,26@1372L67,27@1461L80,28@1565L82,29@1671L82,30@1776L91,31@1892L93,32@2010L93,33@2125L81,34@2230L83,35@2337L83,36@2444L64,37@2537L73,38@2631L61,39@2714L31,40@2765L82,41@2868L62,42@3025L107,42@2953L179,43@3229L111,43@3157L183,44@3437L111,44@3365L183,45@3610L21,45@3553L4961:SettingsScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(onBack) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onManageUsers) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onUpdates) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1057760095, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.SettingsScreen (SettingsScreen.kt:20)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399803102, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.ministry(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState18 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399805924, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.administration(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState19 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399808668, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.branch(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            MutableState mutableState20 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399811197, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.manager(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            MutableState mutableState21 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399813764, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.financeManager(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            MutableState mutableState22 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399816625, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.logoUri(context, DocumentType.ORDER), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState23 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399819955, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.logoUri(context, DocumentType.REQUEST), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final MutableState mutableState24 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399823347, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.logoUri(context, DocumentType.RECEIPT), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            final MutableState mutableState25 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399826716, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.ORDER)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            MutableState mutableState26 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399830430, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.REQUEST)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
            }
            MutableState mutableState27 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399834206, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(DocumentNumbering.INSTANCE.next(context, DocumentType.RECEIPT)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
            }
            MutableState mutableState28 = (MutableState) objRememberedValue11;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399837874, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.pageSize(context, DocumentType.ORDER), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
            }
            MutableState mutableState29 = (MutableState) objRememberedValue12;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399841236, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.pageSize(context, DocumentType.REQUEST), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
            }
            MutableState mutableState30 = (MutableState) objRememberedValue13;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399844660, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppPreferences.INSTANCE.pageSize(context, DocumentType.RECEIPT), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
            }
            MutableState mutableState31 = (MutableState) objRememberedValue14;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399848065, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue15 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(AppLockPreferences.INSTANCE.enabled(context)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
            }
            MutableState mutableState32 = (MutableState) objRememberedValue15;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399851050, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue16 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue16 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(AppLockPreferences.INSTANCE.biometricEnabled(context)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue16);
            }
            MutableState mutableState33 = (MutableState) objRememberedValue16;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399854046, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue17 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue17 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(AppLockPreferences.INSTANCE.type(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue17);
            }
            MutableState mutableState34 = (MutableState) objRememberedValue17;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399856672, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue18 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue18 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue18 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue18);
            }
            MutableState mutableState35 = (MutableState) objRememberedValue18;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399858355, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue19 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue19 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue19 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(String.valueOf(AppLockPreferences.INSTANCE.timeoutMinutes(context)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue19);
            }
            MutableState mutableState36 = (MutableState) objRememberedValue19;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399861631, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue20 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue20 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue20 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(RolePreferences.INSTANCE.userName(context), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue20);
            }
            MutableState mutableState37 = (MutableState) objRememberedValue20;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ActivityResultContracts.GetContent getContent = new ActivityResultContracts.GetContent();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399866700, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue21 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue21 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue21 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$62$lambda$61(context, mutableState23, (Uri) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue21);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(getContent, (Function1) objRememberedValue21, composerStartRestartGroup, 0);
            ActivityResultContracts.GetContent getContent2 = new ActivityResultContracts.GetContent();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399873232, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue22 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue22 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue22 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$65$lambda$64(context, mutableState24, (Uri) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue22);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(getContent2, (Function1) objRememberedValue22, composerStartRestartGroup, 0);
            ActivityResultContracts.GetContent getContent3 = new ActivityResultContracts.GetContent();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1399879888, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue23 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue23 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue23 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$68$lambda$67(context, mutableState25, (Uri) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue23);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ManagedActivityResultLauncher managedActivityResultLauncherRememberLauncherForActivityResult3 = ActivityResultRegistryKt.rememberLauncherForActivityResult(getContent3, (Function1) objRememberedValue23, composerStartRestartGroup, 0);
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(16.0f)), ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(10.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2065586251, "C46@3749L10,46@3695L80,47@3879L10,47@3784L116,48@3959L10,48@3909L72,49@3990L110,50@4109L98,51@4262L17,51@4216L63,52@4378L10,52@4288L112,53@4443L17,53@4409L51,54@4519L23,54@4469L73,55@4592L15,55@4551L56,56@4656L16,56@4616L56,57@4724L16,57@4681L59,58@4798L33,58@4749L83,59@4895L35,59@4841L90,60@4995L35,60@4940L91,61@5094L10,61@5040L76,62@5175L41,62@5125L91,63@5270L18,63@5225L63,64@5352L43,64@5297L98,65@5454L20,65@5404L70,66@5539L43,66@5483L99,67@5642L20,67@5591L71,68@5671L19,69@5742L10,69@5699L65,70@5773L243,74@6025L307,79@6505L18,79@6341L182,80@6532L242,84@6846L38,84@6783L101,85@7007L10,85@6893L135,86@7145L1206,86@7037L1377,94@8423L85:SettingsScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("إعدادات النماذج الرسمية", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getHeadlineMedium(), composerStartRestartGroup, 6, 0, 65534);
            TextKt.m3342Text4IGK_g("يتم حفظ البيانات والشعارات محليًا وتطبيقها على النوع المحدد فقط.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65534);
            TextKt.m3342Text4IGK_g("المستخدم والصلاحيات", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
            ButtonKt.Button(onManageUsers, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.m7699getLambda$414305413$app(), composerStartRestartGroup, ((i2 >> 3) & 14) | 805306416, 508);
            ButtonKt.OutlinedButton(onUpdates, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$1512374201$app(), composerStartRestartGroup, ((i2 >> 6) & 14) | 805306416, 508);
            String strSettingsScreen$lambda$58 = SettingsScreen$lambda$58(mutableState37);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729213148, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue24 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue24 == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState37;
                objRememberedValue24 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$70$lambda$69(mutableState, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue24);
            } else {
                mutableState = mutableState37;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("اسم المستخدم الحالي", strSettingsScreen$lambda$58, (Function1) objRememberedValue24, composerStartRestartGroup, 390);
            TextKt.m3342Text4IGK_g("الدور الحالي: " + RolePreferences.INSTANCE.currentRole(context).getTitle(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodyMedium(), composerStartRestartGroup, 0, 0, 65534);
            String strSettingsScreen$lambda$1 = SettingsScreen$lambda$1(mutableState18);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729218940, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue25 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue25 == Composer.INSTANCE.getEmpty()) {
                mutableState2 = mutableState18;
                objRememberedValue25 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$72$lambda$71(mutableState2, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue25);
            } else {
                mutableState2 = mutableState18;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("الوزارة", strSettingsScreen$lambda$1, (Function1) objRememberedValue25, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$4 = SettingsScreen$lambda$4(mutableState19);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729221378, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue26 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue26 == Composer.INSTANCE.getEmpty()) {
                mutableState3 = mutableState19;
                objRememberedValue26 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$74$lambda$73(mutableState3, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue26);
            } else {
                mutableState3 = mutableState19;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("الإدارة / الصندوق", strSettingsScreen$lambda$4, (Function1) objRememberedValue26, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$7 = SettingsScreen$lambda$7(mutableState20);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729223706, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue27 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue27 == Composer.INSTANCE.getEmpty()) {
                mutableState4 = mutableState20;
                objRememberedValue27 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$76$lambda$75(mutableState4, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue27);
            } else {
                mutableState4 = mutableState20;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("الفرع / المديرية", strSettingsScreen$lambda$7, (Function1) objRememberedValue27, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$10 = SettingsScreen$lambda$10(mutableState21);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729225755, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue28 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue28 == Composer.INSTANCE.getEmpty()) {
                mutableState5 = mutableState21;
                objRememberedValue28 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$78$lambda$77(mutableState5, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue28);
            } else {
                mutableState5 = mutableState21;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("اسم مدير الفرع", strSettingsScreen$lambda$10, (Function1) objRememberedValue28, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$13 = SettingsScreen$lambda$13(mutableState22);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729227931, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue29 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue29 == Composer.INSTANCE.getEmpty()) {
                mutableState6 = mutableState22;
                objRememberedValue29 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$80$lambda$79(mutableState6, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue29);
            } else {
                mutableState6 = mutableState22;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("اسم المدير المالي", strSettingsScreen$lambda$13, (Function1) objRememberedValue29, composerStartRestartGroup, 390);
            boolean z = SettingsScreen$lambda$16(mutableState23) != null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729230316, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult);
            Object objRememberedValue30 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue30 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue30 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$82$lambda$81(managedActivityResultLauncherRememberLauncherForActivityResult);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue30);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LogoSetting("شعار أمر الصرف", z, (Function0) objRememberedValue30, composerStartRestartGroup, 6);
            boolean z2 = SettingsScreen$lambda$19(mutableState24) != null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729233422, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult2);
            Object objRememberedValue31 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance5 || objRememberedValue31 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue31 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$84$lambda$83(managedActivityResultLauncherRememberLauncherForActivityResult2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue31);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LogoSetting("شعار ورقة التقديم", z2, (Function0) objRememberedValue31, composerStartRestartGroup, 6);
            boolean z3 = SettingsScreen$lambda$22(mutableState25) != null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729236622, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(managedActivityResultLauncherRememberLauncherForActivityResult3);
            Object objRememberedValue32 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance6 || objRememberedValue32 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue32 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$86$lambda$85(managedActivityResultLauncherRememberLauncherForActivityResult3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue32);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LogoSetting("شعار ورقة الاستلام", z3, (Function0) objRememberedValue32, composerStartRestartGroup, 6);
            TextKt.m3342Text4IGK_g("الترقيم ومقاسات الصفحات", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
            String strSettingsScreen$lambda$25 = SettingsScreen$lambda$25(mutableState26);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729242388, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue33 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue33 == Composer.INSTANCE.getEmpty()) {
                mutableState7 = mutableState26;
                objRememberedValue33 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$88$lambda$87(mutableState7, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue33);
            } else {
                mutableState7 = mutableState26;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("بداية ترقيم أمر الصرف", strSettingsScreen$lambda$25, (Function1) objRememberedValue33, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$34 = SettingsScreen$lambda$34(mutableState29);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729245405, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue34 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue34 == Composer.INSTANCE.getEmpty()) {
                mutableState8 = mutableState29;
                objRememberedValue34 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$90$lambda$89(mutableState8, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue34);
            } else {
                mutableState8 = mutableState29;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            PageSizeSetting("مقاس أمر الصرف", strSettingsScreen$lambda$34, (Function1) objRememberedValue34, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$28 = SettingsScreen$lambda$28(mutableState27);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729248054, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue35 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue35 == Composer.INSTANCE.getEmpty()) {
                mutableState9 = mutableState27;
                objRememberedValue35 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$93$lambda$92(mutableState9, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue35);
            } else {
                mutableState9 = mutableState27;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("بداية ترقيم ورقة التقديم", strSettingsScreen$lambda$28, (Function1) objRememberedValue35, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$37 = SettingsScreen$lambda$37(mutableState30);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729251295, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue36 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue36 == Composer.INSTANCE.getEmpty()) {
                mutableState10 = mutableState30;
                objRememberedValue36 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$95$lambda$94(mutableState10, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue36);
            } else {
                mutableState10 = mutableState30;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState38 = mutableState7;
            PageSizeSetting("مقاس ورقة التقديم", strSettingsScreen$lambda$37, (Function1) objRememberedValue36, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$31 = SettingsScreen$lambda$31(mutableState28);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729254038, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue37 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue37 == Composer.INSTANCE.getEmpty()) {
                mutableState11 = mutableState28;
                objRememberedValue37 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$98$lambda$97(mutableState11, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue37);
            } else {
                mutableState11 = mutableState28;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState39 = mutableState11;
            SettingField("بداية ترقيم ورقة الاستلام", strSettingsScreen$lambda$31, (Function1) objRememberedValue37, composerStartRestartGroup, 390);
            String strSettingsScreen$lambda$40 = SettingsScreen$lambda$40(mutableState31);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729257311, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue38 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue38 == Composer.INSTANCE.getEmpty()) {
                mutableState12 = mutableState31;
                objRememberedValue38 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$100$lambda$99(mutableState12, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue38);
            } else {
                mutableState12 = mutableState31;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState40 = mutableState12;
            PageSizeSetting("مقاس ورقة الاستلام", strSettingsScreen$lambda$40, (Function1) objRememberedValue38, composerStartRestartGroup, 390);
            final MutableState mutableState41 = mutableState9;
            final MutableState mutableState42 = mutableState8;
            final MutableState mutableState43 = mutableState3;
            final MutableState mutableState44 = mutableState4;
            final MutableState mutableState45 = mutableState5;
            final MutableState mutableState46 = mutableState6;
            DividerKt.m2721HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composerStartRestartGroup, 0, 7);
            TextKt.m3342Text4IGK_g("أمان التطبيق", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 6, 0, 65534);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 321210363, "C71@5877L47,72@5985L20,72@5937L69:SettingsScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("قفل التطبيق عند الخروج أو انتهاء المهلة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 6, 0, 131070);
            boolean zSettingsScreen$lambda$43 = SettingsScreen$lambda$43(mutableState32);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -543824357, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue39 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue39 == Composer.INSTANCE.getEmpty()) {
                mutableState13 = mutableState32;
                objRememberedValue39 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$103$lambda$102$lambda$101(mutableState13, ((Boolean) obj).booleanValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue39);
            } else {
                mutableState13 = mutableState32;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState47 = mutableState2;
            final MutableState mutableState48 = mutableState13;
            final MutableState mutableState49 = mutableState;
            SwitchKt.Switch(zSettingsScreen$lambda$43, (Function1) objRememberedValue39, null, null, false, null, null, composerStartRestartGroup, 48, 124);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1438235696, "C75@6143L17,75@6095L65,76@6225L17,76@6173L69,77@6305L17,77@6255L67:SettingsScreen.kt#ska5t9");
            LockType lockType = LockType.PIN;
            LockType lockTypeSettingsScreen$lambda$49 = SettingsScreen$lambda$49(mutableState34);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1154772031, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue40 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue40 == Composer.INSTANCE.getEmpty()) {
                mutableState14 = mutableState34;
                objRememberedValue40 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$110$lambda$105$lambda$104(mutableState14, (LockType) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue40);
            } else {
                mutableState14 = mutableState34;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LockTypeButton("أرقام", lockType, lockTypeSettingsScreen$lambda$49, (Function1) objRememberedValue40, composerStartRestartGroup, 3126);
            LockType lockType2 = LockType.PASSWORD;
            LockType lockTypeSettingsScreen$lambda$410 = SettingsScreen$lambda$49(mutableState14);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1154769407, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue41 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue41 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue41 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$110$lambda$107$lambda$106(mutableState14, (LockType) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue41);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LockTypeButton("كلمة", lockType2, lockTypeSettingsScreen$lambda$410, (Function1) objRememberedValue41, composerStartRestartGroup, 3126);
            LockType lockType3 = LockType.PATTERN;
            LockType lockTypeSettingsScreen$lambda$411 = SettingsScreen$lambda$49(mutableState14);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1154766847, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue42 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue42 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue42 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$110$lambda$109$lambda$108(mutableState14, (LockType) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue42);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LockTypeButton("نقش", lockType3, lockTypeSettingsScreen$lambda$411, (Function1) objRememberedValue42, composerStartRestartGroup, 3126);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (SettingsScreen$lambda$49(mutableState14) == LockType.PIN) {
                str = "رمز جديد";
            } else {
                str = SettingsScreen$lambda$49(mutableState14) == LockType.PASSWORD ? "كلمة مرور جديدة" : "نقش جديد (أرقام النقاط مثل 1478)";
            }
            String strSettingsScreen$lambda$52 = SettingsScreen$lambda$52(mutableState35);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729284925, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue43 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue43 == Composer.INSTANCE.getEmpty()) {
                mutableState15 = mutableState35;
                objRememberedValue43 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$112$lambda$111(mutableState15, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue43);
            } else {
                mutableState15 = mutableState35;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField(str, strSettingsScreen$lambda$52, (Function1) objRememberedValue43, composerStartRestartGroup, 384);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceBetween2, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default2);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1174541740, "C81@6636L36,82@6738L25,82@6685L79:SettingsScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("السماح بالبصمة / أمان الهاتف", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 6, 0, 131070);
            boolean zSettingsScreen$lambda$46 = SettingsScreen$lambda$46(mutableState33);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1839000600, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue44 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue44 == Composer.INSTANCE.getEmpty()) {
                mutableState16 = mutableState33;
                objRememberedValue44 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$115$lambda$114$lambda$113(mutableState16, ((Boolean) obj).booleanValue());
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue44);
            } else {
                mutableState16 = mutableState33;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final MutableState mutableState50 = mutableState15;
            final MutableState mutableState51 = mutableState14;
            SwitchKt.Switch(zSettingsScreen$lambda$46, (Function1) objRememberedValue44, null, null, false, null, null, composerStartRestartGroup, 48, 124);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            String strSettingsScreen$lambda$55 = SettingsScreen$lambda$55(mutableState36);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729295857, "CC(remember):SettingsScreen.kt#9igjgp");
            Object objRememberedValue45 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue45 == Composer.INSTANCE.getEmpty()) {
                mutableState17 = mutableState36;
                objRememberedValue45 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$118$lambda$117(mutableState17, (String) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue45);
            } else {
                mutableState17 = mutableState36;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SettingField("مهلة إعادة القفل بالدقائق (0 = فورًا)", strSettingsScreen$lambda$55, (Function1) objRememberedValue45, composerStartRestartGroup, 390);
            TextKt.m3342Text4IGK_g("اقتراحات أمان: استخدم بصمة الهاتف، ورمزًا لا يقل عن 4 أرقام، ولا تشارك كلمة المرور.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65534);
            boolean z4 = (SettingsScreen$lambda$43(mutableState48) && !AppLockPreferences.INSTANCE.hasSecret(context) && StringsKt.isBlank(SettingsScreen$lambda$52(mutableState50))) ? false : true;
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1729306593, "CC(remember):SettingsScreen.kt#9igjgp");
            int i3 = i2 & 14;
            boolean zChangedInstance7 = (i3 == 4) | composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue46 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance7 || objRememberedValue46 == Composer.INSTANCE.getEmpty()) {
                final MutableState mutableState52 = mutableState16;
                final MutableState mutableState53 = mutableState17;
                final MutableState mutableState54 = mutableState10;
                Function0 function1 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SettingsScreenKt.SettingsScreen$lambda$121$lambda$120$lambda$119(context, function0, mutableState47, mutableState43, mutableState44, mutableState45, mutableState46, mutableState38, mutableState41, mutableState39, mutableState42, mutableState54, mutableState40, mutableState49, mutableState48, mutableState52, mutableState51, mutableState53, mutableState50);
                    }
                };
                composer2 = composerStartRestartGroup;
                composer2.updateRememberedValue(function1);
                objRememberedValue46 = function1;
            } else {
                composer2 = composerStartRestartGroup;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Composer composer3 = composer2;
            ButtonKt.Button((Function0) objRememberedValue46, modifierFillMaxWidth$default3, z4, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$276247012$app(), composer3, 805306416, 504);
            onBack = function0;
            ButtonKt.OutlinedButton(onBack, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$684451490$app(), composer3, i3 | 805306416, 508);
            composerStartRestartGroup = composer3;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$122(onBack, onManageUsers, onUpdates, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String SettingsScreen$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$28(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$34(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$37(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$40(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean SettingsScreen$lambda$43(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$44(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$46(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$47(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final LockType SettingsScreen$lambda$49(MutableState<LockType> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$52(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$55(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SettingsScreen$lambda$58(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit SettingsScreen$lambda$62$lambda$61(Context context, MutableState mutableState, Uri uri) {
        if (uri != null) {
            mutableState.setValue(uri.toString());
            AppPreferences.INSTANCE.saveLogo(context, DocumentType.ORDER, uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$65$lambda$64(Context context, MutableState mutableState, Uri uri) {
        if (uri != null) {
            mutableState.setValue(uri.toString());
            AppPreferences.INSTANCE.saveLogo(context, DocumentType.REQUEST, uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$68$lambda$67(Context context, MutableState mutableState, Uri uri) {
        if (uri != null) {
            mutableState.setValue(uri.toString());
            AppPreferences.INSTANCE.saveLogo(context, DocumentType.RECEIPT, uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$70$lambda$69(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$72$lambda$71(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$74$lambda$73(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$76$lambda$75(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$78$lambda$77(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$80$lambda$79(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$82$lambda$81(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("image/*");
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$84$lambda$83(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("image/*");
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$86$lambda$85(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("image/*");
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$90$lambda$89(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$95$lambda$94(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$100$lambda$99(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$103$lambda$102$lambda$101(MutableState mutableState, boolean z) {
        SettingsScreen$lambda$44(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$110$lambda$105$lambda$104(MutableState mutableState, LockType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$110$lambda$107$lambda$106(MutableState mutableState, LockType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$110$lambda$109$lambda$108(MutableState mutableState, LockType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$112$lambda$111(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$115$lambda$114$lambda$113(MutableState mutableState, boolean z) {
        SettingsScreen$lambda$47(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$120$lambda$119(Context context, Function0 function0, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, MutableState mutableState10, MutableState mutableState11, MutableState mutableState12, MutableState mutableState13, MutableState mutableState14, MutableState mutableState15, MutableState mutableState16, MutableState mutableState17) {
        AppPreferences.INSTANCE.put(context, "ministry", SettingsScreen$lambda$1(mutableState));
        AppPreferences.INSTANCE.put(context, "administration", SettingsScreen$lambda$4(mutableState2));
        AppPreferences.INSTANCE.put(context, "branch", SettingsScreen$lambda$7(mutableState3));
        AppPreferences.INSTANCE.put(context, "manager", SettingsScreen$lambda$10(mutableState4));
        AppPreferences.INSTANCE.put(context, "finance_manager", SettingsScreen$lambda$13(mutableState5));
        DocumentNumbering documentNumbering = DocumentNumbering.INSTANCE;
        DocumentType documentType = DocumentType.ORDER;
        Integer intOrNull = StringsKt.toIntOrNull(SettingsScreen$lambda$25(mutableState6));
        documentNumbering.setStart(context, documentType, intOrNull != null ? intOrNull.intValue() : 1);
        DocumentNumbering documentNumbering2 = DocumentNumbering.INSTANCE;
        DocumentType documentType2 = DocumentType.REQUEST;
        Integer intOrNull2 = StringsKt.toIntOrNull(SettingsScreen$lambda$28(mutableState7));
        documentNumbering2.setStart(context, documentType2, intOrNull2 != null ? intOrNull2.intValue() : 1);
        DocumentNumbering documentNumbering3 = DocumentNumbering.INSTANCE;
        DocumentType documentType3 = DocumentType.RECEIPT;
        Integer intOrNull3 = StringsKt.toIntOrNull(SettingsScreen$lambda$31(mutableState8));
        documentNumbering3.setStart(context, documentType3, intOrNull3 != null ? intOrNull3.intValue() : 1);
        AppPreferences.INSTANCE.setPageSize(context, DocumentType.ORDER, SettingsScreen$lambda$34(mutableState9));
        AppPreferences.INSTANCE.setPageSize(context, DocumentType.REQUEST, SettingsScreen$lambda$37(mutableState10));
        AppPreferences.INSTANCE.setPageSize(context, DocumentType.RECEIPT, SettingsScreen$lambda$40(mutableState11));
        RolePreferences.INSTANCE.setUserName(context, SettingsScreen$lambda$58(mutableState12));
        AppLockPreferences.INSTANCE.setEnabled(context, SettingsScreen$lambda$43(mutableState13));
        AppLockPreferences.INSTANCE.setBiometricEnabled(context, SettingsScreen$lambda$46(mutableState14));
        AppLockPreferences.INSTANCE.setType(context, SettingsScreen$lambda$49(mutableState15));
        AppLockPreferences appLockPreferences = AppLockPreferences.INSTANCE;
        Integer intOrNull4 = StringsKt.toIntOrNull(SettingsScreen$lambda$55(mutableState16));
        appLockPreferences.setTimeoutMinutes(context, intOrNull4 != null ? intOrNull4.intValue() : 5);
        if (!StringsKt.isBlank(SettingsScreen$lambda$52(mutableState17))) {
            AppLockPreferences.INSTANCE.setSecret(context, SettingsScreen$lambda$52(mutableState17));
        }
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final void SettingField(final String str, final String str2, final Function1<? super String, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-715387579);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SettingField)P(!1,2)99@8658L15,99@8615L95:SettingsScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-715387579, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.SettingField (SettingsScreen.kt:99)");
            }
            int i3 = i2 >> 3;
            composer2 = composerStartRestartGroup;
            OutlinedTextFieldKt.OutlinedTextField(str2, function1, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(961632491, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingField$lambda$123(str, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, (i3 & 14) | 1573248 | (i3 & 112), 0, 0, 8388536);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingField$lambda$124(str, str2, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SettingField$lambda$123(String str, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C99@8660L11:SettingsScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(961632491, i, -1, "com.mohammedalhzmi.masrofmanager.ui.SettingField.<anonymous> (SettingsScreen.kt:99)");
            }
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void LogoSetting(final String str, final boolean z, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(481548102);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LogoSetting)P(!1,2)103@8879L77,103@8810L146:SettingsScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(481548102, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.LogoSetting (SettingsScreen.kt:102)");
            }
            ButtonKt.OutlinedButton(function0, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(590370452, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.LogoSetting$lambda$125(z, str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i2 >> 6) & 14) | 805306416, 508);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.LogoSetting$lambda$126(str, z, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LogoSetting$lambda$125(boolean z, String str, RowScope OutlinedButton, Composer composer, int i) {
        StringBuilder sb;
        StringBuilder sbAppend;
        String str2;
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C103@8881L73:SettingsScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(590370452, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LogoSetting.<anonymous> (SettingsScreen.kt:103)");
            }
            if (z) {
                sb = new StringBuilder();
                sbAppend = sb.append(str);
                str2 = " — تم اختيار شعار";
            } else {
                sb = new StringBuilder();
                sbAppend = sb.append(str);
                str2 = " — اختيار صورة";
            }
            TextKt.m3342Text4IGK_g(sbAppend.append(str2).toString(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void PageSizeSetting(final String str, String str2, final Function1<? super String, Unit> function1, Composer composer, final int i) {
        int i2;
        final String str3;
        final Function1<? super String, Unit> function2;
        String str4;
        int i3;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1556468577);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PageSizeSetting)P(!1,2)108@9098L10,108@9064L56,109@9125L374:SettingsScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            function2 = function1;
            str3 = str2;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1556468577, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSizeSetting (SettingsScreen.kt:107)");
            }
            int i5 = i2;
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodyMedium(), composerStartRestartGroup, i2 & 14, 0, 65534);
            composerStartRestartGroup = composerStartRestartGroup;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 204625522, "C:SettingsScreen.kt#ska5t9");
            str3 = str2;
            if (Intrinsics.areEqual(str3, "A4")) {
                composerStartRestartGroup.startReplaceGroup(1946263830);
                ComposerKt.sourceInformation(composerStartRestartGroup, "110@9227L18,110@9210L51");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1946264341, "CC(remember):SettingsScreen.kt#9igjgp");
                i3 = i5;
                boolean z = (i3 & 896) == 256;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda35
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.PageSizeSetting$lambda$135$lambda$128$lambda$127(function1);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                function2 = function1;
                str4 = "CC(remember):SettingsScreen.kt#9igjgp";
                i4 = 256;
                ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$335175502$app(), composerStartRestartGroup, 805306368, 510);
            } else {
                function2 = function1;
                str4 = "CC(remember):SettingsScreen.kt#9igjgp";
                i3 = i5;
                i4 = 256;
                composerStartRestartGroup.startReplaceGroup(1946265662);
                ComposerKt.sourceInformation(composerStartRestartGroup, "110@9292L18,110@9267L59");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1946266421, str4);
                boolean z2 = (i3 & 896) == 256;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda36
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.PageSizeSetting$lambda$135$lambda$130$lambda$129(function2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$1491795737$app(), composerStartRestartGroup, 805306368, 510);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (Intrinsics.areEqual(str3, "HALF_A4")) {
                composerStartRestartGroup.startReplaceGroup(1946268607);
                ComposerKt.sourceInformation(composerStartRestartGroup, "111@9376L23,111@9359L60");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1946269114, str4);
                boolean z3 = (i3 & 896) == i4;
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda37
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.PageSizeSetting$lambda$135$lambda$132$lambda$131(function2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.Button((Function0) objRememberedValue3, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.m7698getLambda$1608098363$app(), composerStartRestartGroup, 805306368, 510);
            } else {
                composerStartRestartGroup.startReplaceGroup(1946270727);
                ComposerKt.sourceInformation(composerStartRestartGroup, "111@9450L23,111@9425L68");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1946271482, str4);
                boolean z4 = (i3 & 896) == i4;
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda38
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.PageSizeSetting$lambda$135$lambda$134$lambda$133(function2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ButtonKt.OutlinedButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$1802385104$app(), composerStartRestartGroup, 805306368, 510);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.PageSizeSetting$lambda$136(str, str3, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit PageSizeSetting$lambda$135$lambda$128$lambda$127(Function1 function1) {
        function1.invoke("A4");
        return Unit.INSTANCE;
    }

    static final Unit PageSizeSetting$lambda$135$lambda$130$lambda$129(Function1 function1) {
        function1.invoke("A4");
        return Unit.INSTANCE;
    }

    static final Unit PageSizeSetting$lambda$135$lambda$132$lambda$131(Function1 function1) {
        function1.invoke("HALF_A4");
        return Unit.INSTANCE;
    }

    static final Unit PageSizeSetting$lambda$135$lambda$134$lambda$133(Function1 function1) {
        function1.invoke("HALF_A4");
        return Unit.INSTANCE;
    }

    private static final void LockTypeButton(final String str, final LockType lockType, final LockType lockType2, final Function1<? super LockType, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1716148163);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LockTypeButton)P(!1,3,2):SettingsScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(lockType.ordinal()) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(lockType2.ordinal()) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 2048 : 1024;
        }
        if ((i2 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1716148163, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.LockTypeButton (SettingsScreen.kt:116)");
            }
            if (lockType == lockType2) {
                composerStartRestartGroup.startReplaceGroup(49061809);
                ComposerKt.sourceInformation(composerStartRestartGroup, "117@9668L18,117@9688L15,117@9651L52");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 49062319, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean z = ((i2 & 7168) == 2048) | ((i2 & 112) == 32);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda40
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.LockTypeButton$lambda$138$lambda$137(function1, lockType);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1265647368, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SettingsScreenKt.LockTypeButton$lambda$139(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composer2, 805306368, 510);
                composer2.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(49063673);
                ComposerKt.sourceInformation(composerStartRestartGroup, "117@9734L18,117@9754L15,117@9709L60");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 49064431, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean z2 = ((i2 & 7168) == 2048) | ((i2 & 112) == 32);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.LockTypeButton$lambda$141$lambda$140(function1, lockType);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                ButtonKt.OutlinedButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-396990381, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SettingsScreenKt.LockTypeButton$lambda$142(str, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composerStartRestartGroup, 54), composer2, 805306368, 510);
                composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.SettingsScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.LockTypeButton$lambda$143(str, lockType, lockType2, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit LockTypeButton$lambda$138$lambda$137(Function1 function1, LockType lockType) {
        function1.invoke(lockType);
        return Unit.INSTANCE;
    }

    static final Unit LockTypeButton$lambda$139(String str, RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C117@9690L11:SettingsScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1265647368, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LockTypeButton.<anonymous> (SettingsScreen.kt:117)");
            }
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LockTypeButton$lambda$141$lambda$140(Function1 function1, LockType lockType) {
        function1.invoke(lockType);
        return Unit.INSTANCE;
    }

    static final Unit LockTypeButton$lambda$142(String str, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C117@9756L11:SettingsScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-396990381, i, -1, "com.mohammedalhzmi.masrofmanager.ui.LockTypeButton.<anonymous> (SettingsScreen.kt:117)");
            }
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$121$lambda$88$lambda$87(MutableState mutableState, String it) throws IOException {
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

    static final Unit SettingsScreen$lambda$121$lambda$93$lambda$92(MutableState mutableState, String it) throws IOException {
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

    static final Unit SettingsScreen$lambda$121$lambda$98$lambda$97(MutableState mutableState, String it) throws IOException {
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

    static final Unit SettingsScreen$lambda$121$lambda$118$lambda$117(MutableState mutableState, String it) throws IOException {
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
