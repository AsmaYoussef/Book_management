import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function TagList() {
    const [tags, setTags] = useState([]);

    const fetchTags = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/tags");
            setTags(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    const deleteTag = async (id) => {
        if (!window.confirm("Are you sure you want to delete this tag?")) return;
        try {
            await axios.delete(`http://localhost:8080/api/tags/${id}`);
            fetchTags();
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        fetchTags();
    }, []);

    return (
        <div className="p-4">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-2xl font-bold">Tags</h2>
                <Link
                    to="/tags/create"
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                >
                    Add Tag
                </Link>
            </div>

            <table className="table-auto border-collapse border border-gray-300 w-full">
                <thead>
                <tr>
                    <th className="border p-2">Name</th>
                    <th className="border p-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                {tags.map((t) => (
                    <tr key={t.id}>
                        <td className="border p-2">{t.name}</td>
                        <td className="border p-2">
                            <button
                                className="bg-red-500 text-white px-2 py-1 rounded"
                                onClick={() => deleteTag(t.id)}
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
