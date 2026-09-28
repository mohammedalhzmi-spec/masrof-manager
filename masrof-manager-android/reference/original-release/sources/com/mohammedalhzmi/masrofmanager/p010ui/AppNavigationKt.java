package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.p000ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.view.PointerIconCompat;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import com.mohammedalhzmi.masrofmanager.p010ui.forms.PaymentOrderFormScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.forms.ReceiptFormScreenKt;
import com.mohammedalhzmi.masrofmanager.p010ui.forms.RequestFormScreenKt;
import com.mohammedalhzmi.masrofmanager.util.AppPermission;
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering;
import com.mohammedalhzmi.masrofmanager.util.RolePreferences;
import io.ktor.http.LinkHeader;
import io.ktor.sse.ServerSentEventKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppNavigation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m914d2 = {"AppNavigation", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Landroidx/compose/runtime/Composer;I)V", "app"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppNavigationKt {
    static final Unit AppNavigation$lambda$84(MasrofViewModel masrofViewModel, int i, Composer composer, int i2) {
        AppNavigation(masrofViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void AppNavigation(final MasrofViewModel viewModel, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1639183499);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AppNavigation)17@774L23,18@829L7,19@969L4504,19@841L4632:AppNavigation.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(viewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1639183499, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation (AppNavigation.kt:16)");
            }
            final NavHostController navHostControllerRememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], composerStartRestartGroup, 0);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            String str = viewModel.getIsManagerSession() ? "manager_dashboard" : "dashboard";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 997668525, "CC(remember):AppNavigation.kt#9igjgp");
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(navHostControllerRememberNavController) | composerStartRestartGroup.changedInstance(viewModel) | composerStartRestartGroup.changedInstance(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppNavigationKt.AppNavigation$lambda$83$lambda$82(navHostControllerRememberNavController, viewModel, context, (NavGraphBuilder) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            NavHostKt.NavHost(navHostControllerRememberNavController, str, null, null, null, null, null, null, null, null, (Function1) objRememberedValue, composerStartRestartGroup, 0, 0, PointerIconCompat.TYPE_GRAB);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppNavigationKt.AppNavigation$lambda$84(viewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit AppNavigation$lambda$83$lambda$82(final NavHostController navHostController, final MasrofViewModel masrofViewModel, final Context context, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, "manager_dashboard", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-388553742, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$2(navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "dashboard", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1369267301, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "document_book", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1762266246, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$18(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "book_editor/{tag}", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(2139702105, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$25(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "select_type", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1746703160, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$29(navHostController, context, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "new_editor/{type}", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1353704215, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$37(context, masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "request_form", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(960705270, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$40(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "order_form", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(567706325, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$43(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "receipt_form", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(174707380, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$46(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        DocumentType[] documentTypeArrValues = DocumentType.values();
        ArrayList<DocumentType> arrayList = new ArrayList();
        for (DocumentType documentType : documentTypeArrValues) {
            if (documentType != DocumentType.REQUEST && documentType != DocumentType.ORDER && documentType != DocumentType.RECEIPT) {
                arrayList.add(documentType);
            }
        }
        for (final DocumentType documentType2 : arrayList) {
            String lowerCase = documentType2.name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            NavGraphBuilderKt.composable$default(NavHost, lowerCase + "_form", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1954430354, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function4
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$51$lambda$50(masrofViewModel, navHostController, documentType2, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }), 254, null);
        }
        NavGraphBuilderKt.composable$default(NavHost, "edit/{type}/{id}", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-218291565, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$57(masrofViewModel, context, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "settings", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(2123384103, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$64(navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "users", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1730385158, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$67(context, masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "updates", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1337386213, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$70(navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "print_preview/{documentIds}", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(944387268, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$77(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "canvas/{type}", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(551388323, true, new Function4() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$81(masrofViewModel, navHostController, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$2(final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C20@1052L39,20@1013L79:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-388553742, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:20)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 262010041, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$2$lambda$1$lambda$0(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        ManagerDashboardScreenKt.ManagerDashboardScreen((Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$2$lambda$1$lambda$0(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "dashboard", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C22@1168L41,22@1211L43,22@1256L47,22@1305L161,25@1468L38,22@1141L366:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1369267301, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:22)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1605882332, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda43
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13$lambda$4$lambda$3(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1605880954, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13$lambda$6$lambda$5(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function1 = (Function0) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1605879510, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance3 = composer.changedInstance(navHostController);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13$lambda$8$lambda$7(navHostController, (String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Function1 function2 = (Function1) objRememberedValue3;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1605877828, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance4 = composer.changedInstance(navHostController);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13$lambda$10$lambda$9(navHostController, (String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        Function1 function3 = (Function1) objRememberedValue4;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1605872735, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance5 = composer.changedInstance(navHostController);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$13$lambda$12$lambda$11(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        DashboardScreenKt.DashboardScreen(masrofViewModel, function0, function1, function2, function3, (Function0) objRememberedValue5, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13$lambda$4$lambda$3(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "select_type", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13$lambda$6$lambda$5(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "document_book", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13$lambda$8$lambda$7(NavHostController navHostController, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        NavController.navigate$default((NavController) navHostController, "print_preview/" + it, (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13$lambda$10$lambda$9(NavHostController navHostController, String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        List listSplit$default = StringsKt.split$default((CharSequence) token, new String[]{ServerSentEventKt.COLON}, false, 0, 6, (Object) null);
        if (listSplit$default.size() == 2) {
            NavController.navigate$default((NavController) navHostController, "edit/" + listSplit$default.get(0) + DomExceptionUtils.SEPARATOR + listSplit$default.get(1), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$13$lambda$12$lambda$11(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "settings", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$18(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C27@1586L32,27@1620L79,27@1556L143:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1762266246, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:27)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1320746726, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$18$lambda$15$lambda$14(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1320745591, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda38
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$18$lambda$17$lambda$16(navHostController, (String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        DocumentBookScreenKt.DocumentBookScreen(masrofViewModel, function0, (Function1) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$18$lambda$15$lambda$14(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$18$lambda$17$lambda$16(NavHostController navHostController, String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        NavController.navigate$default((NavController) navHostController, "book_editor/" + Uri.encode(tag), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$25(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry entry, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(entry, "entry");
        ComposerKt.sourceInformation(composer, "C30@1900L55,30@1972L57,30@2031L32,30@1855L208:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2139702105, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:29)");
        }
        Bundle arguments = entry.getArguments();
        String string = arguments != null ? arguments.getString("tag") : null;
        if (string == null) {
            string = "";
        }
        String strDecode = Uri.decode(string);
        Intrinsics.checkNotNull(strDecode);
        ComposerKt.sourceInformationMarkerStart(composer, -1035606768, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$25$lambda$20$lambda$19(navHostController, (String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function1 function1 = (Function1) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1035604462, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$25$lambda$22$lambda$21(navHostController, (DocumentType) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function1 function2 = (Function1) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -1035602599, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance3 = composer.changedInstance(navHostController);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$25$lambda$24$lambda$23(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        BookEditorScreenKt.BookEditorScreen(masrofViewModel, strDecode, function1, function2, (Function0) objRememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$25$lambda$20$lambda$19(NavHostController navHostController, String ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        NavController.navigate$default((NavController) navHostController, "print_preview/" + ids, (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$25$lambda$22$lambda$21(NavHostController navHostController, DocumentType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        NavController.navigate$default((NavController) navHostController, "canvas/" + type.name(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$25$lambda$24$lambda$23(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$29(final NavHostController navHostController, Context context, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C32@2234L51,32@2110L175:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1746703160, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:32)");
        }
        DocumentType[] documentTypeArrValues = DocumentType.values();
        ArrayList arrayList = new ArrayList();
        for (DocumentType documentType : documentTypeArrValues) {
            if (RolePreferences.INSTANCE.canCreate(context, documentType)) {
                arrayList.add(documentType);
            }
        }
        Set set = CollectionsKt.toSet(arrayList);
        ComposerKt.sourceInformationMarkerStart(composer, -750471893, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$29$lambda$28$lambda$27(navHostController, (DocumentType) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        DocumentTypeSelectionScreenKt.DocumentTypeSelectionScreen(set, (Function1) objRememberedValue, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$29$lambda$28$lambda$27(NavHostController navHostController, DocumentType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        NavController.navigate$default((NavController) navHostController, "new_editor/" + it.name(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$37(Context context, final MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry entry, Composer composer, int i) {
        Object objM7781constructorimpl;
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(entry, "entry");
        ComposerKt.sourceInformation(composer, "C36@2844L3,36@2858L32,36@2904L59,36@2784L180:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1353704215, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:34)");
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            Bundle arguments = entry.getArguments();
            String string = arguments != null ? arguments.getString(LinkHeader.Parameters.Type) : null;
            if (string == null) {
                string = "";
            }
            objM7781constructorimpl = Result.m7781constructorimpl(DocumentType.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        DocumentType documentType = DocumentType.REQUEST;
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = documentType;
        }
        DocumentType documentType2 = (DocumentType) objM7781constructorimpl;
        Document document = new Document(0L, documentType2, StringsKt.padStart(String.valueOf(DocumentNumbering.INSTANCE.next(context, documentType2)), 4, '0'), "", "", null, "", "", "", "", "", DocumentStatus.DRAFT, 0, 0L, false, null, 0L, null, null, null, null, null, null, null, null, null, null, null, null, 536866817, null);
        ComposerKt.sourceInformationMarkerStart(composer, -465323558, "CC(remember):AppNavigation.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$37$lambda$32$lambda$31((String) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function1 function1 = (Function1) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -465323081, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$37$lambda$34$lambda$33(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function0 = (Function0) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, -465321582, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(masrofViewModel) | composer.changedInstance(navHostController);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$37$lambda$36$lambda$35(masrofViewModel, navHostController, (Document) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen(masrofViewModel, document, function1, function0, (Function1) objRememberedValue3, composer, 384, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$37$lambda$32$lambda$31(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$37$lambda$34$lambda$33(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$37$lambda$36$lambda$35(MasrofViewModel masrofViewModel, NavHostController navHostController, Document it) {
        Intrinsics.checkNotNullParameter(it, "it");
        masrofViewModel.addDocument(it);
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$40(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C38@3058L32,38@3012L79:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(960705270, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:38)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -180202602, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$40$lambda$39$lambda$38(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        RequestFormScreenKt.RequestFormScreen(masrofViewModel, (Function0) objRememberedValue, null, null, composer, 0, 12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$40$lambda$39$lambda$38(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$43(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C39@3180L32,39@3129L84:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(567706325, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:39)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 104933461, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$43$lambda$42$lambda$41(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        PaymentOrderFormScreenKt.PaymentOrderFormScreen(masrofViewModel, (Function0) objRememberedValue, null, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$43$lambda$42$lambda$41(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$46(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C40@3299L32,40@3253L79:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(174707380, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:40)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 390069204, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$46$lambda$45$lambda$44(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        ReceiptFormScreenKt.ReceiptFormScreen(masrofViewModel, (Function0) objRememberedValue, null, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$46$lambda$45$lambda$44(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$51$lambda$50(MasrofViewModel masrofViewModel, final NavHostController navHostController, DocumentType documentType, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C43@3599L32,43@3553L100:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1954430354, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:43)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1306859762, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.m738xb1e62580(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        RequestFormScreenKt.RequestFormScreen(masrofViewModel, (Function0) objRememberedValue, null, documentType, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: AppNavigation$lambda$83$lambda$82$lambda$51$lambda$50$lambda$49$lambda$48 */
    static final Unit m738xb1e62580(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$57(MasrofViewModel masrofViewModel, Context context, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry entry, Composer composer, int i) {
        String string;
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(entry, "entry");
        ComposerKt.sourceInformation(composer, "C:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-218291565, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:47)");
        }
        Bundle arguments = entry.getArguments();
        if (arguments != null) {
            arguments.getString(LinkHeader.Parameters.Type);
        }
        Bundle arguments2 = entry.getArguments();
        Object obj = null;
        Long longOrNull = (arguments2 == null || (string = arguments2.getString("id")) == null) ? null : StringsKt.toLongOrNull(string);
        for (Object obj2 : masrofViewModel.getAllDocuments().getValue()) {
            long id = ((Document) obj2).getId();
            if (longOrNull != null && id == longOrNull.longValue()) {
                obj = obj2;
                break;
            }
        }
        Document document = (Document) obj;
        if (!RolePreferences.INSTANCE.can(context, AppPermission.EDIT)) {
            navHostController.popBackStack();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }
        if (document != null) {
            composer.startReplaceGroup(675216154);
            ComposerKt.sourceInformation(composer, "54@4238L55,55@4320L32,51@4103L263");
            ComposerKt.sourceInformationMarkerStart(composer, 675220266, "CC(remember):AppNavigation.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(navHostController);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$57$lambda$54$lambda$53(navHostController, (String) obj3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 675222867, "CC(remember):AppNavigation.kt#9igjgp");
            boolean zChangedInstance2 = composer.changedInstance(navHostController);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$57$lambda$56$lambda$55(navHostController);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            UniversalDocumentEditorScreenKt.UniversalDocumentEditorScreen(masrofViewModel, document, function1, (Function0) objRememberedValue2, null, composer, 0, 16);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(675224961);
            composer.endReplaceGroup();
            navHostController.popBackStack();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$57$lambda$54$lambda$53(NavHostController navHostController, String ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        NavController.navigate$default((NavController) navHostController, "print_preview/" + ids, (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$57$lambda$56$lambda$55(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$64(final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C58@4459L32,58@4493L35,58@4530L37,58@4444L124:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2123384103, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:58)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 462657959, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$64$lambda$59$lambda$58(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function0 function0 = (Function0) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 462659050, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$64$lambda$61$lambda$60(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function0 function1 = (Function0) objRememberedValue2;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 462660236, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance3 = composer.changedInstance(navHostController);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$64$lambda$63$lambda$62(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        SettingsScreenKt.SettingsScreen(function0, function1, (Function0) objRememberedValue3, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$64$lambda$59$lambda$58(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$64$lambda$61$lambda$60(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "users", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$64$lambda$63$lambda$62(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "updates", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$67(Context context, MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1730385158, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:59)");
        }
        if (RolePreferences.INSTANCE.can(context, AppPermission.SETTINGS)) {
            composer.startReplaceGroup(747795270);
            ComposerKt.sourceInformation(composer, "59@4691L32,59@4659L64");
            ComposerKt.sourceInformationMarkerStart(composer, 747796262, "CC(remember):AppNavigation.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(navHostController);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$67$lambda$66$lambda$65(navHostController);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            UserManagementScreenKt.UserManagementScreen(masrofViewModel, (Function0) objRememberedValue, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(747797908);
            composer.endReplaceGroup();
            navHostController.popBackStack();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$67$lambda$66$lambda$65(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$70(final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation(composer, "C60@4811L32,60@4792L51:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1337386213, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:60)");
        }
        ComposerKt.sourceInformationMarkerStart(composer, 1032929893, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$70$lambda$69$lambda$68(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        UpdateCenterScreenKt.UpdateCenterScreen((Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$70$lambda$69$lambda$68(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$77(final MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry entry, Composer composer, int i) {
        String string;
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(entry, "entry");
        ComposerKt.sourceInformation(composer, "C62@5013L180,64@5195L32,62@4919L308:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(944387268, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:62)");
        }
        Bundle arguments = entry.getArguments();
        if (arguments == null || (string = arguments.getString("documentIds")) == null) {
            string = "";
        }
        String str = string;
        ComposerKt.sourceInformationMarkerStart(composer, 1318069016, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$77$lambda$74$lambda$73(masrofViewModel, navHostController, ((Long) obj).longValue());
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Function1 function1 = (Function1) objRememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerStart(composer, 1318074692, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance2 = composer.changedInstance(navHostController);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$77$lambda$76$lambda$75(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        PrintPreviewScreenKt.PrintPreviewScreen(masrofViewModel, str, function1, (Function0) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$77$lambda$74$lambda$73(MasrofViewModel masrofViewModel, NavHostController navHostController, long j) {
        Object next;
        Iterator<T> it = masrofViewModel.getAllDocuments().getValue().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Document) next).getId() != j);
        Document document = (Document) next;
        if (document != null) {
            String lowerCase = document.getType().name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            NavController.navigate$default((NavController) navHostController, "edit/" + lowerCase + DomExceptionUtils.SEPARATOR + document.getId(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$77$lambda$76$lambda$75(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$81(MasrofViewModel masrofViewModel, final NavHostController navHostController, AnimatedContentScope composable, NavBackStackEntry entry, Composer composer, int i) {
        Object objM7781constructorimpl;
        String string;
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(entry, "entry");
        ComposerKt.sourceInformation(composer, "C66@5433L32,66@5285L180:AppNavigation.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(551388323, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AppNavigation.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:66)");
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            Bundle arguments = entry.getArguments();
            if (arguments == null || (string = arguments.getString(LinkHeader.Parameters.Type)) == null) {
                string = "ORDER";
            }
            objM7781constructorimpl = Result.m7781constructorimpl(DocumentType.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        DocumentType documentType = DocumentType.ORDER;
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = documentType;
        }
        DocumentType documentType2 = (DocumentType) objM7781constructorimpl;
        ComposerKt.sourceInformationMarkerStart(composer, 1603206115, "CC(remember):AppNavigation.kt#9igjgp");
        boolean zChangedInstance = composer.changedInstance(navHostController);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.AppNavigationKt$$ExternalSyntheticLambda40
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(navHostController);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        CanvasEditorScreenKt.CanvasEditorScreen(masrofViewModel, documentType2, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(NavHostController navHostController) {
        navHostController.popBackStack();
        return Unit.INSTANCE;
    }
}
