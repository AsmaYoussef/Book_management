import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function AuthorList() {
    const [authors, setAuthors] = useState([]);

    const fetchAuthors = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/authors");
            setAuthors(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    const deleteAuthor = async (id) => {
        if (!window.confirm("Are you sure you want to delete this author?")) return;
        try {
            await axios.delete(`http://localhost:8080/api/authors/${id}`);
            fetchAuthors();
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        fetchAuthors();
    }, []);

    return (
        <div className="p-4">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-2xl font-bold">Authors</h2>
                <Link
                    to="/authors/create"
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                >
                    Add Author
                </Link>
            </div>

            <table className="table-auto border-collapse border border-gray-300 w-full">
                <thead>
                <tr>
                    <th className="border p-2">Name</th>
                    <th className="border p-2">Email</th>
                    <th className="border p-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {authors.map((a) => (
                    <tr key={a.id}>
                        <td className="border p-2">{a.name}</td>
                        <td className="border p-2">{a.email}</td>
                        <td className="border p-2">
                            <button
                                className="bg-red-500 text-white px-2 py-1 rounded"
                                onClick={() => deleteAuthor(a.id)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
