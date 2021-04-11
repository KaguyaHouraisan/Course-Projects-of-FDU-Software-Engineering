.class public Lfudan/edu/cn/ics_lab_smali/MainActivity;
.super Landroid/support/v7/app/AppCompatActivity;
.source "MainActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# instance fields
.field private mBtn:Landroid/widget/Button;

.field private mEt:Landroid/widget/EditText;

.field private mResult:Landroid/widget/TextView;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 10
    invoke-direct {p0}, Landroid/support/v7/app/AppCompatActivity;-><init>()V

    return-void
.end method

.method private initView()V
    .locals 1

    .line 24
    const v0, 0x7f070022

    invoke-virtual {p0, v0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mBtn:Landroid/widget/Button;

    .line 25
    iget-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mBtn:Landroid/widget/Button;

    invoke-virtual {v0, p0}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 26
    const v0, 0x7f070038

    invoke-virtual {p0, v0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iput-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mEt:Landroid/widget/EditText;

    .line 27
    const v0, 0x7f070060

    invoke-virtual {p0, v0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mResult:Landroid/widget/TextView;

    .line 28
    return-void
.end method


# virtual methods
.method public check(Ljava/lang/String;)Ljava/lang/String;
    .locals 13
    .param p1, "in"    # Ljava/lang/String;

    .line 42
    const/4 v0, 0x5

    .line 43
    .local v0, "k1":I
    const/16 v1, 0x9

    .line 44
    .local v1, "k2":I
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    .line 46
    .local v2, "len":I
    const-string v3, ""

    .line 47
    .local v3, "out":Ljava/lang/String;
    const/4 v4, 0x2

    new-array v4, v4, [C

    fill-array-data v4, :array_0

    .line 49
    .local v4, "b":[C
    add-int/lit8 v5, v2, -0x9

    const/4 v6, 0x1

    :try_start_0
    div-int v5, v6, v5
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    .line 50
    .local v5, "d":I
    const/4 v7, 0x0

    move-object v8, v3

    const/4 v3, 0x0

    .local v3, "i":I
    .local v8, "out":Ljava/lang/String;
    :goto_0
    if-ge v3, v2, :cond_0

    .line 51
    :try_start_1
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/String;->toCharArray()[C

    move-result-object v9

    aget-char v9, v9, v3

    aget-char v10, v4, v7

    sub-int/2addr v9, v10

    .line 52
    .local v9, "enc":I
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v10, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    mul-int v11, v0, v9

    add-int/2addr v11, v1

    rem-int/lit8 v11, v11, 0x1a

    aget-char v12, v4, v6

    add-int/2addr v11, v12

    int-to-char v11, v11

    invoke-static {v11}, Ljava/lang/String;->valueOf(C)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move-object v8, v10

    .line 50
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 54
    .end local v3    # "i":I
    .end local v5    # "d":I
    .end local v9    # "enc":I
    :catch_0
    move-exception v3

    goto :goto_1

    .line 57
    :cond_0
    goto :goto_2

    .line 54
    .end local v8    # "out":Ljava/lang/String;
    .local v3, "out":Ljava/lang/String;
    :catch_1
    move-exception v5

    move-object v8, v3

    move-object v3, v5

    .line 55
    .local v3, "e":Ljava/lang/Exception;
    .restart local v8    # "out":Ljava/lang/String;
    :goto_1
    const/4 v5, 0x3

    new-array v5, v5, [C

    fill-array-data v5, :array_1

    .line 56
    .local v5, "c":[C
    invoke-static {v5}, Ljava/lang/String;->valueOf([C)Ljava/lang/String;

    move-result-object v8

    .line 58
    .end local v3    # "e":Ljava/lang/Exception;
    .end local v5    # "c":[C
    :goto_2
    return-object v8

    nop

    :array_0
    .array-data 2
        0x61s
        0x41s
    .end array-data

    :array_1
    .array-data 2
        0x65s
        0x72s
        0x72s
    .end array-data
.end method

.method public onClick(Landroid/view/View;)V
    .locals 3
    .param p1, "v"    # Landroid/view/View;

    .line 32
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    const v1, 0x7f070022

    if-eq v0, v1, :cond_0

    goto :goto_0

    .line 34
    :cond_0
    iget-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mResult:Landroid/widget/TextView;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setVisibility(I)V

    .line 35
    iget-object v0, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mEt:Landroid/widget/EditText;

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 36
    .local v0, "content":Ljava/lang/String;
    iget-object v1, p0, Lfudan/edu/cn/ics_lab_smali/MainActivity;->mResult:Landroid/widget/TextView;

    invoke-virtual {p0, v0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->check(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 39
    .end local v0    # "content":Ljava/lang/String;
    :goto_0
    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .line 18
    invoke-super {p0, p1}, Landroid/support/v7/app/AppCompatActivity;->onCreate(Landroid/os/Bundle;)V

    .line 19
    const v0, 0x7f09001c

    invoke-virtual {p0, v0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->setContentView(I)V

    .line 20
    invoke-direct {p0}, Lfudan/edu/cn/ics_lab_smali/MainActivity;->initView()V

    .line 21
    return-void
.end method
