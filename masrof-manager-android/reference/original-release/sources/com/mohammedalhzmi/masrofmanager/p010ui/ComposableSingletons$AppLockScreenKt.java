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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppLockScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
public final class ComposableSingletons$AppLockScreenKt {
    public static final ComposableSingletons$AppLockScreenKt INSTANCE = new ComposableSingletons$AppLockScreenKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$837928121 = ComposableLambdaKt.composableLambdaInstance(837928121, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AppLockScreenKt.lambda_837928121$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$475796493 = ComposableLambdaKt.composableLambdaInstance(475796493, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AppLockScreenKt.lambda_475796493$lambda$1((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1236186521 = ComposableLambdaKt.composableLambdaInstance(1236186521, false, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$AppLockScreenKt.lambda_1236186521$lambda$2((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1236186521$app() {
        return lambda$1236186521;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$475796493$app() {
        return lambda$475796493;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$837928121$app() {
        return lambda$837928121;
    }

    static final Unit lambda_837928121$lambda$0(RowScope Button, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation(composer, "C35@1996L19:AppLockScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(837928121, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt.lambda$837928121.<anonymous> (AppLockScreen.kt:35)");
            }
            TextKt.m3342Text4IGK_g("فتح التطبيق", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_475796493$lambda$1(RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C37@2140L33:AppLockScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(475796493, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt.lambda$475796493.<anonymous> (AppLockScreen.kt:37)");
            }
            TextKt.m3342Text4IGK_g("فتح بالبصمة / أمان الهاتف", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1236186521$lambda$2(RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C39@2287L17:AppLockScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1236186521, i, -1, "com.mohammedalhzmi.masrofmanager.ui.ComposableSingletons$AppLockScreenKt.lambda$1236186521.<anonymous> (AppLockScreen.kt:39)");
            }
            TextKt.m3342Text4IGK_g("مسح النقش", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 6, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
