// api/server.js

const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());
app.use(express.json());

// API動作確認
app.get("/api/health", (_req, res) => {
  res.json({ ok: true });
});

// ダミー社員データ
const EMPLOYEES = [
  { employee_no: "1001", full_name: "Yamada Taro",  department: "Sales", email: "taro@example.com" },
  { employee_no: "1002", full_name: "Suzuki Hanako", department: "HR",    email: "hanako@example.com" },
  { employee_no: "1003", full_name: "Sato Jiro",    department: "IT",    email: "jiro@example.com" }
];

// 社員検索
app.get("/api/employees", (req, res) => {
  const q = (req.query.query || "").trim().toLowerCase();
  const result = q
    ? EMPLOYEES.filter(e =>
        e.employee_no.toLowerCase().includes(q) ||
        e.full_name.toLowerCase().includes(q) ||
        e.department.toLowerCase().includes(q)
      )
    : EMPLOYEES;
  res.json(result);
});

const port = process.env.PORT || 8080;
app.listen(port, () => console.log(`API running on http://localhost:${port}`));
