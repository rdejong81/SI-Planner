module di {
    requires dal;
    requires invoice;
    requires logic;
    exports Database;
    exports Invoice;
}