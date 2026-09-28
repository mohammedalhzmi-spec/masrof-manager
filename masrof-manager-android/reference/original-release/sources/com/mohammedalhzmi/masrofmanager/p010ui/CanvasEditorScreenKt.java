package com.mohammedalhzmi.masrofmanager.p010ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.p000ui.Alignment;
import androidx.compose.p000ui.ComposedModifierKt;
import androidx.compose.p000ui.Modifier;
import androidx.compose.p000ui.draw.AlphaKt;
import androidx.compose.p000ui.draw.ClipKt;
import androidx.compose.p000ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.p000ui.graphics.Color;
import androidx.compose.p000ui.graphics.ColorKt;
import androidx.compose.p000ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.p000ui.graphics.GraphicsLayerScope;
import androidx.compose.p000ui.graphics.Shape;
import androidx.compose.p000ui.input.pointer.PointerInputScope;
import androidx.compose.p000ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.p000ui.layout.ContentScale;
import androidx.compose.p000ui.layout.MeasurePolicy;
import androidx.compose.p000ui.node.ComposeUiNode;
import androidx.compose.p000ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.p000ui.text.TextLayoutResult;
import androidx.compose.p000ui.text.TextStyle;
import androidx.compose.p000ui.text.font.FontFamily;
import androidx.compose.p000ui.text.font.FontFamilyKt;
import androidx.compose.p000ui.text.font.FontKt;
import androidx.compose.p000ui.text.font.FontStyle;
import androidx.compose.p000ui.text.font.FontWeight;
import androidx.compose.p000ui.text.font.GenericFontFamily;
import androidx.compose.p000ui.text.input.VisualTransformation;
import androidx.compose.p000ui.text.style.TextAlign;
import androidx.compose.p000ui.text.style.TextDecoration;
import androidx.compose.p000ui.unit.C1786Dp;
import androidx.compose.p000ui.unit.Density;
import androidx.compose.p000ui.unit.IntOffset;
import androidx.compose.p000ui.unit.IntOffsetKt;
import androidx.compose.p000ui.unit.TextUnitKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProduceStateScope;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.example.C2530R;
import com.google.firebase.firestore.model.Values;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CanvasEditorScreen.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a{\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00112\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00112\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0015H\u0003¢\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010\u001c\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003¢\u0006\u0002\u0010\u001f\u001a\u001d\u0010 \u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003¢\u0006\u0002\u0010\u001f\u001a\u001d\u0010!\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003¢\u0006\u0002\u0010\u001f\u001aK\u0010\"\u001a\u00020\u00012\b\u0010#\u001a\u0004\u0018\u00010\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072$\u0010%\u001a \u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0(\u0012\u0004\u0012\u00020\u00010&H\u0003¢\u0006\u0002\u0010)\u001ak\u0010*\u001a\u00020\u00012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072B\u0010%\u001a>\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010+H\u0003¢\u0006\u0002\u0010,\u001a7\u0010-\u001a\u00020\u00012\u0006\u0010#\u001a\u00020.2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u00010\u0015H\u0003¢\u0006\u0002\u0010/\u001aM\u00100\u001a\u00020\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u000720\u00101\u001a,\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0015\u0012\u0004\u0012\u00020\u000102H\u0003¢\u0006\u0002\u00103\u001a\u0010\u00104\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u00065²\u0006\u0010\u00106\u001a\b\u0012\u0004\u0012\u00020\u000b0(X\u008a\u0084\u0002²\u0006\f\u00107\u001a\u0004\u0018\u000108X\u008a\u008e\u0002²\u0006\n\u00109\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010:\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010;\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010>\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\rX\u008a\u008e\u0002²\u0006\f\u0010?\u001a\u0004\u0018\u00010.X\u008a\u0084\u0002²\u0006\f\u0010@\u001a\u0004\u0018\u00010AX\u008a\u0084\u0002²\u0006\n\u0010B\u001a\u00020'X\u008a\u008e\u0002²\u0006\n\u0010C\u001a\u00020'X\u008a\u008e\u0002²\u0006\u0010\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a0(X\u008a\u008e\u0002²\u0006\n\u0010E\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010F\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010G\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010H\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010I\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010J\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010K\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010L\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010M\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010N\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010O\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010P\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010Q\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010R\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010S\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010T\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\n\u0010U\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010V\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010W\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010X\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u00020\u001aX\u008a\u008e\u0002"}, m914d2 = {"CanvasEditorScreen", "", "viewModel", "Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "onBack", "Lkotlin/Function0;", "(Lcom/mohammedalhzmi/masrofmanager/ui/MasrofViewModel;Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "CanvasElement", "element", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "selected", "", "grid", "onSelect", "onMove", "Lkotlin/Function2;", "", "onResize", "onRotate", "Lkotlin/Function1;", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "parseColor", "Landroidx/compose/ui/graphics/Color;", Values.VECTOR_MAP_VECTORS_KEY, "", "(Ljava/lang/String;)J", "DocumentQr", "modifier", "Landroidx/compose/ui/Modifier;", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "DocumentImage", "DocumentTable", "TableElementDialog", "initial", "onDismiss", "onSave", "Lkotlin/Function3;", "", "", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "TextElementDialog", "Lkotlin/Function9;", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function9;Landroidx/compose/runtime/Composer;II)V", "PageSettingsDialog", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "AiLayoutDialog", "onRun", "Lkotlin/Function4;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)V", "typeName", "app", "elements", "selectedId", "", "showTextDialog", "showEditDialog", "showPageDialog", "showAiDialog", "showFieldsDialog", "showTableDialog", "design", "bitmap", "Landroid/graphics/Bitmap;", "rows", "columns", "cells", "text", "bold", "italic", "underline", "family", "color", ContentDisposition.Parameters.Size, "align", "spacing", "orientation", "width", "height", "marginLeft", "marginTop", "marginRight", "marginBottom", "background", "key", "endpoint", "instruction", NotificationCompat.CATEGORY_STATUS}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class CanvasEditorScreenKt {

    /* JADX INFO: compiled from: CanvasEditorScreen.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit AiLayoutDialog$lambda$391(Function0 function0, Function4 function4, int i, Composer composer, int i2) {
        AiLayoutDialog(function0, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$184(MasrofViewModel masrofViewModel, DocumentType documentType, Function0 function0, int i, Composer composer, int i2) {
        CanvasEditorScreen(masrofViewModel, documentType, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CanvasElement$lambda$194(DesignElementEntity designElementEntity, boolean z, boolean z2, Function0 function0, Function2 function2, Function2 function3, Function1 function1, int i, Composer composer, int i2) {
        CanvasElement(designElementEntity, z, z2, function0, function2, function3, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DocumentImage$lambda$204(DesignElementEntity designElementEntity, Modifier modifier, int i, Composer composer, int i2) {
        DocumentImage(designElementEntity, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DocumentQr$lambda$200(DesignElementEntity designElementEntity, Modifier modifier, int i, Composer composer, int i2) {
        DocumentQr(designElementEntity, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DocumentTable$lambda$210(DesignElementEntity designElementEntity, Modifier modifier, int i, Composer composer, int i2) {
        DocumentTable(designElementEntity, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$365(DocumentDesignEntity documentDesignEntity, Function0 function0, Function1 function1, int i, Composer composer, int i2) {
        PageSettingsDialog(documentDesignEntity, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TableElementDialog$lambda$241(DesignElementEntity designElementEntity, Function0 function0, Function3 function3, int i, Composer composer, int i2) {
        TableElementDialog(designElementEntity, function0, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TextElementDialog$lambda$301(DesignElementEntity designElementEntity, Function0 function0, Function9 function9, int i, int i2, Composer composer, int i3) {
        TextElementDialog(designElementEntity, function0, function9, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
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
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v30 java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.ModVisitor.anonymousCallArgMod(ModVisitor.java:535)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.ModVisitor.processAnonymousConstructor(ModVisitor.java:528)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:111)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public static final void CanvasEditorScreen(com.mohammedalhzmi.masrofmanager.p010ui.MasrofViewModel r61, final com.mohammedalhzmi.masrofmanager.data.DocumentType r62, final kotlin.jvm.functions.Function0<kotlin.Unit> r63, androidx.compose.runtime.Composer r64, final int r65) {
        /*
            Method dump skipped, instruction units count: 3261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mohammedalhzmi.masrofmanager.p010ui.CanvasEditorScreenKt.CanvasEditorScreen(com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel, com.mohammedalhzmi.masrofmanager.data.DocumentType, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long CanvasEditorScreen$lambda$2(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean CanvasEditorScreen$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean CanvasEditorScreen$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean CanvasEditorScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean CanvasEditorScreen$lambda$14(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$15(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean CanvasEditorScreen$lambda$17(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void CanvasEditorScreen$lambda$21(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean CanvasEditorScreen$lambda$23(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void CanvasEditorScreen$lambda$24(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void CanvasEditorScreen$add(MasrofViewModel masrofViewModel, int i, String str, String str2, float f, float f2, float f3, float f4) {
        masrofViewModel.addDesignElement(new DesignElementEntity(0L, 0L, str, str2, f, f2, f3, f4, 0.0f, 0.0f, i, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33553152, null));
    }

    static /* synthetic */ void CanvasEditorScreen$add$default(MasrofViewModel masrofViewModel, int i, String str, String str2, float f, float f2, float f3, float f4, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str2 = "";
        }
        String str3 = str2;
        if ((i2 & 16) != 0) {
            f = 60.0f;
        }
        float f5 = f;
        if ((i2 & 32) != 0) {
            f2 = 160.0f;
        }
        CanvasEditorScreen$add(masrofViewModel, i, str, str3, f5, f2, (i2 & 64) != 0 ? 180.0f : f3, (i2 & 128) != 0 ? 70.0f : f4);
    }

    static final Unit CanvasEditorScreen$lambda$32$lambda$31(Context context, MasrofViewModel masrofViewModel, int i, Uri uri) {
        if (uri != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                context.getContentResolver().takePersistableUriPermission(uri, 1);
                Result.m7781constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            CanvasEditorScreen$add(masrofViewModel, i, "IMAGE", string, 55.0f, 150.0f, 220.0f, 150.0f);
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MasrofViewModel masrofViewModel, final int i, final ManagedActivityResultLauncher managedActivityResultLauncher, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final DesignElementEntity designElementEntity, final MutableState mutableState7, final MutableState mutableState8, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1843687274, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda134
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$36(mutableState, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1455852819, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$39(mutableState2, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1705954098, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda69
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$42(mutableState3, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1956055377, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda70
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$45(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-2088810640, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda71
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$48(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1838709361, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda72
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$51(managedActivityResultLauncher, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1588608082, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda73
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$54(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1338506803, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda74
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$57(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1088405524, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda75
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$60(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-838304245, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda76
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$63(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-542376943, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda145
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$66(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-292275664, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$69(masrofViewModel, i, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-42174385, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$72(mutableState4, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(207926894, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$75(mutableState5, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(458028173, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$79(mutableState6, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(708129452, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$82(masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(958230731, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$85(masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1208332010, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$88(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1458433289, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$91(masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(1708534568, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$95(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1379171886, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$99(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-1129070607, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$104(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-878969328, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$108(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-628868049, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda61
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$112(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-378766770, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda62
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$116(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(-128665491, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda63
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$120(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(121435788, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda64
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$124(designElementEntity, masrofViewModel, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(371537067, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda65
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$127(designElementEntity, mutableState7, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(621638346, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda66
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$130(designElementEntity, mutableState2, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyRow, null, null, ComposableLambdaKt.composableLambdaInstance(871739625, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda67
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$134(designElementEntity, masrofViewModel, mutableState8, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$36(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C77@4033L25,77@4016L58:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1843687274, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:77)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -2038022301, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda98
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m758x2751a438(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7643getLambda$1014034054$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$36$lambda$35$lambda$34 */
    static final Unit m758x2751a438(MutableState mutableState) {
        CanvasEditorScreen$lambda$6(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$39(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C78@4113L26,78@4096L61:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1455852819, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:78)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1084778803, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m759xbca9d01(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7654getLambda$330241245$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$39$lambda$38$lambda$37 */
    static final Unit m759xbca9d01(MutableState mutableState) {
        CanvasEditorScreen$lambda$21(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$42(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C79@4196L27,79@4179L68:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1705954098, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:79)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1255618259, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda116
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m760x2f926349(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7663getLambda$80139966$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$42$lambda$41$lambda$40 */
    static final Unit m760x2f926349(MutableState mutableState) {
        CanvasEditorScreen$lambda$18(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$45(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C80@4286L798,80@4269L841:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1956055377, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:80)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1426456945, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda50
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m761x140b5c12(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$169961313$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$45$lambda$44$lambda$43 */
    static final Unit m761x140b5c12(MasrofViewModel masrofViewModel, int i) {
        masrofViewModel.addDesignElement(new DesignElementEntity(0L, 0L, "TEXT", "الجمهورية اليمنية\nوزارة الإدارة والتنمية المحلية والريفية\nصندوق النظافة والتحسين م/إب\nفرع مديرية الحزم", 300.0f, 42.0f, 240.0f, 92.0f, 0.0f, 0.0f, i, "AMIRI", 13.0f, "#123B5D", true, false, false, null, null, 0.0f, false, false, "END", 1.35f, 0.0f, 20939520, null));
        masrofViewModel.addDesignElement(new DesignElementEntity(0L, 0L, "TEXT", "NO: {رقم المستند}\nالتاريخ: {التاريخ الهجري}\nالموافق: {التاريخ الميلادي}", 45.0f, 45.0f, 230.0f, 80.0f, 0.0f, 0.0f, i + 1, "AMIRI", 12.0f, "#222222", false, false, false, null, null, 0.0f, false, false, null, 1.45f, 0.0f, 25150208, null));
        masrofViewModel.addDesignElement(new DesignElementEntity(0L, 0L, "LINE", "", 42.0f, 155.0f, 511.0f, 4.0f, 0.0f, 0.0f, i + 2, null, 0.0f, null, false, false, false, null, "#123B5D", 2.0f, false, false, null, 0.0f, 0.0f, 32766720, null));
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$48(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C85@5149L118,85@5132L154:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2088810640, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:85)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1597297082, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda130
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m762xf88454db(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$420062592$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$48$lambda$47$lambda$46 */
    static final Unit m762xf88454db(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add(masrofViewModel, i, "TEXT", "مقدم الطلب\nالاسم: {اسم المستفيد}\nالتوقيع: .................................", 55.0f, 690.0f, 225.0f, 82.0f);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$51(final ManagedActivityResultLauncher managedActivityResultLauncher, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C86@5325L42,86@5308L77:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1838709361, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:86)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1768136615, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(managedActivityResultLauncher);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m763x1c4c1b0e(managedActivityResultLauncher);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$670163871$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$51$lambda$50$lambda$49 */
    static final Unit m763x1c4c1b0e(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch(new String[]{"image/*"});
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$54(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C87@5424L15,87@5407L52:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1588608082, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:87)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1938976099, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m764xc513ec(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$920265150$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$54$lambda$53$lambda$52 */
    static final Unit m764xc513ec(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add$default(masrofViewModel, i, "RECT", null, 0.0f, 0.0f, 0.0f, 0.0f, 248, null);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$57(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C88@5498L36,88@5481L72:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1338506803, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:88)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -2109815535, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda77
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m765xe53e0cb5(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1170366429$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$57$lambda$56$lambda$55 */
    static final Unit m765xe53e0cb5(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add$default(masrofViewModel, i, "CIRCLE", null, 90.0f, 260.0f, 0.0f, 0.0f, 200, null);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$60(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C89@5592L52,89@5575L85:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1088405524, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:89)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 2014312320, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda99
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m766xd2432913(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1420467708$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$60$lambda$59$lambda$58 */
    static final Unit m766xd2432913(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add$default(masrofViewModel, i, "LINE", null, 70.0f, 340.0f, 230.0f, 8.0f, 8, null);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$63(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C90@5699L54,90@5682L88:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-838304245, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:90)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1843472865, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda101
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m767xed7ecbc6(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1670568987$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$63$lambda$62$lambda$61 */
    static final Unit m767xed7ecbc6(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add$default(masrofViewModel, i, "ARROW", null, 70.0f, 380.0f, 230.0f, 24.0f, 8, null);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$66(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C91@5809L61,91@5792L96:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-542376943, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:91)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1533198738, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda138
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m768xd1f7c48f(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7661getLambda$76718079$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$66$lambda$65$lambda$64 */
    static final Unit m768xd1f7c48f(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add(masrofViewModel, i, "STICKER", "★", 140.0f, 420.0f, 58.0f, 58.0f);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$69(final MasrofViewModel masrofViewModel, final int i, LazyItemScope item, Composer composer, int i2) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C92@5927L68,92@5910L101:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-292275664, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:92)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1704038188, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel) | composer.changed(i);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda100
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m769xb670bd58(masrofViewModel, i);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$173383200$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$69$lambda$68$lambda$67 */
    static final Unit m769xb670bd58(MasrofViewModel masrofViewModel, int i) {
        CanvasEditorScreen$add(masrofViewModel, i, "QR", "{رقم المستند}", 230.0f, 420.0f, 80.0f, 80.0f);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$72(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C93@6058L25,93@6033L78:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-42174385, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:93)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1874877432, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda129
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m770xda3883a0(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7657getLambda$493129663$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$72$lambda$71$lambda$70 */
    static final Unit m770xda3883a0(MutableState mutableState) {
        CanvasEditorScreen$lambda$12(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$75(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C94@6150L23,94@6133L67:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(207926894, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:94)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -2045717147, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda41
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m771xbeb17c69(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$673585758$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$75$lambda$74$lambda$73 */
    static final Unit m771xbeb17c69(MutableState mutableState) {
        CanvasEditorScreen$lambda$15(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$79(final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C95@6247L16,95@6265L52,95@6222L95:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(458028173, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:95)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 2078410941, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda79
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m772xafc36ab3(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(7072895, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda90
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CanvasEditorScreenKt.m773x739c3f3c(mutableState, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$79$lambda$77$lambda$76 */
    static final Unit m772xafc36ab3(MutableState mutableState) {
        CanvasEditorScreen$lambda$24(mutableState, !CanvasEditorScreen$lambda$23(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$79$lambda$78 */
    static final Unit m773x739c3f3c(MutableState mutableState, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C95@6267L48:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(7072895, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:95)");
            }
            TextKt.m3342Text4IGK_g(CanvasEditorScreen$lambda$23(mutableState) ? "شبكة: تشغيل" : "شبكة: إيقاف", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$82(final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C96@6364L20,96@6339L64:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(708129452, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:96)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1907571488, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m774x686fe3bd(masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$257174174$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$82$lambda$81$lambda$80 */
    static final Unit m774x686fe3bd(MasrofViewModel masrofViewModel) {
        masrofViewModel.undo();
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$85(final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C97@6450L20,97@6425L64:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(958230731, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:97)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1736732031, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda137
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m775x4ce8dc86(masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$507275453$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$85$lambda$84$lambda$83 */
    static final Unit m775x4ce8dc86(MasrofViewModel masrofViewModel) {
        masrofViewModel.redo();
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$88(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C98@6564L41,98@6511L111:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1208332010, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:98)");
            }
            boolean z = designElementEntity != null;
            ComposerKt.sourceInformationMarkerStart(composer, 1565893491, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda128
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m776x3161d54f(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$757376732$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$88$lambda$87$lambda$86 */
    static final Unit m776x3161d54f(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.copyElement(designElementEntity);
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$91(final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C99@6669L28,99@6644L70:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1458433289, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:99)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1395053125, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m777x55299b82(masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1007478011$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$91$lambda$90$lambda$89 */
    static final Unit m777x55299b82(MasrofViewModel masrofViewModel) {
        masrofViewModel.pasteElement();
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$95(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C100@6789L48,100@6736L121:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1708534568, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:100)");
            }
            boolean z = designElementEntity != null;
            ComposerKt.sourceInformationMarkerStart(composer, 1224214584, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda123
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m778xdb203ca3(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1257579290$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$95$lambda$94$lambda$93 */
    static final Unit m778xdb203ca3(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.moveLayer(designElementEntity, 1);
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$99(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C101@6932L49,101@6879L121:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1379171886, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:101)");
            }
            boolean z = designElementEntity != null;
            ComposerKt.sourceInformationMarkerStart(composer, 1760713827, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m779x6116ddaf(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7651getLambda$1830127164$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$99$lambda$98$lambda$97 */
    static final Unit m779x6116ddaf(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.moveLayer(designElementEntity, -1);
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$104(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C102@7075L81,102@7158L69,102@7022L205:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1129070607, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:102)");
            }
            boolean z = designElementEntity != null;
            ComposerKt.sourceInformationMarkerStart(composer, 1589874402, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda108
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m748xdc18254(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1580025885, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda109
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CanvasEditorScreenKt.m749x8bc80746(designElementEntity, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$104$lambda$102$lambda$101 */
    static final Unit m748xdc18254(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, !designElementEntity.getLocked(), false, null, 0.0f, 0.0f, 32505855, null));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$104$lambda$103 */
    static final Unit m749x8bc80746(DesignElementEntity designElementEntity, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C102@7160L65:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1580025885, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:102)");
            }
            TextKt.m3342Text4IGK_g((designElementEntity == null || !designElementEntity.getLocked()) ? "قفل العنصر" : "فتح القفل", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$108(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C103@7310L109,103@7249L186:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-878969328, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:103)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 1419035229, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m750x5d002238(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7646getLambda$1329924606$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$108$lambda$107$lambda$106 */
    static final Unit m750x5d002238(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, RangesKt.coerceAtMost(designElementEntity.getFontSize() + 2.0f, 96.0f), null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33550335, null));
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$112(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C104@7518L109,104@7457L186:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-628868049, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:104)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 1248195772, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda47
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m751x5c313ff1(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7644getLambda$1079823327$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$112$lambda$111$lambda$110 */
    static final Unit m751x5c313ff1(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, RangesKt.coerceAtLeast(designElementEntity.getFontSize() - 2.0f, 8.0f), null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33550335, null));
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$116(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C105@7726L81,105@7665L160:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-378766770, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:105)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 1077356287, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m752xa3be3a75(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7664getLambda$829722048$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$116$lambda$115$lambda$114 */
    static final Unit m752xa3be3a75(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, "START", 0.0f, 0.0f, 29360127, null));
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$120(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C106@7908L82,106@7847L160:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-128665491, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:106)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 906516831, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda102
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m753x15cc74e(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7658getLambda$579620769$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$120$lambda$119$lambda$118 */
    static final Unit m753x15cc74e(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, "CENTER", 0.0f, 0.0f, 29360127, null));
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$124(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C107@8090L79,107@8029L158:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(121435788, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:107)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 735677371, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda107
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m754xea7c52b2(designElementEntity, masrofViewModel);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7653getLambda$329519490$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$124$lambda$123$lambda$122 */
    static final Unit m754xea7c52b2(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel) {
        if (designElementEntity != null) {
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, "END", 0.0f, 0.0f, 29360127, null));
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$127(DesignElementEntity designElementEntity, final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C108@8270L25,108@8209L105:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(371537067, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:108)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TEXT");
            ComposerKt.sourceInformationMarkerStart(composer, 564837860, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m755xe0260e95(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7662getLambda$79418211$app(), composer, 805306374, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$127$lambda$126$lambda$125 */
    static final Unit m755xe0260e95(MutableState mutableState) {
        CanvasEditorScreen$lambda$9(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$130(DesignElementEntity designElementEntity, final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C109@8398L26,109@8336L114:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(621638346, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:109)");
            }
            boolean zAreEqual = Intrinsics.areEqual(designElementEntity != null ? designElementEntity.getType() : null, "TABLE");
            ComposerKt.sourceInformationMarkerStart(composer, 393998436, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda122
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m756xebe15ccd(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, zAreEqual, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$170683068$app(), composer, 805306374, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$130$lambda$129$lambda$128 */
    static final Unit m756xebe15ccd(MutableState mutableState) {
        CanvasEditorScreen$lambda$21(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$134(final DesignElementEntity designElementEntity, final MasrofViewModel masrofViewModel, final MutableState mutableState, LazyItemScope item, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation(composer, "C110@8525L74,110@8472L144:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(871739625, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:110)");
            }
            boolean z = designElementEntity != null;
            ComposerKt.sourceInformationMarkerStart(composer, 223158739, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(designElementEntity) | composer.changedInstance(masrofViewModel);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m757xd500e831(designElementEntity, masrofViewModel, mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$420784347$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$136$lambda$135$lambda$134$lambda$133$lambda$132 */
    static final Unit m757xd500e831(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel, MutableState mutableState) {
        if (designElementEntity != null) {
            masrofViewModel.deleteDesignElement(designElementEntity);
            mutableState.setValue(null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$149$lambda$148$lambda$147$lambda$140$lambda$139 */
    static final Unit m780x2e973a7e(DesignElementEntity designElementEntity, MutableState mutableState) {
        mutableState.setValue(Long.valueOf(designElementEntity.getId()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$149$lambda$148$lambda$147$lambda$142$lambda$141 */
    static final Unit m781x3dfa8553(DesignElementEntity designElementEntity, MasrofViewModel masrofViewModel, MutableState mutableState, float f, float f2) {
        if (!designElementEntity.getLocked()) {
            float f3 = CanvasEditorScreen$lambda$23(mutableState) ? 8.0f : 1.0f;
            masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, designElementEntity.getX() + (MathKt.roundToInt(f / f3) * f3), designElementEntity.getY() + (MathKt.roundToInt(f2 / f3) * f3), 0.0f, 0.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554383, null));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$149$lambda$148$lambda$147$lambda$144$lambda$143 */
    static final Unit m782x4d5dd013(MasrofViewModel masrofViewModel, DesignElementEntity designElementEntity, float f, float f2) {
        masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, RangesKt.coerceAtLeast(designElementEntity.getWidth() + f, 24.0f), RangesKt.coerceAtLeast(designElementEntity.getHeight() + f2, 18.0f), 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554239, null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$156$lambda$149$lambda$148$lambda$147$lambda$146$lambda$145 */
    static final Unit m783x5cc11ad3(MasrofViewModel masrofViewModel, DesignElementEntity designElementEntity, float f) {
        masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, null, 0.0f, 0.0f, 0.0f, 0.0f, designElementEntity.getRotation() + f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554175, null));
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$156$lambda$155$lambda$154(State state, final MutableState mutableState, LazyListScope LazyRow) {
        Intrinsics.checkNotNullParameter(LazyRow, "$this$LazyRow");
        final List listSortedWith = CollectionsKt.sortedWith(CanvasEditorScreen$lambda$0(state), new Comparator() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$lambda$156$lambda$155$lambda$154$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((DesignElementEntity) t2).getZIndex()), Integer.valueOf(((DesignElementEntity) t).getZIndex()));
            }
        });
        final Function1 function1 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda121
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CanvasEditorScreenKt.CanvasEditorScreen$lambda$156$lambda$155$lambda$154$lambda$151((DesignElementEntity) obj);
            }
        };
        final C3873xf8ac6fb4 c3873xf8ac6fb4 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$lambda$156$lambda$155$lambda$154$$inlined$items$default$1
            @Override // kotlin.jvm.functions.Function1
            public final Void invoke(DesignElementEntity designElementEntity) {
                return null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((DesignElementEntity) obj);
            }
        };
        LazyRow.items(listSortedWith.size(), new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$lambda$156$lambda$155$lambda$154$$inlined$items$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return function1.invoke(listSortedWith.get(i));
            }
        }, new Function1<Integer, Object>() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$lambda$156$lambda$155$lambda$154$$inlined$items$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Object invoke(int i) {
                return c3873xf8ac6fb4.invoke(listSortedWith.get(i));
            }
        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$lambda$156$lambda$155$lambda$154$$inlined$items$default$4
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
                final DesignElementEntity designElementEntity = (DesignElementEntity) listSortedWith.get(i);
                composer.startReplaceGroup(-1148973997);
                ComposerKt.sourceInformation(composer, "C*128@10273L27,128@10310L42,128@10215L138:CanvasEditorScreen.kt#ska5t9");
                Long lCanvasEditorScreen$lambda$2 = CanvasEditorScreenKt.CanvasEditorScreen$lambda$2(mutableState);
                boolean z = lCanvasEditorScreen$lambda$2 != null && lCanvasEditorScreen$lambda$2.longValue() == designElementEntity.getId();
                ComposerKt.sourceInformationMarkerStart(composer, 1486958721, "CC(remember):CanvasEditorScreen.kt#9igjgp");
                boolean zChanged = composer.changed(designElementEntity);
                Object objRememberedValue = composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    final MutableState mutableState2 = mutableState;
                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$2$4$1$3$1$1
                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            mutableState2.setValue(Long.valueOf(designElementEntity.getId()));
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ChipKt.FilterChip(z, (Function0) objRememberedValue, ComposableLambdaKt.rememberComposableLambda(-370936653, true, new Function2<Composer, Integer, Unit>() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$CanvasEditorScreen$2$4$1$3$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer2, int i4) {
                        ComposerKt.sourceInformation(composer2, "C128@10312L38:CanvasEditorScreen.kt#ska5t9");
                        if ((i4 & 3) == 2 && composer2.getSkipping()) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-370936653, i4, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:128)");
                        }
                        TextKt.m3342Text4IGK_g(designElementEntity.getType() + " #" + designElementEntity.getId(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 0, 0, 131070);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, composer, 54), null, false, null, null, null, null, null, null, null, composer, 384, 0, 4088);
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }));
        return Unit.INSTANCE;
    }

    static final Object CanvasEditorScreen$lambda$156$lambda$155$lambda$154$lambda$151(DesignElementEntity it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getId());
    }

    static final Unit CanvasEditorScreen$lambda$158$lambda$157(MutableState mutableState) {
        CanvasEditorScreen$lambda$6(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$160$lambda$159(MasrofViewModel masrofViewModel, int i, MutableState mutableState, String text, boolean z, boolean z2, boolean z3, String family, String color, float f, String align, float f2) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(family, "family");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(align, "align");
        masrofViewModel.addDesignElement(new DesignElementEntity(0L, 0L, "TEXT", text, 40.0f, 70.0f, 280.0f, 70.0f, 0.0f, 0.0f, i, family, f, color, z, z2, z3, null, null, 0.0f, false, false, align, f2, 0.0f, 20841216, null));
        CanvasEditorScreen$lambda$6(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$162$lambda$161(MutableState mutableState) {
        CanvasEditorScreen$lambda$18(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$171(final MasrofViewModel masrofViewModel, final int i, final MutableState mutableState, Composer composer, int i2) {
        Composer composer2 = composer;
        ComposerKt.sourceInformation(composer2, "C138@11181L628:CanvasEditorScreen.kt#ska5t9");
        if ((i2 & 3) == 2 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1064736315, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous> (CanvasEditorScreen.kt:138)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer2, 6);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, -1379754021, "C:CanvasEditorScreen.kt#ska5t9");
            composer2.startReplaceGroup(-1845612600);
            ComposerKt.sourceInformation(composer2, "*140@11673L59,140@11770L15,140@11648L137");
            for (Pair pair : CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.m921to("{رقم المستند}", "رقم المستند"), TuplesKt.m921to("{اسم المستفيد}", "اسم المستفيد"), TuplesKt.m921to("{المبلغ}", "المبلغ"), TuplesKt.m921to("{المبلغ كتابة}", "المبلغ كتابةً"), TuplesKt.m921to("{الغرض}", "الغرض"), TuplesKt.m921to("{التاريخ الهجري}", "التاريخ الهجري"), TuplesKt.m921to("{التاريخ الميلادي}", "التاريخ الميلادي"), TuplesKt.m921to("{البند المالي}", "البند المالي"), TuplesKt.m921to("{مركز التكلفة}", "مركز التكلفة"), TuplesKt.m921to("{مصدر التمويل}", "مصدر التمويل")})) {
                final String str = (String) pair.component1();
                final String str2 = (String) pair.component2();
                ComposerKt.sourceInformationMarkerStart(composer2, 211851239, "CC(remember):CanvasEditorScreen.kt#9igjgp");
                boolean zChangedInstance = composer2.changedInstance(masrofViewModel) | composer2.changed(i) | composer2.changed(str2) | composer2.changed(str);
                Object objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda78
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CanvasEditorScreenKt.m784xf129e5ca(str2, str, masrofViewModel, i, mutableState);
                        }
                    };
                    composer2.updateRememberedValue(function0);
                    objRememberedValue = function0;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.OutlinedButton((Function0) objRememberedValue, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(988032254, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda80
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return CanvasEditorScreenKt.CanvasEditorScreen$lambda$171$lambda$170$lambda$169$lambda$168(str2, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), composer2, 805306416, 508);
                composer2 = composer;
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

    /* JADX INFO: renamed from: CanvasEditorScreen$lambda$171$lambda$170$lambda$169$lambda$167$lambda$166 */
    static final Unit m784xf129e5ca(String str, String str2, MasrofViewModel masrofViewModel, int i, MutableState mutableState) {
        CanvasEditorScreen$add$default(masrofViewModel, i, "TEXT", str + ": " + str2, 0.0f, 0.0f, 0.0f, 0.0f, 240, null);
        CanvasEditorScreen$lambda$18(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$171$lambda$170$lambda$169$lambda$168(String str, RowScope OutlinedButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(OutlinedButton, "$this$OutlinedButton");
        ComposerKt.sourceInformation(composer, "C140@11772L11:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(988032254, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:140)");
            }
            TextKt.m3342Text4IGK_g(str, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$165(final MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C143@11860L28,143@11839L68:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1081289824, i, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreen.<anonymous> (CanvasEditorScreen.kt:143)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -262564004, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda81
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.CanvasEditorScreen$lambda$165$lambda$164$lambda$163(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7648getLambda$1414590525$app(), composer, 805306374, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$165$lambda$164$lambda$163(MutableState mutableState) {
        CanvasEditorScreen$lambda$18(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$173$lambda$172(MutableState mutableState) {
        CanvasEditorScreen$lambda$9(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$175$lambda$174(MasrofViewModel masrofViewModel, DesignElementEntity designElementEntity, MutableState mutableState, String text, boolean z, boolean z2, boolean z3, String family, String color, float f, String align, float f2) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(family, "family");
        Intrinsics.checkNotNullParameter(color, "color");
        Intrinsics.checkNotNullParameter(align, "align");
        masrofViewModel.updateDesignElement(DesignElementEntity.copy$default(designElementEntity, 0L, 0L, null, text, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0, family, f, color, z, z2, z3, null, null, 0.0f, false, false, align, f2, 0.0f, 20842487, null));
        CanvasEditorScreen$lambda$9(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$177$lambda$176(MutableState mutableState) {
        CanvasEditorScreen$lambda$12(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$179$lambda$178(MasrofViewModel masrofViewModel, MutableState mutableState, DocumentDesignEntity updated) {
        Intrinsics.checkNotNullParameter(updated, "updated");
        masrofViewModel.updateDesign(updated);
        CanvasEditorScreen$lambda$12(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$181$lambda$180(MutableState mutableState) {
        CanvasEditorScreen$lambda$15(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit CanvasEditorScreen$lambda$183$lambda$182(CoroutineScope coroutineScope, Context context, State state, State state2, MasrofViewModel masrofViewModel, String key, String endpoint, String instruction, Function1 status) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        Intrinsics.checkNotNullParameter(status, "status");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new CanvasEditorScreenKt$CanvasEditorScreen$13$1$1(status, context, key, endpoint, instruction, state, state2, masrofViewModel, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:104:0x0277 A[PHI: r4 r5 r9 r35 r39
      0x0277: PHI (r4v33 kotlin.coroutines.Continuation) = 
      (r4v12 kotlin.coroutines.Continuation)
      (r4v13 kotlin.coroutines.Continuation)
      (r4v14 kotlin.coroutines.Continuation)
      (r4v15 kotlin.coroutines.Continuation)
      (r4v16 kotlin.coroutines.Continuation)
      (r4v34 kotlin.coroutines.Continuation)
     binds: [B:207:0x071a, B:203:0x06ea, B:199:0x0695, B:195:0x0629, B:123:0x03e5, B:103:0x0270] A[DONT_GENERATE, DONT_INLINE]
      0x0277: PHI (r5v16 int) = (r5v1 int), (r5v2 int), (r5v3 int), (r5v4 int), (r5v5 int), (r5v17 int) binds: [B:207:0x071a, B:203:0x06ea, B:199:0x0695, B:195:0x0629, B:123:0x03e5, B:103:0x0270] A[DONT_GENERATE, DONT_INLINE]
      0x0277: PHI (r9v24 androidx.compose.runtime.Composer) = 
      (r9v9 androidx.compose.runtime.Composer)
      (r9v12 androidx.compose.runtime.Composer)
      (r9v13 androidx.compose.runtime.Composer)
      (r9v14 androidx.compose.runtime.Composer)
      (r9v15 androidx.compose.runtime.Composer)
      (r9v25 androidx.compose.runtime.Composer)
     binds: [B:207:0x071a, B:203:0x06ea, B:199:0x0695, B:195:0x0629, B:123:0x03e5, B:103:0x0270] A[DONT_GENERATE, DONT_INLINE]
      0x0277: PHI (r35v10 int) = (r35v0 int), (r35v1 int), (r35v2 int), (r35v3 int), (r35v4 int), (r35v11 int) binds: [B:207:0x071a, B:203:0x06ea, B:199:0x0695, B:195:0x0629, B:123:0x03e5, B:103:0x0270] A[DONT_GENERATE, DONT_INLINE]
      0x0277: PHI (r39v10 java.lang.String) = 
      (r39v0 java.lang.String)
      (r39v1 java.lang.String)
      (r39v2 java.lang.String)
      (r39v3 java.lang.String)
      (r39v4 java.lang.String)
      (r39v11 java.lang.String)
     binds: [B:207:0x071a, B:203:0x06ea, B:199:0x0695, B:195:0x0629, B:123:0x03e5, B:103:0x0270] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:184:0x05b1  */
    private static final void CanvasElement(final DesignElementEntity designElementEntity, final boolean z, final boolean z2, final Function0<Unit> function0, final Function2<? super Float, ? super Float, Unit> function2, final Function2<? super Float, ? super Float, Unit> function3, final Function1<? super Float, Unit> function1, Composer composer, final int i) {
        Modifier.Companion companionM1225borderxT4_qwU$default;
        int i2;
        String str;
        Continuation continuation;
        int i3;
        Composer composer2;
        Object objM7781constructorimpl;
        GenericFontFamily sansSerif;
        GenericFontFamily genericFontFamilyFontFamily;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(1461631030);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CanvasElement)P(!1,6!1,5)161@13346L61,161@13464L32,161@13567L113,162@13685L3018:CanvasEditorScreen.kt#ska5t9");
        int i5 = (i & 6) == 0 ? (composerStartRestartGroup.changed(designElementEntity) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & 3072) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function0) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((599059 & i5) == 599058 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1461631030, i5, -1, "com.mohammedalhzmi.masrofmanager.ui.CanvasElement (CanvasEditorScreen.kt:160)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 173122099, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            int i6 = i5 & 14;
            boolean z3 = i6 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.CanvasElement$lambda$186$lambda$185(designElementEntity, (Density) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierM1705sizeVpY3zN4 = SizeKt.m1705sizeVpY3zN4(OffsetKt.offset(companion, (Function1) objRememberedValue), C1786Dp.m7249constructorimpl(designElementEntity.getWidth()), C1786Dp.m7249constructorimpl(designElementEntity.getHeight()));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 173125846, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean z4 = i6 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.CanvasElement$lambda$188$lambda$187(designElementEntity, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierAlpha = AlphaKt.alpha(GraphicsLayerModifierKt.graphicsLayer(modifierM1705sizeVpY3zN4, (Function1) objRememberedValue2), designElementEntity.getOpacity());
            Object[] objArr = {Long.valueOf(designElementEntity.getId()), Float.valueOf(designElementEntity.getX()), Float.valueOf(designElementEntity.getY())};
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 173129223, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean z5 = ((i5 & 7168) == 2048) | ((57344 & i5) == 16384);
            CanvasEditorScreenKt$CanvasElement$root$3$1 canvasEditorScreenKt$CanvasElement$root$3$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || canvasEditorScreenKt$CanvasElement$root$3$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                canvasEditorScreenKt$CanvasElement$root$3$1RememberedValue = new CanvasEditorScreenKt$CanvasElement$root$3$1(function0, function2, null);
                composerStartRestartGroup.updateRememberedValue(canvasEditorScreenKt$CanvasElement$root$3$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierAlpha, objArr, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) canvasEditorScreenKt$CanvasElement$root$3$1RememberedValue);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(173134117);
                ComposerKt.sourceInformation(composerStartRestartGroup, "162@13749L11");
                companionM1225borderxT4_qwU$default = BorderKt.m1225borderxT4_qwU$default(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(2.0f), MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getPrimary(), null, 4, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(173135774);
                composerStartRestartGroup.endReplaceGroup();
                companionM1225borderxT4_qwU$default = Modifier.INSTANCE;
            }
            Modifier modifierThen = modifierPointerInput.then(companionM1225borderxT4_qwU$default);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
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
            Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1573609390, "C:CanvasEditorScreen.kt#ska5t9");
            String type = designElementEntity.getType();
            switch (type.hashCode()) {
                case -1172269795:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    if (!type.equals("STICKER")) {
                        i4 = -1587385946;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit = Unit.INSTANCE;
                    } else {
                        composer2.startReplaceGroup(-1851814866);
                        ComposerKt.sourceInformation(composer2, "169@15822L114");
                        TextKt.m3342Text4IGK_g(designElementEntity.getContent(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 0L, TextUnitKt.getSp(designElementEntity.getHeight() * 0.75f), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m7131boximpl(TextAlign.INSTANCE.m7138getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 48, 0, 130548);
                        composer2.endReplaceGroup();
                        Unit unit2 = Unit.INSTANCE;
                    }
                    break;
                case 2593:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    if (!type.equals("QR")) {
                        i4 = -1587385946;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit3 = Unit.INSTANCE;
                    } else {
                        composer2.startReplaceGroup(-1851810617);
                        ComposerKt.sourceInformation(composer2, "170@15957L43");
                        DocumentQr(designElementEntity, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), composer2, i6 | 48);
                        composer2.endReplaceGroup();
                        Unit unit4 = Unit.INSTANCE;
                    }
                    break;
                case 2336756:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    if (!type.equals("LINE")) {
                        i4 = -1587385946;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit5 = Unit.INSTANCE;
                    } else {
                        composer2.startReplaceGroup(-1851824805);
                        ComposerKt.sourceInformation(composer2, "167@15511L127");
                        BoxKt.Box(BackgroundKt.m1213backgroundbw27NRU$default(boxScopeInstance.align(SizeKt.m1689height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(designElementEntity.getStrokeWidth())), Alignment.INSTANCE.getCenter()), parseColor(designElementEntity.getStrokeColor()), null, 2, null), composer2, 0);
                        composer2.endReplaceGroup();
                        Unit unit6 = Unit.INSTANCE;
                    }
                    break;
                case 2511332:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    if (!type.equals("RECT")) {
                        i4 = -1587385946;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit7 = Unit.INSTANCE;
                    } else {
                        composer2.startReplaceGroup(-1851840459);
                        ComposerKt.sourceInformation(composer2, "165@15019L217");
                        BoxKt.Box(BorderKt.m1225borderxT4_qwU$default(BackgroundKt.m1213backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1941RoundedCornerShape0680j_4(C1786Dp.m7249constructorimpl(designElementEntity.getCornerRadius()))), parseColor(designElementEntity.getFillColor()), null, 2, null), C1786Dp.m7249constructorimpl(designElementEntity.getStrokeWidth()), parseColor(designElementEntity.getStrokeColor()), null, 4, null), composer2, 0);
                        composer2.endReplaceGroup();
                        Unit unit8 = Unit.INSTANCE;
                    }
                    break;
                case 2571565:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    if (!type.equals("TEXT")) {
                        i4 = -1587385946;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit9 = Unit.INSTANCE;
                    } else {
                        composer2.startReplaceGroup(-1573619838);
                        ComposerKt.sourceInformation(composer2, "164@13840L1156");
                        String content = designElementEntity.getContent();
                        Modifier modifierM1658padding3ABfNKs = PaddingKt.m1658padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), C1786Dp.m7249constructorimpl(4.0f));
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(Color.m4798boximpl(ColorKt.Color(android.graphics.Color.parseColor(designElementEntity.getTextColor()))));
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                        }
                        Color colorM4798boximpl = Color.m4798boximpl(Color.INSTANCE.m4834getBlack0d7_KjU());
                        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                            objM7781constructorimpl = colorM4798boximpl;
                        }
                        long jM4818unboximpl = ((Color) objM7781constructorimpl).m4818unboximpl();
                        long sp = TextUnitKt.getSp(designElementEntity.getFontSize());
                        FontWeight bold = designElementEntity.getBold() ? FontWeight.INSTANCE.getBold() : FontWeight.INSTANCE.getNormal();
                        int iM6852getItalic_LCdwA = designElementEntity.getItalic() ? FontStyle.INSTANCE.m6852getItalic_LCdwA() : FontStyle.INSTANCE.m6853getNormal_LCdwA();
                        TextDecoration underline = designElementEntity.getUnderline() ? TextDecoration.INSTANCE.getUnderline() : TextDecoration.INSTANCE.getNone();
                        switch (designElementEntity.getFontFamily()) {
                            case "EL_MESSIRI":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.el_messiri_regular, null, 0, 0, 14, null));
                                break;
                            case "TAJAWAL":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.tajawal_regular, null, 0, 0, 14, null));
                                break;
                            case "AMIRI":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.amiri_regular, null, 0, 0, 14, null));
                                break;
                            case "CAIRO":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.cairo_regular, null, 0, 0, 14, null));
                                break;
                            case "SERIF":
                                sansSerif = FontFamily.INSTANCE.getSerif();
                            case "NOTO_KUFI":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.noto_kufi_regular, null, 0, 0, 14, null));
                                break;
                            case "SCHEHERAZADE":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.scheherazade_regular, null, 0, 0, 14, null));
                                break;
                            case "NOTO_NASKH":
                                genericFontFamilyFontFamily = FontFamilyKt.FontFamily(FontKt.m6829FontYpTlLL0$default(C2530R.font.noto_naskh_regular, null, 0, 0, 14, null));
                                break;
                            case "MONOSPACE":
                                sansSerif = FontFamily.INSTANCE.getMonospace();
                            default:
                                sansSerif = FontFamily.INSTANCE.getSansSerif();
                                genericFontFamilyFontFamily = sansSerif;
                                break;
                        }
                        FontFamily fontFamily = genericFontFamilyFontFamily;
                        String textAlign = designElementEntity.getTextAlign();
                        TextKt.m3342Text4IGK_g(content, modifierM1658padding3ABfNKs, jM4818unboximpl, sp, FontStyle.m6843boximpl(iM6852getItalic_LCdwA), bold, fontFamily, 0L, underline, TextAlign.m7131boximpl(Intrinsics.areEqual(textAlign, "CENTER") ? TextAlign.INSTANCE.m7138getCentere0LSkKk() : Intrinsics.areEqual(textAlign, "END") ? TextAlign.INSTANCE.m7139getEnde0LSkKk() : TextAlign.INSTANCE.m7143getStarte0LSkKk()), TextUnitKt.getSp(designElementEntity.getFontSize() * designElementEntity.getLineSpacing()), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 48, 0, 129152);
                        composer2 = composer2;
                        composer2.endReplaceGroup();
                        Unit unit10 = Unit.INSTANCE;
                    }
                    break;
                case 62553065:
                    if (!type.equals("ARROW")) {
                        i2 = i5;
                        str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                        continuation = null;
                        i3 = 1;
                        i4 = -1587385946;
                        composer2 = composerStartRestartGroup;
                        composer2.startReplaceGroup(i4);
                        composer2.endReplaceGroup();
                        Unit unit11 = Unit.INSTANCE;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(-1851819966);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "168@15662L134");
                        i2 = i5;
                        continuation = null;
                        str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                        i3 = 1;
                        TextKt.m3342Text4IGK_g("➜", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), parseColor(designElementEntity.getStrokeColor()), TextUnitKt.getSp(designElementEntity.getHeight()), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m7131boximpl(TextAlign.INSTANCE.m7138getCentere0LSkKk()), 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 54, 0, 130544);
                        composer2 = composerStartRestartGroup;
                        composer2.endReplaceGroup();
                        Unit unit12 = Unit.INSTANCE;
                    }
                    break;
                case 69775675:
                    if (type.equals("IMAGE")) {
                        composerStartRestartGroup.startReplaceGroup(-1851808470);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "171@16024L46");
                        DocumentImage(designElementEntity, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), composerStartRestartGroup, i6 | 48);
                        composerStartRestartGroup.endReplaceGroup();
                        Unit unit13 = Unit.INSTANCE;
                        i2 = i5;
                        str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                        continuation = null;
                        i3 = 1;
                        composer2 = composerStartRestartGroup;
                    }
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    i4 = -1587385946;
                    composer2 = composerStartRestartGroup;
                    composer2.startReplaceGroup(i4);
                    composer2.endReplaceGroup();
                    Unit unit14 = Unit.INSTANCE;
                    break;
                case 79578030:
                    if (type.equals("TABLE")) {
                        composerStartRestartGroup.startReplaceGroup(-1851806230);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "172@16094L46");
                        DocumentTable(designElementEntity, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), composerStartRestartGroup, i6 | 48);
                        composerStartRestartGroup.endReplaceGroup();
                        Unit unit15 = Unit.INSTANCE;
                        i2 = i5;
                        str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                        continuation = null;
                        i3 = 1;
                        composer2 = composerStartRestartGroup;
                    }
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    i4 = -1587385946;
                    composer2 = composerStartRestartGroup;
                    composer2.startReplaceGroup(i4);
                    composer2.endReplaceGroup();
                    Unit unit16 = Unit.INSTANCE;
                    break;
                case 1988079824:
                    if (type.equals("CIRCLE")) {
                        composerStartRestartGroup.startReplaceGroup(-1851832705);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "166@15261L227");
                        BoxKt.Box(BorderKt.m1224borderxT4_qwU(BackgroundKt.m1212backgroundbw27NRU(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), parseColor(designElementEntity.getFillColor()), RoundedCornerShapeKt.getCircleShape()), C1786Dp.m7249constructorimpl(designElementEntity.getStrokeWidth()), parseColor(designElementEntity.getStrokeColor()), RoundedCornerShapeKt.getCircleShape()), composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                        Unit unit17 = Unit.INSTANCE;
                        i2 = i5;
                        str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                        continuation = null;
                        i3 = 1;
                        composer2 = composerStartRestartGroup;
                    }
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    i4 = -1587385946;
                    composer2 = composerStartRestartGroup;
                    composer2.startReplaceGroup(i4);
                    composer2.endReplaceGroup();
                    Unit unit18 = Unit.INSTANCE;
                    break;
                default:
                    i2 = i5;
                    str = "CC(remember):CanvasEditorScreen.kt#9igjgp";
                    continuation = null;
                    i3 = 1;
                    composer2 = composerStartRestartGroup;
                    i4 = -1587385946;
                    composer2.startReplaceGroup(i4);
                    composer2.endReplaceGroup();
                    Unit unit19 = Unit.INSTANCE;
                    break;
            }
            if (z) {
                composer2 = composer2;
                composer2.startReplaceGroup(-1571325094);
                ComposerKt.sourceInformation(composer2, "175@16264L11,175@16341L85,175@16187L240,176@16538L11,176@16604L82,176@16440L247");
                Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(SizeKt.m1703size3ABfNKs(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomEnd()), C1786Dp.m7249constructorimpl(18.0f)), MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getPrimary(), null, 2, null);
                Object[] objArr2 = {Long.valueOf(designElementEntity.getId()), Float.valueOf(designElementEntity.getWidth()), Float.valueOf(designElementEntity.getHeight())};
                String str2 = str;
                ComposerKt.sourceInformationMarkerStart(composer2, -1851798287, str2);
                int i7 = (i2 & 458752) == 131072 ? i3 : 0;
                CanvasEditorScreenKt$CanvasElement$1$2$1 canvasEditorScreenKt$CanvasElement$1$2$1RememberedValue = composer2.rememberedValue();
                if (i7 != 0 || canvasEditorScreenKt$CanvasElement$1$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    canvasEditorScreenKt$CanvasElement$1$2$1RememberedValue = new CanvasEditorScreenKt$CanvasElement$1$2$1(function3, continuation);
                    composer2.updateRememberedValue(canvasEditorScreenKt$CanvasElement$1$2$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                BoxKt.Box(SuspendingPointerInputFilterKt.pointerInput(modifierM1213backgroundbw27NRU$default, objArr2, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) canvasEditorScreenKt$CanvasElement$1$2$1RememberedValue), composer2, 0);
                Modifier modifierM1213backgroundbw27NRU$default2 = BackgroundKt.m1213backgroundbw27NRU$default(SizeKt.m1703size3ABfNKs(OffsetKt.m1619offsetVpY3zN4$default(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, C1786Dp.m7249constructorimpl(-22.0f), i3, continuation), C1786Dp.m7249constructorimpl(18.0f)), MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getSecondary(), null, 2, null);
                Long lValueOf = Long.valueOf(designElementEntity.getId());
                Float fValueOf = Float.valueOf(designElementEntity.getRotation());
                ComposerKt.sourceInformationMarkerStart(composer2, -1851789874, str2);
                int i8 = (i2 & 3670016) == 1048576 ? i3 : 0;
                CanvasEditorScreenKt$CanvasElement$1$3$1 canvasEditorScreenKt$CanvasElement$1$3$1RememberedValue = composer2.rememberedValue();
                if (i8 != 0 || canvasEditorScreenKt$CanvasElement$1$3$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    canvasEditorScreenKt$CanvasElement$1$3$1RememberedValue = new CanvasEditorScreenKt$CanvasElement$1$3$1(function1, continuation);
                    composer2.updateRememberedValue(canvasEditorScreenKt$CanvasElement$1$3$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                BoxKt.Box(SuspendingPointerInputFilterKt.pointerInput(modifierM1213backgroundbw27NRU$default2, lValueOf, fValueOf, (Function2) canvasEditorScreenKt$CanvasElement$1$3$1RememberedValue), composer2, 0);
            } else {
                composer2 = composer2;
                composer2.startReplaceGroup(-1587385946);
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.CanvasElement$lambda$194(designElementEntity, z, z2, function0, function2, function3, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final IntOffset CanvasElement$lambda$186$lambda$185(DesignElementEntity designElementEntity, Density offset) {
        Intrinsics.checkNotNullParameter(offset, "$this$offset");
        return IntOffset.m7368boximpl(IntOffsetKt.IntOffset(MathKt.roundToInt(designElementEntity.getX()), MathKt.roundToInt(designElementEntity.getY())));
    }

    static final Unit CanvasElement$lambda$188$lambda$187(DesignElementEntity designElementEntity, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setRotationZ(designElementEntity.getRotation());
        return Unit.INSTANCE;
    }

    private static final long parseColor(String str) {
        Object objM7781constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(Color.m4798boximpl(ColorKt.Color(android.graphics.Color.parseColor(str))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        Color colorM4798boximpl = Color.m4798boximpl(Color.INSTANCE.m4843getTransparent0d7_KjU());
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = colorM4798boximpl;
        }
        return ((Color) objM7781constructorimpl).m4818unboximpl();
    }

    private static final void DocumentQr(final DesignElementEntity designElementEntity, Modifier modifier, Composer composer, final int i) {
        Object objM7781constructorimpl;
        final Modifier modifier2 = modifier;
        Composer composerStartRestartGroup = composer.startRestartGroup(1132760762);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentQr)185@16948L583:CanvasEditorScreen.kt#ska5t9");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(designElementEntity) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1132760762, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentQr (CanvasEditorScreen.kt:184)");
            }
            String content = designElementEntity.getContent();
            int iRoundToInt = MathKt.roundToInt(designElementEntity.getWidth());
            int iRoundToInt2 = MathKt.roundToInt(designElementEntity.getHeight());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1956370305, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(content) | composerStartRestartGroup.changed(iRoundToInt) | composerStartRestartGroup.changed(iRoundToInt2);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(designElementEntity.getContent(), BarcodeFormat.QR_CODE, RangesKt.coerceAtLeast(MathKt.roundToInt(designElementEntity.getWidth()), 64), RangesKt.coerceAtLeast(MathKt.roundToInt(designElementEntity.getHeight()), 64));
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitMatrixEncode.getWidth(), bitMatrixEncode.getHeight(), Bitmap.Config.ARGB_8888);
                    int width = bitMatrixEncode.getWidth();
                    for (int i3 = 0; i3 < width; i3++) {
                        int height = bitMatrixEncode.getHeight();
                        for (int i4 = 0; i4 < height; i4++) {
                            bitmapCreateBitmap.setPixel(i3, i4, bitMatrixEncode.get(i3, i4) ? ViewCompat.MEASURED_STATE_MASK : -1);
                        }
                    }
                    objM7781constructorimpl = Result.m7781constructorimpl(bitmapCreateBitmap);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                    objM7781constructorimpl = null;
                }
                objRememberedValue = (Bitmap) objM7781constructorimpl;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Bitmap bitmap = (Bitmap) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (bitmap != null) {
                composerStartRestartGroup.startReplaceGroup(1956390170);
                ComposerKt.sourceInformation(composerStartRestartGroup, "191@17584L96");
                ImageKt.m1269Image5hnEew(AndroidImageBitmap_androidKt.asImageBitmap(bitmap), "QR", modifier2, null, ContentScale.INSTANCE.getFillBounds(), 0.0f, null, 0, composerStartRestartGroup, ((i2 << 3) & 896) | 24624, 232);
                composerStartRestartGroup.endReplaceGroup();
                modifier2 = modifier;
            } else {
                composerStartRestartGroup.startReplaceGroup(1956393427);
                ComposerKt.sourceInformation(composerStartRestartGroup, "191@17686L89");
                modifier2 = modifier;
                Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(modifier2, Color.INSTANCE.m4845getWhite0d7_KjU(), null, 2, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1213backgroundbw27NRU$default);
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
                Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1338717845, "C191@17763L10:CanvasEditorScreen.kt#ska5t9");
                TextKt.m3342Text4IGK_g("QR", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 6, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda68
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.DocumentQr$lambda$200(designElementEntity, modifier2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void DocumentImage(final DesignElementEntity designElementEntity, Modifier modifier, Composer composer, final int i) {
        int i2;
        final Modifier modifier2 = modifier;
        Composer composerStartRestartGroup = composer.startRestartGroup(1709792370);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentImage)196@17900L7,197@17988L172,197@17926L234:CanvasEditorScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(designElementEntity) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1709792370, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentImage (CanvasEditorScreen.kt:195)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Context context = (Context) objConsume;
            String content = designElementEntity.getContent();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1861999938, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChangedInstance = ((i2 & 14) == 4) | composerStartRestartGroup.changedInstance(context);
            CanvasEditorScreenKt$DocumentImage$bitmap$2$1 canvasEditorScreenKt$DocumentImage$bitmap$2$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || canvasEditorScreenKt$DocumentImage$bitmap$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                canvasEditorScreenKt$DocumentImage$bitmap$2$1RememberedValue = new CanvasEditorScreenKt$DocumentImage$bitmap$2$1(context, designElementEntity, null);
                composerStartRestartGroup.updateRememberedValue(canvasEditorScreenKt$DocumentImage$bitmap$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            State stateProduceState = SnapshotStateKt.produceState((Object) null, content, (Function2<? super ProduceStateScope<Object>, ? super Continuation<? super Unit>, ? extends Object>) canvasEditorScreenKt$DocumentImage$bitmap$2$1RememberedValue, composerStartRestartGroup, 6);
            if (DocumentImage$lambda$202(stateProduceState) != null) {
                composerStartRestartGroup.startReplaceGroup(-1861992819);
                ComposerKt.sourceInformation(composerStartRestartGroup, "198@18213L91");
                Bitmap bitmapDocumentImage$lambda$202 = DocumentImage$lambda$202(stateProduceState);
                Intrinsics.checkNotNull(bitmapDocumentImage$lambda$202);
                ImageKt.m1269Image5hnEew(AndroidImageBitmap_androidKt.asImageBitmap(bitmapDocumentImage$lambda$202), null, modifier, null, ContentScale.INSTANCE.getFit(), 0.0f, null, 0, composerStartRestartGroup, ((i2 << 3) & 896) | 24624, 232);
                composerStartRestartGroup.endReplaceGroup();
                modifier2 = modifier;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1861989711);
                ComposerKt.sourceInformation(composerStartRestartGroup, "198@18310L95");
                modifier2 = modifier;
                Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(modifier2, Color.INSTANCE.m4840getLightGray0d7_KjU(), null, 2, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1213backgroundbw27NRU$default);
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
                Updater.m4308setimpl(composerM4301constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4308setimpl(composerM4301constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM4301constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM4301constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM4301constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m4308setimpl(composerM4301constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2015067857, "C198@18391L12:CanvasEditorScreen.kt#ska5t9");
                TextKt.m3342Text4IGK_g("صورة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 6, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.DocumentImage$lambda$204(designElementEntity, modifier2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void DocumentTable(final DesignElementEntity designElementEntity, final Modifier modifier, Composer composer, final int i) {
        int i2;
        List listEmptyList;
        Composer composer2;
        Integer intOrNull;
        Integer intOrNull2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1772191199);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DocumentTable)207@18765L675:CanvasEditorScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(designElementEntity) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1772191199, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.DocumentTable (CanvasEditorScreen.kt:202)");
            }
            List listSplit$default = StringsKt.split$default((CharSequence) designElementEntity.getContent(), new String[]{"|"}, false, 3, 2, (Object) null);
            String str = (String) CollectionsKt.getOrNull(listSplit$default, 0);
            int iCoerceIn = 3;
            int iCoerceIn2 = (str == null || (intOrNull2 = StringsKt.toIntOrNull(str)) == null) ? 3 : RangesKt.coerceIn(intOrNull2.intValue(), 1, 20);
            String str2 = (String) CollectionsKt.getOrNull(listSplit$default, 1);
            if (str2 != null && (intOrNull = StringsKt.toIntOrNull(str2)) != null) {
                iCoerceIn = RangesKt.coerceIn(intOrNull.intValue(), 1, 10);
            }
            int i3 = iCoerceIn;
            String str3 = (String) CollectionsKt.getOrNull(listSplit$default, 2);
            if (str3 == null || (listEmptyList = StringsKt.split$default((CharSequence) str3, new String[]{"§"}, false, 0, 6, (Object) null)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List list = listEmptyList;
            int i4 = 1;
            Modifier modifierM1225borderxT4_qwU$default = BorderKt.m1225borderxT4_qwU$default(modifier, C1786Dp.m7249constructorimpl(designElementEntity.getStrokeWidth()), parseColor(designElementEntity.getStrokeColor()), null, 4, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1225borderxT4_qwU$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            String str4 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 670130168, "C:CanvasEditorScreen.kt#ska5t9");
            composerStartRestartGroup.startReplaceGroup(991448427);
            ComposerKt.sourceInformation(composerStartRestartGroup, "*209@18890L534");
            int i5 = 0;
            while (i5 < iCoerceIn2) {
                Modifier modifierWeight$default = ColumnScope.weight$default(columnScopeInstance, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i4, null), 1.0f, false, 2, null);
                ColumnScopeInstance columnScopeInstance2 = columnScopeInstance;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composerStartRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierWeight$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = i5;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str4);
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1201200568, "C:CanvasEditorScreen.kt#ska5t9");
                composerStartRestartGroup.startReplaceGroup(1839863723);
                ComposerKt.sourceInformation(composerStartRestartGroup, "*212@19051L341");
                int i7 = 0;
                while (i7 < i3) {
                    int i8 = (i6 * i3) + i7;
                    int i9 = i7;
                    RowScopeInstance rowScopeInstance2 = rowScopeInstance;
                    Modifier modifierM1213backgroundbw27NRU$default = BackgroundKt.m1213backgroundbw27NRU$default(BorderKt.m1225borderxT4_qwU$default(SizeKt.fillMaxHeight$default(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), C1786Dp.m7249constructorimpl(0.5f), parseColor(designElementEntity.getStrokeColor()), null, 4, null), parseColor(designElementEntity.getFillColor()), null, 2, null);
                    Alignment centerEnd = Alignment.INSTANCE.getCenterEnd();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(centerEnd, false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1213backgroundbw27NRU$default);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i10 = i3;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str4);
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
                    Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -598179102, "C213@19248L122:CanvasEditorScreen.kt#ska5t9");
                    String str5 = (String) CollectionsKt.getOrNull(list, i8);
                    if (str5 == null) {
                        str5 = "";
                    }
                    Composer composer3 = composerStartRestartGroup;
                    TextKt.m3342Text4IGK_g(str5, PaddingKt.m1658padding3ABfNKs(Modifier.INSTANCE, C1786Dp.m7249constructorimpl(3.0f)), 0L, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m7131boximpl(TextAlign.INSTANCE.m7139getEnde0LSkKk()), 0L, 0, false, 4, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 3120, 3072, 122356);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    list = list;
                    iCoerceIn2 = iCoerceIn2;
                    rowScopeInstance = rowScopeInstance2;
                    i7 = i9 + 1;
                    composerStartRestartGroup = composer3;
                    str4 = str4;
                    i3 = i10;
                }
                Composer composer4 = composerStartRestartGroup;
                composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                i5 = i6 + 1;
                columnScopeInstance = columnScopeInstance2;
                i4 = 1;
            }
            composer2 = composerStartRestartGroup;
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda49
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.DocumentTable$lambda$210(designElementEntity, modifier, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x0198 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0196  */
    private static final void TableElementDialog(final DesignElementEntity designElementEntity, final Function0<Unit> function0, final Function3<? super Integer, ? super Integer, ? super List<String>, Unit> function3, Composer composer, final int i) {
        String str;
        Integer intOrNull;
        String str2;
        Integer intOrNull2;
        String str3;
        String str4;
        Composer composer2;
        String content;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1252102862);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TableElementDialog)224@19664L105,225@19789L105,226@19912L142,244@21528L77,244@21623L53,228@20205L63,228@20277L1233,228@20155L1522:CanvasEditorScreen.kt#ska5t9");
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changed(designElementEntity) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1252102862, i2, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog (CanvasEditorScreen.kt:222)");
            }
            List listSplit$default = (designElementEntity == null || (content = designElementEntity.getContent()) == null) ? null : StringsKt.split$default((CharSequence) content, new String[]{"|"}, false, 3, 2, (Object) null);
            Long lValueOf = designElementEntity != null ? Long.valueOf(designElementEntity.getId()) : null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -680000357, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(lValueOf);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf((listSplit$default == null || (str = (String) CollectionsKt.getOrNull(listSplit$default, 0)) == null || (intOrNull = StringsKt.toIntOrNull(str)) == null) ? 3 : RangesKt.coerceIn(intOrNull.intValue(), 1, 20)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Long lValueOf2 = designElementEntity != null ? Long.valueOf(designElementEntity.getId()) : null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -679996357, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(lValueOf2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Integer.valueOf((listSplit$default == null || (str2 = (String) CollectionsKt.getOrNull(listSplit$default, 1)) == null || (intOrNull2 = StringsKt.toIntOrNull(str2)) == null) ? 3 : RangesKt.coerceIn(intOrNull2.intValue(), 1, 10)), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Long lValueOf3 = designElementEntity != null ? Long.valueOf(designElementEntity.getId()) : null;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -679992384, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged3 = composerStartRestartGroup.changed(lValueOf3);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                int iTableElementDialog$lambda$212 = TableElementDialog$lambda$212(mutableState) * TableElementDialog$lambda$215(mutableState2);
                ArrayList arrayList = new ArrayList(iTableElementDialog$lambda$212);
                for (int i3 = 0; i3 < iTableElementDialog$lambda$212; i3++) {
                    if (listSplit$default != null && (str4 = (String) CollectionsKt.getOrNull(listSplit$default, 2)) != null) {
                        List listSplit$default2 = StringsKt.split$default((CharSequence) str4, new String[]{"§"}, false, 0, 6, (Object) null);
                        str3 = listSplit$default2 != null ? (String) CollectionsKt.getOrNull(listSplit$default2, i3) : null;
                        if (str3 == null) {
                            str3 = "";
                        }
                        arrayList.add(str3);
                    }
                    if (str3 == null) {
                        str3 = "";
                    }
                    arrayList.add(str3);
                }
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(arrayList, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(774554490, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda110
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TableElementDialog$lambda$224(function3, mutableState, mutableState2, mutableState3, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-630637188, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda111
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TableElementDialog$lambda$225(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-2035828866, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda113
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TableElementDialog$lambda$226(designElementEntity, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(1556542591, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda114
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TableElementDialog$lambda$240(mutableState, mutableState3, mutableState2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, ((i2 >> 3) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda115
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TableElementDialog$lambda$241(designElementEntity, function0, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final int TableElementDialog$lambda$212(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void TableElementDialog$lambda$213(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    private static final int TableElementDialog$lambda$215(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    private static final void TableElementDialog$lambda$216(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    private static final List<String> TableElementDialog$lambda$219(MutableState<List<String>> mutableState) {
        return mutableState.getValue();
    }

    private static final void TableElementDialog$resize(MutableState<Integer> mutableState, MutableState<Integer> mutableState2, MutableState<List<String>> mutableState3) {
        int iTableElementDialog$lambda$212 = TableElementDialog$lambda$212(mutableState) * TableElementDialog$lambda$215(mutableState2);
        ArrayList arrayList = new ArrayList(iTableElementDialog$lambda$212);
        for (int i = 0; i < iTableElementDialog$lambda$212; i++) {
            String str = (String) CollectionsKt.getOrNull(TableElementDialog$lambda$219(mutableState3), i);
            if (str == null) {
                str = "";
            }
            arrayList.add(str);
        }
        mutableState3.setValue(arrayList);
    }

    static final Unit TableElementDialog$lambda$226(DesignElementEntity designElementEntity, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C228@20207L59:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2035828866, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog.<anonymous> (CanvasEditorScreen.kt:228)");
            }
            TextKt.m3342Text4IGK_g(designElementEntity == null ? "إضافة جدول" : "تحرير الجدول", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TableElementDialog$lambda$240(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C229@20318L21,229@20287L1217:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1556542591, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog.<anonymous> (CanvasEditorScreen.kt:229)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(Modifier.INSTANCE, ScrollKt.rememberScrollState(0, composer, 0, 1), false, null, false, 14, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1997136750, "C230@20406L433,234@20959L10,234@20852L128:CanvasEditorScreen.kt#ska5t9");
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion);
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
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 387917073, "C231@20515L63,231@20480L159,232@20694L69,232@20656L169:CanvasEditorScreen.kt#ska5t9");
            String strValueOf = String.valueOf(TableElementDialog$lambda$212(mutableState));
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 151061624, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(mutableState) | composer.changed(mutableState2) | composer.changed(mutableState3);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda124
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m797x5d02db96(mutableState, mutableState3, mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            String str = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            String str2 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            String str3 = "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo";
            String str4 = "C101@5126L9:Row.kt#2w3rfo";
            OutlinedTextFieldKt.OutlinedTextField(strValueOf, (Function1<? super String, Unit>) objRememberedValue, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7655getLambda$470452493$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572864, 0, 0, 8388536);
            String strValueOf2 = String.valueOf(TableElementDialog$lambda$215(mutableState3));
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 151067358, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged2 = composer.changed(mutableState3) | composer.changed(mutableState2) | composer.changed(mutableState);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda125
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m798xdf8b721(mutableState3, mutableState, mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strValueOf2, (Function1<? super String, Unit>) objRememberedValue2, modifierWeight$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7666getLambda$851446614$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572864, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextKt.m3342Text4IGK_g("اكتب محتوى كل خلية. يدعم الجدول الحقول الرسمية مثل {المبلغ} و{اسم المستفيد}.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 6, 0, 65534);
            Composer composer2 = composer;
            composer2.startReplaceGroup(-1043936694);
            ComposerKt.sourceInformation(composer2, "*236@21031L449");
            int iTableElementDialog$lambda$212 = TableElementDialog$lambda$212(mutableState);
            final int i2 = 0;
            while (i2 < iTableElementDialog$lambda$212) {
                Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
                String str5 = str3;
                ComposerKt.sourceInformationMarkerStart(composer2, 693286680, str5);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                char c = 6;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composer2, 6);
                String str6 = str2;
                byte b = -1323940314;
                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str6);
                boolean z = false;
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, companion2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                String str7 = str5;
                String str8 = str6;
                String str9 = str;
                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str9);
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
                String str10 = str4;
                byte b2 = -407840262;
                ComposerKt.sourceInformationMarkerStart(composer2, -407840262, str10);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer2, 26914849, "C:CanvasEditorScreen.kt#ska5t9");
                composer2.startReplaceGroup(1247794210);
                ComposerKt.sourceInformation(composer2, "*239@21272L69,239@21351L36,239@21220L220");
                int iTableElementDialog$lambda$215 = TableElementDialog$lambda$215(mutableState3);
                final int i3 = 0;
                while (i3 < iTableElementDialog$lambda$215) {
                    final int iTableElementDialog$lambda$216 = (TableElementDialog$lambda$215(mutableState3) * i2) + i3;
                    String str11 = (String) CollectionsKt.getOrNull(TableElementDialog$lambda$219(mutableState2), iTableElementDialog$lambda$216);
                    if (str11 == null) {
                        str11 = "";
                    }
                    Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance2, Modifier.INSTANCE, 1.0f, false, 2, null);
                    RowScopeInstance rowScopeInstance3 = rowScopeInstance2;
                    ComposerKt.sourceInformationMarkerStart(composer2, 953417462, "CC(remember):CanvasEditorScreen.kt#9igjgp");
                    boolean zChanged3 = composer2.changed(mutableState2) | composer2.changed(iTableElementDialog$lambda$216);
                    Object objRememberedValue3 = composer2.rememberedValue();
                    if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda126
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return CanvasEditorScreenKt.m799xc4c53d9c(mutableState2, iTableElementDialog$lambda$216, (String) obj);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    OutlinedTextFieldKt.OutlinedTextField(str11, (Function1<? super String, Unit>) objRememberedValue3, modifierWeight$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-810732501, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda127
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return CanvasEditorScreenKt.m800xe4ef391e(i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composer2, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572864, 12582912, 0, 8257464);
                    i3++;
                    composer2 = composer;
                    rowScopeInstance2 = rowScopeInstance3;
                    i2 = i2;
                    iTableElementDialog$lambda$212 = iTableElementDialog$lambda$212;
                    b = -1323940314;
                    iTableElementDialog$lambda$215 = iTableElementDialog$lambda$215;
                    z = z;
                    str10 = str10;
                    b2 = -407840262;
                    str7 = str7;
                    str9 = str9;
                    str8 = str8;
                    c = 6;
                }
                str4 = str10;
                str = str9;
                str2 = str8;
                str3 = str7;
                composer.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                i2++;
                composer2 = composer;
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

    /* JADX INFO: renamed from: TableElementDialog$lambda$240$lambda$239$lambda$231$lambda$228$lambda$227 */
    static final Unit m797x5d02db96(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Integer intOrNull = StringsKt.toIntOrNull(it);
        TableElementDialog$lambda$213(mutableState, RangesKt.coerceIn(intOrNull != null ? intOrNull.intValue() : TableElementDialog$lambda$212(mutableState), 1, 20));
        TableElementDialog$resize(mutableState, mutableState2, mutableState3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TableElementDialog$lambda$240$lambda$239$lambda$231$lambda$230$lambda$229 */
    static final Unit m798xdf8b721(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Integer intOrNull = StringsKt.toIntOrNull(it);
        TableElementDialog$lambda$216(mutableState, RangesKt.coerceIn(intOrNull != null ? intOrNull.intValue() : TableElementDialog$lambda$215(mutableState), 1, 10));
        TableElementDialog$resize(mutableState2, mutableState, mutableState3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TableElementDialog$lambda$240$lambda$239$lambda$238$lambda$237$lambda$236$lambda$234$lambda$233 */
    static final Unit m799xc4c53d9c(MutableState mutableState, int i, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        List mutableList = CollectionsKt.toMutableList((Collection) TableElementDialog$lambda$219(mutableState));
        mutableList.set(i, value);
        mutableState.setValue(mutableList);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TableElementDialog$lambda$240$lambda$239$lambda$238$lambda$237$lambda$236$lambda$235 */
    static final Unit m800xe4ef391e(int i, int i2, Composer composer, int i3) {
        ComposerKt.sourceInformation(composer, "C239@21353L32:CanvasEditorScreen.kt#ska5t9");
        if ((i3 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-810732501, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:239)");
            }
            TextKt.m3342Text4IGK_g((i + 1) + DomExceptionUtils.SEPARATOR + (i2 + 1), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TableElementDialog$lambda$224(final Function3 function3, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C244@21547L32,244@21530L73:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(774554490, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog.<anonymous> (CanvasEditorScreen.kt:244)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1317523322, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function3) | composer.changed(mutableState) | composer.changed(mutableState2) | composer.changed(mutableState3);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda112
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.TableElementDialog$lambda$224$lambda$223$lambda$222(function3, mutableState, mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7665getLambda$831209622$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TableElementDialog$lambda$224$lambda$223$lambda$222(Function3 function3, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        function3.invoke(Integer.valueOf(TableElementDialog$lambda$212(mutableState)), Integer.valueOf(TableElementDialog$lambda$215(mutableState2)), TableElementDialog$lambda$219(mutableState3));
        return Unit.INSTANCE;
    }

    static final Unit TableElementDialog$lambda$225(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C244@21625L49:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-630637188, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TableElementDialog.<anonymous> (CanvasEditorScreen.kt:244)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7645getLambda$1302750727$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void TextElementDialog(DesignElementEntity designElementEntity, final Function0<Unit> function0, final Function9<? super String, ? super Boolean, ? super Boolean, ? super Boolean, ? super String, ? super String, ? super Float, ? super String, ? super Float, Unit> function9, Composer composer, final int i, final int i2) {
        DesignElementEntity designElementEntity2;
        int i3;
        Composer composer2;
        final DesignElementEntity designElementEntity3;
        String textAlign;
        String textColor;
        String fontFamily;
        String content;
        Composer composerStartRestartGroup = composer.startRestartGroup(1568640541);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TextElementDialog)249@21893L58,249@21965L51,249@22032L53,249@22104L56,249@22176L58,249@22249L60,249@22323L53,249@22391L58,249@22466L55,250@23894L116,250@24028L53,250@22610L1266,250@22526L1556:CanvasEditorScreen.kt#ska5t9");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            designElementEntity2 = designElementEntity;
        } else if ((i & 6) == 0) {
            designElementEntity2 = designElementEntity;
            i3 = (composerStartRestartGroup.changed(designElementEntity2) ? 4 : 2) | i;
        } else {
            designElementEntity2 = designElementEntity;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function9) ? 256 : 128;
        }
        if ((i3 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            designElementEntity3 = designElementEntity2;
        } else {
            DesignElementEntity designElementEntity4 = i4 != 0 ? null : designElementEntity2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1568640541, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog (CanvasEditorScreen.kt:248)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69954153, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                if (designElementEntity4 == null || (content = designElementEntity4.getContent()) == null) {
                    content = "نص جديد";
                }
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(content, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69951856, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(designElementEntity4 != null ? designElementEntity4.getBold() : false), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69949710, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(designElementEntity4 != null ? designElementEntity4.getItalic() : false), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69947403, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(designElementEntity4 != null ? designElementEntity4.getUnderline() : false), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69945097, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                if (designElementEntity4 == null || (fontFamily = designElementEntity4.getFontFamily()) == null) {
                    fontFamily = "SANS";
                }
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(fontFamily, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69942759, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                if (designElementEntity4 == null || (textColor = designElementEntity4.getTextColor()) == null) {
                    textColor = "#000000";
                }
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(textColor, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69940398, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(designElementEntity4 != null ? designElementEntity4.getFontSize() : 18.0f), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69938217, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                if (designElementEntity4 == null || (textAlign = designElementEntity4.getTextAlign()) == null) {
                    textAlign = "START";
                }
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(textAlign, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            final MutableState mutableState8 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -69935820, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(designElementEntity4 != null ? designElementEntity4.getLineSpacing() : 1.0f), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
            }
            final MutableState mutableState9 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(2049658581, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda131
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TextElementDialog$lambda$271(function9, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-350974829, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda132
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TextElementDialog$lambda$272(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1543359057$app(), ComposableLambdaKt.rememberComposableLambda(-1804441296, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda133
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TextElementDialog$lambda$300(mutableState, mutableState6, mutableState2, mutableState3, mutableState4, mutableState8, mutableState7, mutableState5, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, ((i3 >> 3) & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            designElementEntity3 = designElementEntity4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda135
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.TextElementDialog$lambda$301(designElementEntity3, function0, function9, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String TextElementDialog$lambda$243(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean TextElementDialog$lambda$246(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void TextElementDialog$lambda$247(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean TextElementDialog$lambda$249(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void TextElementDialog$lambda$250(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean TextElementDialog$lambda$252(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void TextElementDialog$lambda$253(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String TextElementDialog$lambda$255(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String TextElementDialog$lambda$258(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final float TextElementDialog$lambda$261(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void TextElementDialog$lambda$262(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final String TextElementDialog$lambda$264(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final float TextElementDialog$lambda$267(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    static final Unit TextElementDialog$lambda$271(final Function9 function9, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, final MutableState mutableState9, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C250@23913L78,250@23896L112:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2049658581, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog.<anonymous> (CanvasEditorScreen.kt:250)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1668091837, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function9);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda136
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.TextElementDialog$lambda$271$lambda$270$lambda$269(function9, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9);
                    }
                };
                composer.updateRememberedValue(function0);
                objRememberedValue = function0;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$196744421$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TextElementDialog$lambda$271$lambda$270$lambda$269(Function9 function9, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9) {
        function9.invoke(TextElementDialog$lambda$243(mutableState), Boolean.valueOf(TextElementDialog$lambda$246(mutableState2)), Boolean.valueOf(TextElementDialog$lambda$249(mutableState3)), Boolean.valueOf(TextElementDialog$lambda$252(mutableState4)), TextElementDialog$lambda$255(mutableState5), TextElementDialog$lambda$258(mutableState6), Float.valueOf(TextElementDialog$lambda$261(mutableState7)), TextElementDialog$lambda$264(mutableState8), Float.valueOf(TextElementDialog$lambda$267(mutableState9)));
        return Unit.INSTANCE;
    }

    static final Unit TextElementDialog$lambda$272(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C250@24030L49:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-350974829, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog.<anonymous> (CanvasEditorScreen.kt:250)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$597175414$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TextElementDialog$lambda$300(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, MutableState mutableState6, final MutableState mutableState7, MutableState mutableState8, Composer composer, int i) {
        final MutableState mutableState9;
        final MutableState mutableState10;
        ComposerKt.sourceInformation(composer, "C250@22612L1262:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1804441296, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog.<anonymous> (CanvasEditorScreen.kt:250)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(6.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1762266744, "C250@22695L13,250@22671L100,250@22798L14,250@22773L67,250@22842L216,250@23060L185,250@23247L244,250@23493L379:CanvasEditorScreen.kt#ska5t9");
            String strTextElementDialog$lambda$243 = TextElementDialog$lambda$243(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 888130887, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda139
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.TextElementDialog$lambda$300$lambda$299$lambda$274$lambda$273(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strTextElementDialog$lambda$243, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$149823648$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388540);
            String strTextElementDialog$lambda$258 = TextElementDialog$lambda$258(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, 888134184, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda141
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.TextElementDialog$lambda$300$lambda$299$lambda$276$lambda$275(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strTextElementDialog$lambda$258, (Function1<? super String, Unit>) objRememberedValue2, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$484683721$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388540);
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion2);
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
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1337715043, "C250@22865L16,250@22848L60,250@22929L20,250@22910L66,250@23000L26,250@22978L78:CanvasEditorScreen.kt#ska5t9");
            boolean zTextElementDialog$lambda$246 = TextElementDialog$lambda$246(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, 233942918, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda142
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m801x8c975fd(mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ChipKt.FilterChip(zTextElementDialog$lambda$246, (Function0) objRememberedValue3, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1301092355$app(), null, false, null, null, null, null, null, null, null, composer, 432, 0, 4088);
            boolean zTextElementDialog$lambda$249 = TextElementDialog$lambda$249(mutableState4);
            ComposerKt.sourceInformationMarkerStart(composer, 233944970, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda143
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m802xb9bf5188(mutableState4);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ChipKt.FilterChip(zTextElementDialog$lambda$249, (Function0) objRememberedValue4, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$379302316$app(), null, false, null, null, null, null, null, null, null, composer, 432, 0, 4088);
            boolean zTextElementDialog$lambda$252 = TextElementDialog$lambda$252(mutableState5);
            ComposerKt.sourceInformationMarkerStart(composer, 233947248, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda144
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m803xc9229c5d(mutableState5);
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ChipKt.FilterChip(zTextElementDialog$lambda$252, (Function0) objRememberedValue5, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7659getLambda$670069685$app(), null, false, null, null, null, null, null, null, null, composer, 432, 0, 4088);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
            String str = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, str);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, companion3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            String str2 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, str2);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            String str3 = "C101@5126L9:Row.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, str3);
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            String str4 = "C:CanvasEditorScreen.kt#ska5t9";
            ComposerKt.sourceInformationMarkerStart(composer, -1957340013, "C:CanvasEditorScreen.kt#ska5t9");
            composer.startReplaceGroup(491051250);
            ComposerKt.sourceInformation(composer, "*250@23175L15,250@23192L49,250@23154L87");
            char c = 3;
            boolean z = true;
            for (Pair pair : CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.m921to("START", "يمين"), TuplesKt.m921to("CENTER", "وسط"), TuplesKt.m921to("END", "يسار")})) {
                final String str5 = (String) pair.component1();
                final String str6 = (String) pair.component2();
                ComposerKt.sourceInformationMarkerStart(composer, -844247883, "CC(remember):CanvasEditorScreen.kt#9igjgp");
                boolean zChanged = composer.changed(str5);
                Object objRememberedValue6 = composer.rememberedValue();
                if (zChanged || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    mutableState10 = mutableState6;
                    objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CanvasEditorScreenKt.m804xb56cbb54(str5, mutableState10);
                        }
                    };
                    composer.updateRememberedValue(objRememberedValue6);
                } else {
                    mutableState10 = mutableState6;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                ButtonKt.TextButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-525470781, z, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return CanvasEditorScreenKt.m805x6292d582(str5, str6, mutableState10, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer, 54), composer, 805306368, 510);
                c = c;
                str4 = str4;
                str3 = str3;
                str2 = str2;
                str = str;
                z = true;
            }
            String str7 = str3;
            String str8 = str;
            char c2 = c;
            String str9 = str4;
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion4 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, 48);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, str8);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, companion4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, str2);
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl4 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, str7);
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1779643561, "C250@23301L34,250@23358L39,250@23337L75,250@23435L39,250@23414L75:CanvasEditorScreen.kt#ska5t9");
            String str10 = str2;
            TextKt.m3342Text4IGK_g("الحجم " + MathKt.roundToInt(TextElementDialog$lambda$261(mutableState7)), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            Composer composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, 1442882853, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue7 = composer2.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m806xa443e707(mutableState7);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.TextButton((Function0) objRememberedValue7, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$370376577$app(), composer2, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer2, 1442885317, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue8 = composer2.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m807xb3a731dc(mutableState7);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.TextButton((Function0) objRememberedValue8, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7656getLambda$492036758$app(), composer2, 805306374, 510);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerStart(composer2, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion5 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer2, 0);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, str8);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer2, companion5);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, str10);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor5);
            } else {
                composer2.useNode();
            }
            Composer composerM4301constructorimpl5 = Updater.m4301constructorimpl(composer2);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -407840262, str7);
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer2, 1221665171, str9);
            composer2.startReplaceGroup(-1900246222);
            ComposerKt.sourceInformation(composer2, "*250@23800L16,250@23818L50,250@23779L89");
            Pair[] pairArr = new Pair[10];
            pairArr[0] = TuplesKt.m921to("SANS", "Sans");
            boolean z2 = true;
            pairArr[1] = TuplesKt.m921to("SERIF", "Serif");
            pairArr[2] = TuplesKt.m921to("MONOSPACE", "Mono");
            pairArr[c2] = TuplesKt.m921to("AMIRI", "Amiri");
            pairArr[4] = TuplesKt.m921to("CAIRO", "Cairo");
            pairArr[5] = TuplesKt.m921to("SCHEHERAZADE", "Scheherazade");
            pairArr[6] = TuplesKt.m921to("EL_MESSIRI", "El Messiri");
            pairArr[7] = TuplesKt.m921to("NOTO_KUFI", "Noto Kufi");
            pairArr[8] = TuplesKt.m921to("NOTO_NASKH", "Noto Naskh");
            pairArr[9] = TuplesKt.m921to("TAJAWAL", "Tajawal");
            for (Pair pair2 : CollectionsKt.listOf((Object[]) pairArr)) {
                final String str11 = (String) pair2.component1();
                final String str12 = (String) pair2.component2();
                ComposerKt.sourceInformationMarkerStart(composer2, 1059415796, "CC(remember):CanvasEditorScreen.kt#9igjgp");
                boolean zChanged2 = composer2.changed(str11);
                Object objRememberedValue9 = composer2.rememberedValue();
                if (zChanged2 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    mutableState9 = mutableState8;
                    objRememberedValue9 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CanvasEditorScreenKt.m808x6ca1094(str11, mutableState9);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue9);
                } else {
                    mutableState9 = mutableState8;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.TextButton((Function0) objRememberedValue9, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(455280001, z2, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda140
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return CanvasEditorScreenKt.m809x4d176b01(str11, str12, mutableState9, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), composer2, 805306368, 510);
                composer2 = composer;
                z2 = z2;
            }
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
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

    static final Unit TextElementDialog$lambda$300$lambda$299$lambda$274$lambda$273(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit TextElementDialog$lambda$300$lambda$299$lambda$276$lambda$275(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$283$lambda$278$lambda$277 */
    static final Unit m801x8c975fd(MutableState mutableState) {
        TextElementDialog$lambda$247(mutableState, !TextElementDialog$lambda$246(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$283$lambda$280$lambda$279 */
    static final Unit m802xb9bf5188(MutableState mutableState) {
        TextElementDialog$lambda$250(mutableState, !TextElementDialog$lambda$249(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$283$lambda$282$lambda$281 */
    static final Unit m803xc9229c5d(MutableState mutableState) {
        TextElementDialog$lambda$253(mutableState, !TextElementDialog$lambda$252(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$288$lambda$287$lambda$285$lambda$284 */
    static final Unit m804xb56cbb54(String str, MutableState mutableState) {
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$288$lambda$287$lambda$286 */
    static final Unit m805x6292d582(String str, String str2, MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C250@23194L45:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-525470781, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:250)");
            }
            TextKt.m3342Text4IGK_g(Intrinsics.areEqual(TextElementDialog$lambda$264(mutableState), str) ? str2 + " ✓" : str2, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$293$lambda$290$lambda$289 */
    static final Unit m806xa443e707(MutableState mutableState) {
        TextElementDialog$lambda$262(mutableState, RangesKt.coerceAtLeast(TextElementDialog$lambda$261(mutableState) - 2.0f, 8.0f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$293$lambda$292$lambda$291 */
    static final Unit m807xb3a731dc(MutableState mutableState) {
        TextElementDialog$lambda$262(mutableState, RangesKt.coerceAtMost(TextElementDialog$lambda$261(mutableState) + 2.0f, 96.0f));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$298$lambda$297$lambda$295$lambda$294 */
    static final Unit m808x6ca1094(String str, MutableState mutableState) {
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TextElementDialog$lambda$300$lambda$299$lambda$298$lambda$297$lambda$296 */
    static final Unit m809x4d176b01(String str, String str2, MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C250@23820L46:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(455280001, i, -1, "com.mohammedalhzmi.masrofmanager.ui.TextElementDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:250)");
            }
            TextKt.m3342Text4IGK_g(Intrinsics.areEqual(TextElementDialog$lambda$255(mutableState), str) ? str2 + " ✓" : str2, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void PageSettingsDialog(final DocumentDesignEntity documentDesignEntity, Function0<Unit> function0, final Function1<? super DocumentDesignEntity, Unit> function1, Composer composer, final int i) {
        int i2;
        Composer composer2;
        final Function0<Unit> function2 = function0;
        Composer composerStartRestartGroup = composer.startRestartGroup(2092367375);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PageSettingsDialog)255@24248L48,256@24314L46,257@24379L47,258@24449L47,259@24518L46,260@24588L48,261@24661L49,262@24733L52,277@26769L468,277@27255L53,263@24875L1876,263@24790L2519:CanvasEditorScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(documentDesignEntity) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        int i3 = i2;
        if ((i3 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2092367375, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog (CanvasEditorScreen.kt:254)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720358079, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(documentDesignEntity.getOrientation(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720360189, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getPageWidth()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720362270, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getPageHeight()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720364510, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getMarginLeft()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720366717, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getMarginTop()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720368959, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getMarginRight()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720371296, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(documentDesignEntity.getMarginBottom()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 720373603, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(documentDesignEntity.getBackgroundColor(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
            }
            final MutableState mutableState8 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(494235847, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda117
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$328(function1, documentDesignEntity, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(-117033211, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda118
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$329(function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7660getLambda$728302269$app(), ComposableLambdaKt.rememberComposableLambda(-1033936798, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda119
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$364(mutableState, mutableState2, mutableState3, mutableState5, mutableState6, mutableState7, mutableState4, mutableState8, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, ((i3 >> 3) & 14) | 1772592, 0, 16276);
            function2 = function0;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda120
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$365(documentDesignEntity, function2, function1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String PageSettingsDialog$lambda$303(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final float PageSettingsDialog$lambda$306(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$307(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float PageSettingsDialog$lambda$309(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$310(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float PageSettingsDialog$lambda$312(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$313(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float PageSettingsDialog$lambda$315(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$316(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float PageSettingsDialog$lambda$318(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$319(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final float PageSettingsDialog$lambda$321(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void PageSettingsDialog$lambda$322(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    private static final String PageSettingsDialog$lambda$324(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit PageSettingsDialog$lambda$364(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C263@24877L1872:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1033936798, i, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog.<anonymous> (CanvasEditorScreen.kt:263)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
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
            ComposerKt.sourceInformationMarkerStart(composer, -1006398480, "C264@24944L238,265@25191L217,266@25417L224,267@25740L10,267@25650L111,268@25770L429,272@26208L435,276@26682L19,276@26652L91:CanvasEditorScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, companion2);
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
            Updater.m4308setimpl(composerM4301constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM4301constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM4301constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m4308setimpl(composerM4301constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 647933525, "C264@24971L28,264@25001L63,264@24950L114,264@25087L29,264@25118L62,264@25066L114:CanvasEditorScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer, 713638212, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda82
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m785xeb4861a3(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1656767147, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda88
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$332(mutableState, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer, 713641925, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda89
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m786x25d51c3(mutableState);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue2, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(794353812, true, new Function3() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda91
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CanvasEditorScreenKt.PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$335(mutableState, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer, 54), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion3 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, companion3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl3 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM4301constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM4301constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m4308setimpl(composerM4301constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -2105252927, "C265@25218L31,265@25197L68,265@25288L31,265@25267L68,265@25358L32,265@25337L69:CanvasEditorScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer, -1314836880, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda92
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m787x3e90aa5f(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue3, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$660101460$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer, -1314834640, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda93
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m788xef8685ea(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$809574653$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer, -1314832399, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda94
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m789xfee9d0bf(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue5, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$150508572$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion4 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer, companion4);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl4 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                composerM4301constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                composerM4301constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
            }
            Updater.m4308setimpl(composerM4301constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1457025253, "C266@25444L31,266@25423L68,266@25514L31,266@25493L72,266@25588L32,266@25567L72:CanvasEditorScreen.kt#ska5t9");
            ComposerKt.sourceInformationMarkerStart(composer, -1432473649, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue6 = composer.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda95
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m790x336b83fb(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue6, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1611933299$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer, -1432471409, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue7 = composer.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda96
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m791x42cecebb(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue7, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1761406492$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerStart(composer, -1432469040, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue8 = composer.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda97
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.m792x5232197b(mutableState2, mutableState3);
                    }
                };
                composer.updateRememberedValue(objRememberedValue8);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) objRememberedValue8, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1102340411$app(), composer, 805306374, 510);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextKt.m3342Text4IGK_g("المقاس: " + MathKt.roundToInt(PageSettingsDialog$lambda$306(mutableState2)) + " × " + MathKt.roundToInt(PageSettingsDialog$lambda$309(mutableState3)) + " نقطة", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 0, 0, 65534);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_5 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion5 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_5, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer, companion5);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor5);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl5 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                composerM4301constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                composerM4301constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
            }
            Updater.m4308setimpl(composerM4301constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -808781769, "C269@25880L66,269@25840L165,270@26060L70,270@26018L171:CanvasEditorScreen.kt#ska5t9");
            String strValueOf = String.valueOf(PageSettingsDialog$lambda$315(mutableState4));
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1550109391, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue9 = composer.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda83
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m793x7dd198c0(mutableState4, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue9);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strValueOf, (Function1<? super String, Unit>) objRememberedValue9, modifierWeight$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7667getLambda$897746795$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388536);
            String strValueOf2 = String.valueOf(PageSettingsDialog$lambda$318(mutableState5));
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance4, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1550103627, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue10 = composer.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda84
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m794x8d34e380(mutableState5, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue10);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strValueOf2, (Function1<? super String, Unit>) objRememberedValue10, modifierWeight$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$504957822$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_6 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(4.0f));
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            Modifier.Companion companion6 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_6, Alignment.INSTANCE.getTop(), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer, companion6);
            Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor6);
            } else {
                composer.useNode();
            }
            Composer composerM4301constructorimpl6 = Updater.m4301constructorimpl(composer);
            Updater.m4308setimpl(composerM4301constructorimpl6, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4308setimpl(composerM4301constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4301constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM4301constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                composerM4301constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                composerM4301constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
            }
            Updater.m4308setimpl(composerM4301constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -160554126, "C273@26321L72,273@26278L174,274@26506L68,274@26465L168:CanvasEditorScreen.kt#ska5t9");
            String strValueOf3 = String.valueOf(PageSettingsDialog$lambda$321(mutableState6));
            Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1667746058, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue11 = composer.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue11 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda85
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m795x2d53643a(mutableState6, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue11);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strValueOf3, (Function1<? super String, Unit>) objRememberedValue11, modifierWeight$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$54085044$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388536);
            String strValueOf4 = String.valueOf(PageSettingsDialog$lambda$312(mutableState7));
            Modifier modifierWeight$default4 = RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -1667740142, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue12 = composer.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue12 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda86
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.m796x3cb6aefa(mutableState7, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue12);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strValueOf4, (Function1<? super String, Unit>) objRememberedValue12, modifierWeight$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1456789661$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388536);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            String strPageSettingsDialog$lambda$324 = PageSettingsDialog$lambda$324(mutableState8);
            ComposerKt.sourceInformationMarkerStart(composer, -1972073281, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue13 = composer.rememberedValue();
            if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue13 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda87
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.PageSettingsDialog$lambda$364$lambda$363$lambda$362$lambda$361(mutableState8, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue13);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strPageSettingsDialog$lambda$324, (Function1<? super String, Unit>) objRememberedValue13, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$113966034$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 0, 0, 8388540);
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

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$331$lambda$330 */
    static final Unit m785xeb4861a3(MutableState mutableState) {
        mutableState.setValue("PORTRAIT");
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$332(MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C264@25003L59:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1656767147, i, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:264)");
            }
            TextKt.m3342Text4IGK_g(Intrinsics.areEqual(PageSettingsDialog$lambda$303(mutableState), "PORTRAIT") ? "عمودي ✓" : "عمودي", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$334$lambda$333 */
    static final Unit m786x25d51c3(MutableState mutableState) {
        mutableState.setValue("LANDSCAPE");
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$364$lambda$363$lambda$336$lambda$335(MutableState mutableState, RowScope TextButton, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C264@25120L58:CanvasEditorScreen.kt#ska5t9");
        if ((i & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(794353812, i, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (CanvasEditorScreen.kt:264)");
            }
            TextKt.m3342Text4IGK_g(Intrinsics.areEqual(PageSettingsDialog$lambda$303(mutableState), "LANDSCAPE") ? "أفقي ✓" : "أفقي", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 0, 0, 131070);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$343$lambda$338$lambda$337 */
    static final Unit m787x3e90aa5f(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 595.0f);
        PageSettingsDialog$lambda$310(mutableState2, 842.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$343$lambda$340$lambda$339 */
    static final Unit m788xef8685ea(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 420.0f);
        PageSettingsDialog$lambda$310(mutableState2, 595.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$343$lambda$342$lambda$341 */
    static final Unit m789xfee9d0bf(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 842.0f);
        PageSettingsDialog$lambda$310(mutableState2, 1191.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$350$lambda$345$lambda$344 */
    static final Unit m790x336b83fb(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 298.0f);
        PageSettingsDialog$lambda$310(mutableState2, 420.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$350$lambda$347$lambda$346 */
    static final Unit m791x42cecebb(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 612.0f);
        PageSettingsDialog$lambda$310(mutableState2, 792.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$350$lambda$349$lambda$348 */
    static final Unit m792x5232197b(MutableState mutableState, MutableState mutableState2) {
        PageSettingsDialog$lambda$307(mutableState, 612.0f);
        PageSettingsDialog$lambda$310(mutableState2, 1008.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$355$lambda$352$lambda$351 */
    static final Unit m793x7dd198c0(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Float floatOrNull = StringsKt.toFloatOrNull(it);
        PageSettingsDialog$lambda$316(mutableState, floatOrNull != null ? RangesKt.coerceAtLeast(floatOrNull.floatValue(), 0.0f) : PageSettingsDialog$lambda$315(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$355$lambda$354$lambda$353 */
    static final Unit m794x8d34e380(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Float floatOrNull = StringsKt.toFloatOrNull(it);
        PageSettingsDialog$lambda$319(mutableState, floatOrNull != null ? RangesKt.coerceAtLeast(floatOrNull.floatValue(), 0.0f) : PageSettingsDialog$lambda$318(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$360$lambda$357$lambda$356 */
    static final Unit m795x2d53643a(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Float floatOrNull = StringsKt.toFloatOrNull(it);
        PageSettingsDialog$lambda$322(mutableState, floatOrNull != null ? RangesKt.coerceAtLeast(floatOrNull.floatValue(), 0.0f) : PageSettingsDialog$lambda$321(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PageSettingsDialog$lambda$364$lambda$363$lambda$360$lambda$359$lambda$358 */
    static final Unit m796x3cb6aefa(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Float floatOrNull = StringsKt.toFloatOrNull(it);
        PageSettingsDialog$lambda$313(mutableState, floatOrNull != null ? RangesKt.coerceAtLeast(floatOrNull.floatValue(), 0.0f) : PageSettingsDialog$lambda$312(mutableState));
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$364$lambda$363$lambda$362$lambda$361(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$328(final Function1 function1, final DocumentDesignEntity documentDesignEntity, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableState mutableState7, final MutableState mutableState8, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C277@26788L430,277@26771L464:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(494235847, i, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog.<anonymous> (CanvasEditorScreen.kt:277)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1780719851, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function1) | composer.changed(documentDesignEntity);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda57
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.PageSettingsDialog$lambda$328$lambda$327$lambda$326(function1, documentDesignEntity, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8);
                    }
                };
                composer.updateRememberedValue(function0);
                objRememberedValue = function0;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7652getLambda$2039811369$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$328$lambda$327$lambda$326(Function1 function1, DocumentDesignEntity documentDesignEntity, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8) {
        function1.invoke(documentDesignEntity.copy((199 & 1) != 0 ? documentDesignEntity.id : 0L, (199 & 2) != 0 ? documentDesignEntity.documentType : null, (199 & 4) != 0 ? documentDesignEntity.name : null, (199 & 8) != 0 ? documentDesignEntity.pageWidth : Intrinsics.areEqual(PageSettingsDialog$lambda$303(mutableState), "LANDSCAPE") ? Math.max(PageSettingsDialog$lambda$306(mutableState2), PageSettingsDialog$lambda$309(mutableState3)) : Math.min(PageSettingsDialog$lambda$306(mutableState2), PageSettingsDialog$lambda$309(mutableState3)), (199 & 16) != 0 ? documentDesignEntity.pageHeight : Intrinsics.areEqual(PageSettingsDialog$lambda$303(mutableState), "LANDSCAPE") ? Math.min(PageSettingsDialog$lambda$306(mutableState2), PageSettingsDialog$lambda$309(mutableState3)) : Math.max(PageSettingsDialog$lambda$306(mutableState2), PageSettingsDialog$lambda$309(mutableState3)), (199 & 32) != 0 ? documentDesignEntity.backgroundColor : PageSettingsDialog$lambda$324(mutableState8), (199 & 64) != 0 ? documentDesignEntity.backgroundImageUri : null, (199 & 128) != 0 ? documentDesignEntity.updatedAt : 0L, (199 & 256) != 0 ? documentDesignEntity.orientation : PageSettingsDialog$lambda$303(mutableState), (199 & 512) != 0 ? documentDesignEntity.marginLeft : PageSettingsDialog$lambda$312(mutableState4), (199 & 1024) != 0 ? documentDesignEntity.marginTop : PageSettingsDialog$lambda$315(mutableState5), (199 & 2048) != 0 ? documentDesignEntity.marginRight : PageSettingsDialog$lambda$318(mutableState6), (199 & 4096) != 0 ? documentDesignEntity.marginBottom : PageSettingsDialog$lambda$321(mutableState7)));
        return Unit.INSTANCE;
    }

    static final Unit PageSettingsDialog$lambda$329(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C277@27257L49:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-117033211, i, -1, "com.mohammedalhzmi.masrofmanager.ui.PageSettingsDialog.<anonymous> (CanvasEditorScreen.kt:277)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$522283560$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void AiLayoutDialog(final Function0<Unit> function0, final Function4<? super String, ? super String, ? super String, ? super Function1<? super String, Unit>, Unit> function4, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2125318338);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AiLayoutDialog)282@27451L31,283@27503L73,284@27600L108,285@27727L70,292@28545L136,292@28699L53,286@27893L634,286@27802L951:CanvasEditorScreen.kt#ska5t9");
        if ((i & 6) == 0) {
            i2 = i | (composerStartRestartGroup.changedInstance(function0) ? 4 : 2);
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
        }
        int i3 = i2;
        if ((i3 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2125318338, i3, -1, "com.mohammedalhzmi.masrofmanager.ui.AiLayoutDialog (CanvasEditorScreen.kt:281)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1973505631, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1973503925, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("https://api.openai.com/v1/chat/completions", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1973500786, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("نسق الصفحة بشكل رسمي، وحاذِ العنوان في الوسط وضع QR في الزاوية اليمنى السفلية", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1973496760, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("المفتاح لا يُحفظ ولا يُضمن داخل التطبيق", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            AndroidAlertDialog_androidKt.m2410AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-1785825526, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda103
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.AiLayoutDialog$lambda$381(function4, mutableState3, mutableState, mutableState2, mutableState4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableLambdaKt.rememberComposableLambda(1561089804, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda104
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.AiLayoutDialog$lambda$382(function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$613037838$app(), ComposableLambdaKt.rememberComposableLambda(139011855, true, new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda105
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.AiLayoutDialog$lambda$390(mutableState, mutableState2, mutableState3, mutableState4, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, composer2, (i3 & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda106
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CanvasEditorScreenKt.AiLayoutDialog$lambda$391(function0, function4, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String AiLayoutDialog$lambda$367(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AiLayoutDialog$lambda$370(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AiLayoutDialog$lambda$373(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AiLayoutDialog$lambda$376(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit AiLayoutDialog$lambda$390(final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, MutableState mutableState4, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C286@27895L630:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(139011855, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AiLayoutDialog.<anonymous> (CanvasEditorScreen.kt:286)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM1538spacedBy0680j_4 = Arrangement.INSTANCE.m1538spacedBy0680j_4(C1786Dp.m7249constructorimpl(8.0f));
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM1538spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer, 6);
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
            ComposerKt.sourceInformationMarkerStart(composer, 1898796305, "C287@28102L10,287@27962L161,288@28155L12,288@28132L95,289@28264L17,289@28236L109,290@28385L20,290@28354L100,291@28498L10,291@28463L56:CanvasEditorScreen.kt#ska5t9");
            TextKt.m3342Text4IGK_g("يعمل محليًا دون اتصال بقواعد تنسيق آمنة، ويستخدم المساعد السحابي عند توفر الشبكة والمفتاح لطلبات اللغة الحرة.", (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 6, 0, 65534);
            String strAiLayoutDialog$lambda$367 = AiLayoutDialog$lambda$367(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 476899121, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.AiLayoutDialog$lambda$390$lambda$389$lambda$384$lambda$383(mutableState, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strAiLayoutDialog$lambda$367, (Function1<? super String, Unit>) objRememberedValue, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7649getLambda$169144929$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12582912, 0, 8257468);
            String strAiLayoutDialog$lambda$370 = AiLayoutDialog$lambda$370(mutableState2);
            ComposerKt.sourceInformationMarkerStart(composer, 476902614, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda45
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.AiLayoutDialog$lambda$390$lambda$389$lambda$386$lambda$385(mutableState2, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strAiLayoutDialog$lambda$370, (Function1<? super String, Unit>) objRememberedValue2, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$1393236118$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 12582912, 0, 8257468);
            String strAiLayoutDialog$lambda$373 = AiLayoutDialog$lambda$373(mutableState3);
            ComposerKt.sourceInformationMarkerStart(composer, 476906489, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda46
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CanvasEditorScreenKt.AiLayoutDialog$lambda$390$lambda$389$lambda$388$lambda$387(mutableState3, (String) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(strAiLayoutDialog$lambda$373, (Function1<? super String, Unit>) objRememberedValue3, (Modifier) null, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$CanvasEditorScreenKt.INSTANCE.m7647getLambda$1411162473$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 3, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1572912, 805306368, 0, 7864252);
            TextKt.m3342Text4IGK_g(AiLayoutDialog$lambda$376(mutableState4), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 0, 0, 65534);
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

    static final Unit AiLayoutDialog$lambda$390$lambda$389$lambda$384$lambda$383(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$390$lambda$389$lambda$386$lambda$385(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$390$lambda$389$lambda$388$lambda$387(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$381(final Function4 function4, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C292@28600L53,292@28547L132:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1785825526, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AiLayoutDialog.<anonymous> (CanvasEditorScreen.kt:292)");
            }
            boolean z = !StringsKt.isBlank(AiLayoutDialog$lambda$373(mutableState));
            ComposerKt.sourceInformationMarkerStart(composer, 1808589727, "CC(remember):CanvasEditorScreen.kt#9igjgp");
            boolean zChanged = composer.changed(function4);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Function0 function0 = new Function0() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda48
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CanvasEditorScreenKt.AiLayoutDialog$lambda$381$lambda$380$lambda$379(function4, mutableState2, mutableState3, mutableState, mutableState4);
                    }
                };
                composer.updateRememberedValue(function0);
                objRememberedValue = function0;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.Button((Function0) objRememberedValue, null, z, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$830554874$app(), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$381$lambda$380$lambda$379(Function4 function4, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, final MutableState mutableState4) {
        function4.invoke(AiLayoutDialog$lambda$367(mutableState), AiLayoutDialog$lambda$370(mutableState2), AiLayoutDialog$lambda$373(mutableState3), new Function1() { // from class: com.mohammedalhzmi.masrofmanager.ui.CanvasEditorScreenKt$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CanvasEditorScreenKt.AiLayoutDialog$lambda$381$lambda$380$lambda$379$lambda$378(mutableState4, (String) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$381$lambda$380$lambda$379$lambda$378(MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AiLayoutDialog$lambda$382(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C292@28701L49:CanvasEditorScreen.kt#ska5t9");
        if ((i & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1561089804, i, -1, "com.mohammedalhzmi.masrofmanager.ui.AiLayoutDialog.<anonymous> (CanvasEditorScreen.kt:292)");
            }
            ButtonKt.TextButton(function0, null, false, null, null, null, null, null, null, ComposableSingletons$CanvasEditorScreenKt.INSTANCE.getLambda$130030217$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final String typeName(DocumentType documentType) {
        int i = WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()];
        if (i == 1) {
            return "أمر الصرف";
        }
        if (i != 2) {
            return i != 3 ? documentType.name() : "ورقة الاستلام";
        }
        return "ورقة التقديم";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<DesignElementEntity> CanvasEditorScreen$lambda$0(State<? extends List<DesignElementEntity>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DocumentDesignEntity CanvasEditorScreen$lambda$25(State<DocumentDesignEntity> state) {
        return state.getValue();
    }

    private static final Bitmap DocumentImage$lambda$202(State<Bitmap> state) {
        return state.getValue();
    }
}
