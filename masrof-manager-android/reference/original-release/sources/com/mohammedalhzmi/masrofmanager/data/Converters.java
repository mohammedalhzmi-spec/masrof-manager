package com.mohammedalhzmi.masrofmanager.data;

import com.google.firebase.firestore.model.Values;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MasrofDatabase.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005H\u0007J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0005H\u0007¨\u0006\u000f"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/Converters;", "", "<init>", "()V", "fromDocumentType", "", Values.VECTOR_MAP_VECTORS_KEY, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "toDocumentType", "fromDocumentStatus", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "toDocumentStatus", "fromContactType", "Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "toContactType", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class Converters {
    public static final int $stable = 0;

    public final String fromDocumentType(DocumentType value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value.name();
    }

    public final DocumentType toDocumentType(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return DocumentType.valueOf(value);
    }

    public final String fromDocumentStatus(DocumentStatus value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value.name();
    }

    public final DocumentStatus toDocumentStatus(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return DocumentStatus.valueOf(value);
    }

    public final String fromContactType(ContactType value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return value.name();
    }

    public final ContactType toContactType(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return ContactType.valueOf(value);
    }
}
