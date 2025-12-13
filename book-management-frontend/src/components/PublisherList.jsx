import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function PublisherList() {
    const [publishers, setPublishers] = useState([]);

    const fetchPublishers = async () => {
        try {
            const res = await axios.get("http://localhost:8080/api/publishers");
            setPublishers(res.data);
        } catch (err) {
            console.error(err);
        }
    };

    const deletePublisher = async (id) => {
        if (!window.confirm("Are you sure you want to delete this publisher?")) return;
        try {
            await axios.delete(`http://localhost:8080/api/publishers/${id}`);
            fetchPublishers();
        } catch (err) {
            console.error(err);
        }
    };

    useEffect(() => {
        fetchPublishers();
    }, []);

    return (
        <div className="p-4">
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-2xl font-bold">Publishers</h2>
                <Link
                    to="/publishers/create"
                    className="bg-blue-500 text-white px-3 py-1 rounded"
                >
                    Add Publisher
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
                {publishers.map((p) => (
                    <tr key={p.id}>
                        <td className="border p-2">{p.name}</td>
                        <td className="border p-2">
                            <button
                                className="bg-red-500 text-white px-2 py-1 rounded"
                                onClick={() => deletePublisher(p.id)}
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
