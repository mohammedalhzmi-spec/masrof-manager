package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.LockKt;
import androidx.compose.material.icons.filled.PersonKt;
import androidx.compose.material3.IconKt;
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
import androidx.compose.p000ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
public final class ComposableSingletons$LoginScreenKt {
    public static final ComposableSingletons$LoginScreenKt INSTANCE = new ComposableSingletons$LoginScreenKt();
    private static Function3<AnimatedVisibilityScope, Composer, Integer, Unit> lambda$1465886474 = ComposableLambdaKt.composableLambdaInstance(1465886474, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LoginScreenKt.lambda_1465886474$lambda$1((AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$581704104 = ComposableLambdaKt.composableLambdaInstance(581704104, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda_581704104$lambda$2((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1724221926 = ComposableLambdaKt.composableLambdaInstance(1724221926, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda_1724221926$lambda$3((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-102591521, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f1145lambda$102591521 = ComposableLambdaKt.composableLambdaInstance(-102591521, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda__102591521$lambda$4((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1654592355, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f1147lambda$1654592355 = ComposableLambdaKt.composableLambdaInstance(-1654592355, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda__1654592355$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$887911309 = ComposableLambdaKt.composableLambdaInstance(887911309, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda_887911309$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1165264565, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f1146lambda$1165264565 = ComposableLambdaKt.composableLambdaInstance(-1165264565, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$LoginScreenKt.lambda__1165264565$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1513939440 = ComposableLambdaKt.composableLambdaInstance(1513939440, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LoginScreenKt.lambda_1513939440$lambda$8((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1913019591, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f1148lambda$1913019591 = ComposableLambdaKt.composableLambdaInstance(-1913019591, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LoginScreenKt.lambda__1913019591$lambda$9((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$97562341 = ComposableLambdaKt.composableLambdaInstance(97562341, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$LoginScreenKt.lambda_97562341$lambda$10((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-102591521$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7681getLambda$102591521$app() {
        return f1145lambda$102591521;
    }

    /* JADX INFO: renamed from: getLambda$-1165264565$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7682getLambda$1165264565$app() {
        return f1146lambda$1165264565;
    }

    /* JADX INFO: renamed from: getLambda$-1654592355$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7683getLambda$1654592355$app() {
        return f1147lambda$1654592355;
    }

    /* JADX INFO: renamed from: getLambda$-1913019591$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7684getLambda$1913019591$app() {
        return f1148lambda$1913019591;
    }

    public final Function3<AnimatedVisibilityScope, Composer, Integer, Unit> getLambda$1465886474$app() {
        return lambda$1465886474;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1513939440$app() {
        return lambda$1513939440;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1724221926$app() {
        return lambda$1724221926;
    }

    public final Function2<Composer, Integer, Unit> getLambda$581704104$app() {
        return lambda$581704104;
    }

    public final Function2<Composer, Integer, Unit> getLambda$887911309$app() {
        return lambda$887911309;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$97562341$app() {
        return lambda$97562341;
    }

    static final Unit lambda_1465886474$lambda$1(AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation(composer, "C71@3767L425:LoginScreen.kt#ska5t9");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1465886474, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$1465886474.<anonymous> (LoginScreen.kt:71)");
        }
        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
        ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
        Modifier.Companion companion = Modifier.INSTANCE;
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, composer, 48);
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
        ComposerKt.sourceInformationMarkerStart(composer, -2071993283, "C72@3848L90,73@3959L104,74@4084L90:LoginScreen.kt#ska5t9");
        TextKt.m3342Text4IGK_g("الجمهورية اليمنية", (Modifier) null, LoginScreenKt.GovNavy, TextUnitKt.getSp(18), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 131026);
        TextKt.m3342Text4IGK_g("صندوق النظافة والتحسين م/إب", (Modifier) null, LoginScreenKt.GovNavy, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 131026);
        TextKt.m3342Text4IGK_g("فرع مديرية الحزم", (Modifier) null, LoginScreenKt.GovGreen, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 200070, 0, 131026);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        composer.endNode();
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        ComposerKt.sourceInformationMarkerEnd(composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1724221926$lambda$3(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C84@5546L32:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1724221926, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$1724221926.<anonymous> (LoginScreen.kt:84)");
            }
            IconKt.m2799Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_581704104$lambda$2(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C84@5493L33:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(581704104, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$581704104.<anonymous> (LoginScreen.kt:84)");
            }
            TextKt.m3342Text4IGK_g("البريد الإلكتروني المعتمد", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__102591521$lambda$4(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C85@5753L20:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-102591521, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$-102591521.<anonymous> (LoginScreen.kt:85)");
            }
            TextKt.m3342Text4IGK_g("اسم المستخدم", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1654592355$lambda$5(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C85@5793L32:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1654592355, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$-1654592355.<anonymous> (LoginScreen.kt:85)");
            }
            IconKt.m2799Iconww6aTOc(PersonKt.getPerson(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_887911309$lambda$6(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C86@5983L19:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(887911309, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$887911309.<anonymous> (LoginScreen.kt:86)");
            }
            TextKt.m3342Text4IGK_g("كلمة المرور", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1165264565$lambda$7(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C86@6022L30:LoginScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1165264565, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$-1165264565.<anonymous> (LoginScreen.kt:86)");
            }
            IconKt.m2799Iconww6aTOc(LockKt.getLock(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1513939440$lambda$8(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C90@7338L79:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1513939440, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$1513939440.<anonymous> (LoginScreen.kt:90)");
            }
            TextKt.m3342Text4IGK_g("نسيت كلمة المرور؟ إرسال رابط استعادة", (Modifier) null, LoginScreenKt.GovNavy, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3462, 0, 131058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1913019591$lambda$9(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C92@7793L45:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1913019591, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$-1913019591.<anonymous> (LoginScreen.kt:92)");
            }
            TextKt.m3342Text4IGK_g("رجوع إلى شاشة الدخول", (Modifier) null, LoginScreenKt.GovNavy, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_97562341$lambda$10(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C93@7950L46:LoginScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(97562341, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$LoginScreenKt.lambda$97562341.<anonymous> (LoginScreen.kt:93)");
            }
            TextKt.m3342Text4IGK_g("طلب إنشاء حساب مستخدم", (Modifier) null, LoginScreenKt.GovNavy, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 390, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
