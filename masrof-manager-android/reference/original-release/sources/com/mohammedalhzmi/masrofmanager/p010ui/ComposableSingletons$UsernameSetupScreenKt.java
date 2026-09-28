package com.mohammedalhzmi.masrofmanager.p010ui;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UsernameSetupScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
public final class ComposableSingletons$UsernameSetupScreenKt {
    public static final ComposableSingletons$UsernameSetupScreenKt INSTANCE = new ComposableSingletons$UsernameSetupScreenKt();

    /* JADX INFO: renamed from: lambda$-1812584931, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f1180lambda$1812584931 = ComposableLambdaKt.composableLambdaInstance(-1812584931, false, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$UsernameSetupScreenKt.lambda__1812584931$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$166631283 = ComposableLambdaKt.composableLambdaInstance(166631283, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$UsernameSetupScreenKt.lambda_166631283$lambda$1((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-575041163, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f1181lambda$575041163 = ComposableLambdaKt.composableLambdaInstance(-575041163, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$UsernameSetupScreenKt.lambda__575041163$lambda$2((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1812584931$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m7716getLambda$1812584931$app() {
        return f1180lambda$1812584931;
    }

    /* JADX INFO: renamed from: getLambda$-575041163$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m7717getLambda$575041163$app() {
        return f1181lambda$575041163;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$166631283$app() {
        return lambda$166631283;
    }

    static final Unit lambda__1812584931$lambda$0(Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C26@1514L20:UsernameSetupScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1812584931, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt.lambda$-1812584931.<anonymous> (UsernameSetupScreen.kt:26)");
            }
            TextKt.m3342Text4IGK_g("اسم المستخدم", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_166631283$lambda$1(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C28@1889L32:UsernameSetupScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(166631283, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt.lambda$166631283.<anonymous> (UsernameSetupScreen.kt:28)");
            }
            TextKt.m3342Text4IGK_g("حفظ اسم المستخدم والدخول", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__575041163$lambda$2(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C29@2011L12:UsernameSetupScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-575041163, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$UsernameSetupScreenKt.lambda$-575041163.<anonymous> (UsernameSetupScreen.kt:29)");
            }
            TextKt.m3342Text4IGK_g("رجوع", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
