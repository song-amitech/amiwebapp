import express from "express";
import cors from "cors";
import pkg from "pg";
const { Pool } = pkg;


const app = express();
app.use(cors());
app.use(express.json());


// App Service の「構成」>「アプリケーション設定」に DB_URL を設定しておくこと
// 例: postgres://<USER>:<PASSWORD>@<HOST>:5432/<DBNAME>?sslmode=require
const pool = new Pool({
connectionString: process.env.DB_URL,
ssl: { rejectUnauthorized: false }
});


app.get("/api/health", (_req, res) => res.json({ ok: true }));


// 社員検索 API
app.get("/api/employees", async (req, res) => {
const q = (req.query.query || "").trim();
try {
let sql = "SELECT employee_no, full_name, department, email FROM employees";
let params = [];
if (q) {
sql += " WHERE employee_no ILIKE $1 OR full_name ILIKE $1 OR department ILIKE $1";
params = [`%${q}%`];
}
sql += " ORDER BY employee_no ASC LIMIT 50";


const { rows } = await pool.query(sql, params);
res.json(rows);
} catch (e) {
console.error(e);
res.status(500).json({ error: "internal_error" });
}
});


const port = process.env.PORT || 8080;
app.listen(port, () => console.log(`API listening on ${port}`));